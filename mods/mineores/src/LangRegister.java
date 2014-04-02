package mods.mineores.src;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LangRegister {
	
	public void lang()
	{
		System.out.println("[Mine Ores] now checking registering new language.");
		
		LanguageRegistry.addName(Core.blockAmethyst, "Amethyst Block");
	      
	      LanguageRegistry.addName(Core.oreAmethyst, "Amethyst Ore");
	      
	      LanguageRegistry.addName(Core.Amethyst,"Amethyst");
	}

}
