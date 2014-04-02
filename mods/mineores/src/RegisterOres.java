package mods.mineores.src;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RegisterOres {
	
	public void registerBlocks()
	{
		/**
		 * ログのpreInit中にこのメッセージが出てきたら、目当てのタイミングでこのメソッドを呼び出せたということです。
		 * 登録がきちんと終わったか、下の方でもメッセージを出しておきます。
		 */
		System.out.println("[Mine Ores] Now checking loading new ores.");
		
		/**
		 * インスタンスや変数の頭に「Core.」をつければエラーは出ません。
		 * 「CoreクラスにあるoreAmethyst」を呼び出して使っているからです。
		 * 注意として、引数(()のこと)を忘れないこと、
		 */
		Core.oreAmethyst = new OreAmethyst(Core.oreAmethystID, Material.rock).
				setCreativeTab(Core.mineores).setUnlocalizedName("oreAmethyst");
	    GameRegistry.registerBlock(Core.oreAmethyst, "oreAmethyst");
	    
	    /**
		 * 登録がきちんと終わったか、確認用のメッセージです。
		 * 「.getUnlocalizedName()」は、登録した名前（内部用の名前です）をString（文字列のことです）を呼ぶメソッドです。
		 * これを、ここだけで使う臨時のString、「oreName」に代入しています。
		 */
	    String oreName = Core.oreAmethyst.getUnlocalizedName();
		System.out.println("[Mine Ores] Succeeded to register " + oreName);
	}
	
	public void registerMaterials()
	{
		System.out.println("[Mine Ores] now checking loading new materials.");
		
		Core.Amethyst = new Amethyst(Core.AmethystID).
				setCreativeTab(Core.mineores).setUnlocalizedName("Amethyst");
	      GameRegistry.registerItem(Core.Amethyst, "Amethyst");

	}

}
