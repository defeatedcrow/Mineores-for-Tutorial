package mods.mineores.src;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * 拙作のMODの蛍石生成クラスの丸写しだったりします。
 */
public class WorldGeneration implements IWorldGenerator {
	
	/**
	 * Dimはディメンションです。
	 * 0の時が地上、1はエンドで-1はネザーだったかな？
	 * 下記の場合は、「0の時」「1より大きい」「-1より小さい」ですから、
	 * ネザーとエンド以外なら、他MODで追加される新しいディメンション（黄昏やCaveWorld等ですね）でも生成します。
	 */
	private int genDim1 = 0;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		//ワールドのディメンションIDを確かめています。
		genDim1 = world.provider.dimensionId;
		
		//左に4シフト。つまり16倍する処理。チャンク番号からX・Z座標を割り出しているらしい。
		int chunk2X = chunkX << 4;
        int chunk2Z = chunkZ << 4;         
        
    	if ((genDim1 == 0) || (genDim1 > 1) || (genDim1 < -1))
    	{
    	    	//同じ処理を5回繰り返すことで生成確率を上げている。
    			//「Core.oreAmethyst.blockID」の部分を変えて、for～以降を続けて書いてやれば、他の鉱石の生成もここで登録できる。
    			for(int i = 0; i < 5; i++)
    			{
    				//そのチャンク内のX座標（16マス）のどこかを選んでいる。
    				int randomPosX = chunk2X + random.nextInt(16);
    				//0～30のランダムな数値を選ぶ。これが生成する高さになるので、この鉱石はY=0～30の範囲に生成する。
    				int randomPosY = random.nextInt(30);
    				//そのチャンク内のZ座標（16マス）のどこかを選んでいる。
    				int randomPosZ = chunk2Z + random.nextInt(16);
    				//new WorldGenMinable(Core.oreAmethyst.blockID, 4)なので、最大で4個が固まって生成する。
    				(new WorldGenMinable(Core.oreAmethyst.blockID, 4)).generate(world, random, randomPosX, randomPosY, randomPosZ);
    			}
    	}

	}

}
