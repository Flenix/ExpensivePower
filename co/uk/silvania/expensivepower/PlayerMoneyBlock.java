package co.uk.silvania.expensivepower;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlayerMoneyBlock extends BlockContainer {

	protected PlayerMoneyBlock(int id) {
		super(id, Material.iron);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(3.0F);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileEntityPlayerMoneyBlock();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		
		if (world.isRemote) return true; 
		
		TileEntityPlayerMoneyBlock te = (TileEntityPlayerMoneyBlock) world.getBlockTileEntity(x, y, z);
		
		if (te != null) {
			if (player.isSneaking()) te.energy = 0;
			player.addChatMessage("Current Energy: " + te.energy);
		}
		return true;
	}

}
