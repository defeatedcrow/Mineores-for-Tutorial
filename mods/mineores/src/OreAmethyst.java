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

public class OreAmethyst extends Block{
	
	/**
	 * 最初のsuperなんちゃらの部分は、バニラ含む全てのブロックの共通事項を登録してくれます。
	 * 実はこのメソッドだけでもブロックは作れますが、ブロックに関する色々な内容がデフォルトの状態のままになるので、
	 * 下記のように@Overrideを付けた色々なメソッドを用意します。
	 * ＠Overrideが付いたメソッドは、「デフォルトとは違う特別な特徴」を付けるためのものです。
	 * Blockクラスにある同じ名前のメソッドの代わりに働きます。
	 * 専用のテクスチャとか、ドロップアイテムの指定のようなものに使います。
	 */
	public OreAmethyst (int blockid, Material material)
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
	}
	
	/**
	 * ブロックを破壊した場合のドロップアイテムです。
	 * ここで、例えばアイテムのアメジストIDを、Core.Amethyst.itemIDで指定すれば、ダイヤやラピスのようにアイテムをドロップします。
	 * 試しにアイテムのアメジストをドロップさせてみます。
	 */
	@Override
	public int idDropped(int metadata, Random rand, int fortune)
	{
		return Core.Amethyst.itemID;
	}
	
	/**
	 * ブロックを破壊した場合のドロップアイテムの数です。
	 * random.nextInt(n)で、0～(n-1)の間のランダムな数字が使えるので、
	 * 下記のように個数をランダムにすることもできます。
	 * 
	 * 今回は、if文で場合分けして、ランダムに変化させてみます。
	 * ランダムな数値が0の時（つまり1/3の確率ですね）で、3個ドロップします。
	 * 
	 * このランダムな数値自体をドロップ数に加算することも出来ます。
	 * （「return 1 + r;」 等と書きます）
	 */
	public int quantityDropped(Random random)
	{
		int r = random.nextInt(3);
		if (r == 0)
		{
			return 3;
		}
		else
		{
			return 1;
		}
	}
	
	/**
	 * オリジナルテクスチャを指定します。
	 * この場合だと、assets/mineores/textures/blocks/amethystore.pngが読み込まれます。
	 * テクスチャは、エクリプス上ではmcp/src/minecraft/srcの中にassetsフォルダを作って、
	 * 更にその中にtexturesフォルダ、その中にblocksフォルダを作ります。blocksフォルダにpng画像を入れます。
	 * 
	 * modとしてリリースする際は、
	 * assetsフォルダ以下を一緒にzipファイルに入れれば反映します。
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("mineores:amethystore");
		
	}
}
