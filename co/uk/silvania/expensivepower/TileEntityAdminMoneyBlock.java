package co.uk.silvania.expensivepower;

import ic2.api.Direction;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityAdminMoneyBlock extends TileEntity implements IEnergySource {

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
	public boolean isAddedToEnergyNet() {
		return true;
	}

	@Override
	public boolean emitsEnergyTo(TileEntity receiver, Direction direction) {
		return true;
	}

	@Override
	public int getMaxEnergyOutput() {
		return 128;
	}

}
