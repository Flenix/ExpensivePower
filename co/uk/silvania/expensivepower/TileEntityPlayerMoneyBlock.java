package co.uk.silvania.expensivepower;

import ic2.api.Direction;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityPlayerMoneyBlock extends TileEntity implements IEnergySink {

	public int energy = 0;
	public int maxEnergy = 10000;
	private boolean initialized;
	
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		
		if(nbtTagCompound.hasKey("energy")) {
			this.energy = nbtTagCompound.getInteger("energy");
		}
	}
	
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		nbtTagCompound.setInteger("energy", this.energy);
	}
	
	@Override
	public void updateEntity() {
		if(!initialized && worldObj != null) {
			if(!worldObj.isRemote) {
				EnergyTileLoadEvent loadEvent = new EnergyTileLoadEvent(this);
				MinecraftForge.EVENT_BUS.post(loadEvent);
			}
			initialized = true;
		}
	}
	
	@Override
	public void invalidate() {
		EnergyTileUnloadEvent unloadEvent = new EnergyTileUnloadEvent(this);
		MinecraftForge.EVENT_BUS.post(unloadEvent);
	}
	
	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, Direction direction) {
		return true;
	}

	@Override
	public boolean isAddedToEnergyNet() {
		return true;
	}

	@Override
	public int demandsEnergy() {
		return this.maxEnergy - this.energy;
	}

	@Override
	public int injectEnergy(Direction directionFrom, int amount) {
		if (this.energy >= this.maxEnergy) return amount;
		
		int openEnergy = this.maxEnergy - this.energy;
		
		if (openEnergy >= amount) {
			this.energy += amount;
			return 0;
		} else if (amount > openEnergy) {
			this.energy = this.maxEnergy;
			return amount - openEnergy;
		}
		
		return 0;
	}

	@Override
	public int getMaxSafeInput() {
		return 512;
	}

}
