package mods.mineores.src;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

//必要なクラスは下記のようにインポートしないと使用できません。
import mods.mineores.src.RegisterOres;;

/**
 * @Mod を書いたクラスがコアとなるクラスです。
 * 初期化イベント（(FMLInitializationEvent event)等を含むもの）は、このクラス内に置きます。
 * 他のクラスに分散するとおかしくなります。
 * 初期化イベントを他のクラスに移動させるというのは、動作原理が分かっていないなら、手を出さないべきものです。
 * 
 * 余談ですが、modidは他MODと被るとかなり致命的な部分なので、
 * 被りにくい名前をお勧めします。
 */
@Mod(modid="mineores",name="Mine Ores",version="1.0")

/**
 * @NetworkMod
 * 無くても動く気はしますが、念のため。
 * 今後、パケットが必要になった時は、このカッコ内でチャンネル登録をします。
 */
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false)

public class Core
{

	//インスタンスの生成
	@Instance("Core")
	public static Core instance;
	
	/**
	 * 勝手ながらクリエイティブタブを増やしました。
	 * （私の製作環境で検証する都合で、タブを整理しないと色々ごっちゃになるのです。）
	 * CreativeTabMineOresクラス内で、アイコン用にしたいブロックやアイテム、名前などが変えられるので、
	 * 好きな様に変更して下さい。
	 */
	public static final CreativeTabs mineores = new CreativeTabMineOres("mineores");
	
	/**
	 * Blockインスタンス及びブロックIDの宣言をここで行います。
	 * やろうと思えば他のクラス内で行うことも出来ると思いますが、少なくとも「インスタンスって何？」くらいの段階なら、
	 * 手出ししないほうがいいです。
	 * 
	 * 呼び出し方ですが、
	 * このクラス内なら下の名前のまま、または「this.blockAmethyst」のような形で使用できます。
	 * 他のクラスでこれらのブロックを使いたい時は、
	 * 「Core.blockAmethyst」としてみてください。
	 */
	public static Block blockAmethyst;
	public static int blockAmethystID = 508;

	public static Block oreAmethyst;
	public static int oreAmethystID = 500;
	
	
	/**
	 * ItemのIDは4096以上にしましょう。
	 * 0～4095はForgeによってブロックのために使われている領域です。
	 * 沢山のMODを入れているユーザーさんにとってはブロックID被りの防止は頭を悩ませる問題であり、
	 * ただでさえ悩ましいID競合の種を増やすメリットはありません。
	 * アイテムは4096～30000くらいまでの広い範囲を使えます。
	 * 今回は5000番台を使用するようにしてみます。
	 */
	public static Item Amethyst;
	public static int AmethystID = 5000;
	
	/**
	 * いずれ必要になるでしょうから、ツール属性も増やしときます。
	 * バニラだとピッケル等の属性は木、石、鉄、金、ダイヤですが、これに新属性を足します。
	 * ここでは宣言だけで、内容は後で登録します。
	 */
	public static EnumToolMaterial enumToolMaterialAmethyst;
		
	/**
	 * preInit
	 * コンフィグ登録・クリエイティブ作成・ブロックとアイテムの登録、効果音登録を含めるべきメソッドです。
	 * メソッドというのは要するに、「public void うにゃら(){}」みたいな形式のものの呼称です。
	 * 何処かから呼び出された時、このクラス内、又はメソッドの()内に、例えば(int par1)とあれば、
	 * {}の中で「par1」というint変数を使って何かが出来ます。
	 * ここの場合は、@EventHandlerが付いていますから、
	 * Forgeの内部処理によって、マイクラを起動した直後に呼び出されます。
	 * （@EventHandlerというアノテーション自体がイベントを呼ぶ印のようなものです。）
	 * mcp/jarsファイル内に「ForgeModLoader-client-0.log」というテキストファイルが出てきており、
	 * 起動時のログはすべてここに出てきますから、どんな流れでForgeが起動しているのか一度読んでみるのを勧めます。
	 * 
	 * ちなみに、
	 * System.out.println("好きなテキスト");
	 * と書いておくことで、そのテキストファイルや、エクリプスのコンソールにテキストを出すことが出来ます。
	 * 開発段階ですし、やりたいことの成否判定を逐一テキストで出しておくのも、バグ探しに役に立つと思います。
	 * 今回は、時々テキストを吐くようにしておきますので、
	 * 適当に付け足したり、削ったりして下さいね。
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		/**
		 * 確か1.6.X以降でしょうか、
		 * 1.5の頃は下記のブロック登録部分は「init」メソッドで行っていたのですが、
		 * 今は「preInit」メソッドでの登録が推奨されているようです。
		 * まだinitの方で登録されているMODさんも若干見受けられますが、
		 * タイミングが遅い程、レシピ登録や鉱石辞書登録に影響するので、まぁ遅くともpostInit時には終わっている方が良いでしょう。
		 * 
		 * あと、このままだとコンパイルが通らずこちらで起動確認が出来ないので、
		 * 勝手に各ブロックのクラスを作らせて頂きます。
		 * 適宜差し替えて使って下さい。
		 * 
		 * 余談：これは起動には関係のないことですが、
		 * クラス名は大文字で始めたほうがいいです。
		 * こちらで勝手に大文字にしました。
		 * 
		 * 余談2：別に、Material.ironはここで指定しなくても良かったりします。
		 * AmethystBlockの中身を見てみると判りますが、
		 * super(blockID, Material)の部分で指定すれば大丈夫です。
		 * そちらの方法はoreAmethystの方で例示します。
		 */
		blockAmethyst = new AmethystBlock(blockAmethystID, Material.iron).
				setCreativeTab(mineores).setUnlocalizedName("blockAmethyst");
	    GameRegistry.registerBlock(blockAmethyst, "blockAmethyst");
	    
	    /**
		 * アイテムと鉱石のほうは、ここではなく「RegisterOres」という別のクラスの中で登録してみます。
		 * 注意として、引数(()のこと)を忘れないこと、
		 * importして使うこと、
		 * クラスを分けても、呼び出されるタイミングは適切であることです。
		 * 
		 * ですから、
		 * GameResistry.registerBlock(instance, name)を登録する「registerBlocks()」メソッドは、
		 * preInitの｛｝の中で呼ばなければエラーになってしまいます。。
		 */
	    (new RegisterOres()).registerBlocks();
	    
	    (new RegisterOres()).registerMaterials();
	    
	    /**
		 * ツール属性の登録もpreInit内です。
		 * 順に、名前、壊せる範囲（2だと鉄と同じで、黒曜石以外は破壊できます。）、耐久度、破壊の早さ、
		 * MOBを殴った時のダメージ、良いエンチャントの出やすさです。
		 * EnumToolMaterial.classにバニラのツールの数値があるので、それを見つつバランスを調整すると良いと思います。
		 */
	  enumToolMaterialAmethyst = EnumHelper.addToolMaterial("AMETHYST", 2, 128, 5.0F, 2.0F, 15);
	  
	  //登録したツール属性の名前をチェックしています。成否判定用です。
	  System.out.println("[Mine Ores] registered tool material : "
	  		+ this.enumToolMaterialAmethyst.name());
	}
	
	/**
	 * Initメソッドでは主にレシピ登録、言語登録等を行います。
	 * これらは先にブロックやアイテムの登録が済んでいないとおかしくなります。
	 * 他にもイベント、レンダー、エンティティ等もろもろの登録がここに詰め込まれます。
	 */
	@EventHandler
	public void init(FMLInitializationEvent event)
	{

		
		/**
		 * 勝手ながらWorldgenクラスも作らせて頂きます。
		 * コンパイルが通りませんので。
		 */
	    GameRegistry.registerWorldGenerator(new WorldGeneration());
	    
	    /**
		 * 言語登録の部分をLangRegisteringクラスに持って行きました。
		 */
	    (new LangRegister()).lang();
	    
	    
	    /**
		 * オマケでOreDictionaryへの登録です。
		 * 特に鉱石追加MODの場合、これに登録するか否かでMODの使い勝手が格段に変わるので、
		 * ぜひ登録したいところです。
		 * 
		 * 鉱石は「ore～」、インゴットは「ingot～」、宝石は「gem～」という名前で統一します。
		 * この命名法を使えば、ForestryやRailCraft等の大型MOD側で自動的に名前をサーチして、
		 * バックパックや採掘トロッコの機能に登録して貰えたりします。
		 */
	    OreDictionary.registerOre("oreAmethyst", new ItemStack(this.oreAmethyst));
	    OreDictionary.registerOre("gemAmethyst", new ItemStack(this.Amethyst));

	}

	/**
	 * ちなみに、何もすることがないのであれば、postInit以降のメソッド自体を省略しても問題ありません。
	 */

}
