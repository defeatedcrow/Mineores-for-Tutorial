package mods.mineores.src;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.src.*;
import net.minecraft.util.Icon;

public class AmethystBlock extends Block{
	
	/**
	 * 最初のsuperなんちゃらの部分は、バニラ含む全てのブロックの共通事項を登録してくれます。
	 * 実はこのメソッドだけでもブロックは作れますが、ブロックに関する色々な内容がデフォルトの状態のままになるので、
	 * 下記のように@Overrideを付けた色々なメソッドを用意します。
	 * ＠Overrideが付いたメソッドは、「デフォルトとは違う特別な特徴」を付けるためのものです。
	 * Blockクラスにある同じ名前のメソッドの代わりに働きます。
	 * 専用のテクスチャとか、ドロップアイテムの指定のようなものに使います。
	 */
	public AmethystBlock (int blockid, Material material)
	{
		super(blockid, material);
		//これは上を歩いた時の音です。Stoneなので石と同じ音がします。
		this.setStepSound(Block.soundStoneFootstep);
		//壊すのにかかる時間です。
		this.setHardness(1.0F);
		//爆発耐性です。
		this.setResistance(1.0F);
		//明るさです。1.0Fで最大、グロウストーンとおなじになります。
		this.setLightValue(0.5F);
		//「this.set」まで入力した辺りでエクリプスが色々な候補を出してくれると思うので、
		//変えたら面白そう、というものを色々変えて試してみるといいです。
	}
	
	/**
	 * ブロックを破壊した場合のドロップアイテムです。
	 * ここで、例えばアイテムのアメジストIDを、Core.Amethyst.itemIDで指定すれば、ダイヤやラピスのようにアメジストの宝石アイテムをドロップします。
	 */
	@Override
	public int idDropped(int metadata, Random rand, int fortune)
	{
		return this.blockID;
	}
	
	/**
	 * ブロックを破壊した場合のドロップアイテムの数です。
	 * random.nextInt(n)で、0～(n-1)の間のランダムな数字が使えるので、
	 * 下記のように個数をランダムにすることもできます。
	 * 
	 * 「1」とだけ記述した場合は、1個だけドロップします。
	 */
	public int quantityDropped(Random random)
	{
		return 1 + random.nextInt(3);
	}
	
	/**
	 * オリジナルテクスチャを指定します。
	 * この場合だと、assets/mineores/textures/blocks/amethyst.pngが読み込まれます。
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("mineores:amethyst");
		
	}
}
