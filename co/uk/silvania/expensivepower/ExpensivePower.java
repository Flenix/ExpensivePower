package co.uk.silvania.expensivepower;

import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid=ExpensivePower.modid, name="Expensive Power", version="0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired = false)
public class ExpensivePower {
	
	@Instance("expensivepower")
	public static ExpensivePower instance;
	
	@SidedProxy(clientSide="co.uk.silvania.expensivepower.client.ClientProxy", serverSide="co.uk.silvania.expensivepower.CommonProxy")
	public static CommonProxy proxy;
	public final static String modid = "expensivepower";
	
	public static Block adminMoney;
	public static Block playerMoney;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		adminMoney = new AdminMoneyBlock(500).setUnlocalizedName("adminMoneyBlock");
		playerMoney = new PlayerMoneyBlock(501).setUnlocalizedName("playerMoneyBlock");
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerBlocks();
		proxy.addNames();
		proxy.addRecipes();
		
		GameRegistry.registerTileEntity(TileEntityAdminMoneyBlock.class, "adminMoneyBlockEntity");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}

}
