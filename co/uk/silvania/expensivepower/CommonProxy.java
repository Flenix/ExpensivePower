package co.uk.silvania.expensivepower;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {
    
    public void registerBlocks() {
    	GameRegistry.registerBlock(ExpensivePower.adminMoney, "adminMoney");
    	GameRegistry.registerBlock(ExpensivePower.playerMoney, "playerMoney");
    }
    
    public void addNames() {
    	LanguageRegistry.addName(ExpensivePower.adminMoney, "Admin Electricity Meter");
    	LanguageRegistry.addName(ExpensivePower.playerMoney, "Electricity Meter");
    	
    }
    
    public void addRecipes() {
    	
    }

}
