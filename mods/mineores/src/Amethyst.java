package mods.mineores.src;

import net.minecraft.src.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Amethyst extends Item {
	
	public Amethyst (int itemId){
		super (itemId);
		//アイテムの最大スタックサイズです。
		//バニラでは投擲可能物が16個になる慣例があるようですが、これは普通のアイテムなので64個で良いでしょう。
		this.maxStackSize = 64;

	}
	
	/**
	 * オリジナルテクスチャを指定します。
	 * この場合だと、assets/mineores/textures/items/amethyst.pngが読み込まれます。
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
	this.itemIcon = par1IconRegister.registerIcon("mineores:amethyst");
	}

}
