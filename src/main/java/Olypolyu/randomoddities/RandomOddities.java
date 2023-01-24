package Olypolyu.randomoddities;

import Olypolyu.randomoddities.block.*;
import Olypolyu.randomoddities.entity.EntityBoar;
import Olypolyu.randomoddities.entity.TileEntityBubbleColumn;
import Olypolyu.randomoddities.entity.TileEntityLauncher;
import Olypolyu.randomoddities.entity.TileEntityResizableChest;
import Olypolyu.randomoddities.item.*;
import Olypolyu.randomoddities.mixin.ReparableRecipeMixin;
import net.fabricmc.api.ModInitializer;
import net.minecraft.src.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.*;
import turniplabs.halplibe.mixin.accessors.BlockAccessor;
import turniplabs.halplibe.mixin.accessors.CraftingManagerAccessor;
import turniplabs.halplibe.mixin.accessors.TileEntityAccessor;

import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

import static net.minecraft.src.Block.blocksList;

public class RandomOddities implements ModInitializer {

    private final Random random = new Random();

    public static final String MOD_ID = "randomoddities";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static void info(Object obj) { LOGGER.info(String.valueOf(obj)); }
    public static void warn(Object obj) { LOGGER.warn(String.valueOf(obj)); }


    public static String name(String name) {
        return RandomOddities.MOD_ID + "." + name;
    }

    // blocks
    static int randomOdditiesIds = 700;

    public static final Block flintBlock = BlockHelper.createBlock( MOD_ID,
            new Block(randomOdditiesIds + 1, Material.rock),
            "FlintBlock",
            "FlintBlock.png",
            Block.soundStoneFootstep,
            3f,
            2f,
            0f);

    public static final Block trampoline =  BlockHelper.createBlock( MOD_ID,
            new BlockEntityLauncher(randomOdditiesIds + 2,
            Material.iron,
            0,1.25,0),
            "Trampoline",
            "TrampolineTop.png", "TrampolineBottom.png","TrampolineSides.png",
            Block.soundMetalFootstep,
            2.5f,
            5f,
            0);

    public static final Block pillow = BlockHelper.createBlock( MOD_ID,
            new BlockEntityLauncher(randomOdditiesIds + 3,
            Material.cloth,
            0,0,0),
            "Pillow",
            "pillowTop.png", "pillowSides.png",
            Block.soundClothFootstep,
            1F,
            1f,
            0f);

    public static final Block obsidianChest = BlockHelper.createBlock( MOD_ID,
            new BlockResizableChest(RandomOddities.randomOdditiesIds + 4,
            Material.iron,
            108,31, 18, 31, 20, 31, 19),
            "ObsidianChest",
            "FlintBlock.png",
            Block.soundMetalFootstep,
            15.0F,
            150.0F,
            0F);

    public static final Block ironChest = BlockHelper.createBlock( MOD_ID,
            new BlockResizableChest(RandomOddities.randomOdditiesIds + 5,
            Material.iron,
            45,31, 23, 31, 25, 31, 24),
            "IronChest",
            "FlintBlock.png",
            Block.soundMetalFootstep,
            5F,
            2.5F,
            0F);

    public static final Block pumpkinPie = BlockHelper.createBlock( MOD_ID,
            new BlockPumpkinPie(randomOdditiesIds + 6),
            "PumpkinPie",
            "pumpkinPieTop.png", "pumpkinPieBottom.png", "pumpkinPieSides.png",
            Block.soundClothFootstep,
            1F,
            2f,
            0f);

    public static final Block bubbleGenerator = BlockHelper.createBlock( MOD_ID,
            new BlockBubbleGenerator(randomOdditiesIds + 7, Material.iron),
            "BubbleGenerator",
            "bubbleTop.png", "TrampolineBottom.png", "bubbleSide.png",
            Block.soundStoneFootstep,
            2.5f,
            5f,
            0);

    public static final Block platform = BlockHelper.createBlock( MOD_ID,
            new BlockPlatform(randomOdditiesIds + 8, Material.iron),
            "Platform",
            "FlintBlock.png",
            Block.soundMetalFootstep,
            2.5f,
            5f,
            0);

    public static final Block fishTrap = BlockHelper.createBlock( MOD_ID,
            new BlockFishTrap(randomOdditiesIds + 9, Material.rock),
            "FishTrap",
            "FishTrap.png",
            Block.soundStoneFootstep,
            2.5f,
            5f,
            0);

    public static final Block cocoaBeans = BlockHelper.createBlock( MOD_ID,
            new BlockCocoaBeans(randomOdditiesIds + 12, Material.plants),
            "CocoaBeans",
            "FlintBlock.png",
            Block.soundGrassFootstep,
            0.3F,
            0.0F,
            0);

    public static final Block paintedGlass = new BlockPaintedGlass(randomOdditiesIds + 13, Material.glass, false)
            .setBlockName("PaintedGlass")
            .setTexCoords(1, 3)
            .setNotInCreativeMenu();

    static {
        ((BlockAccessor) paintedGlass).callSetHardness(0.3F);
        ((BlockAccessor) paintedGlass).callSetStepSound(Block.soundGlassFootstep);
    }

     public static final Block fireStriker = BlockHelper.createBlock( MOD_ID,
            new BlockFireStriker(randomOdditiesIds + 14, Material.rock),
            "FireStriker",
             "FlintBlock.png",
            Block.soundStoneFootstep,
            3f,
            2f,
            0f);

    public static final Block unLoaderRail = BlockHelper.createBlock( MOD_ID,
            new BlockMinecartUnloaderRail(randomOdditiesIds + 15, true),
            "UnLoaderRail",
            "minecartUnloader.png",
            Block.soundStoneFootstep,
            3f,
            2f,
            0f);

    public static final Block loaderRail = BlockHelper.createBlock( MOD_ID,
            new BlockMinecartLoaderRail(randomOdditiesIds + 16, true),
            "LoaderRail",
            "minecartLoader.png",
            Block.soundStoneFootstep,
            3f,
            2f,
            0f);


    // items
    public static final Item itemPumpkinPie = new ItemSugarcane(randomOdditiesIds + 1, pumpkinPie).setIconCoord(13, 3).setItemName(name("food.pumpkin_pie"));

    // paint brushes
    public static final Item whitePaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 2,0),"paint_brush.white","paintBrush/white.png");
    public static final Item orangePaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 3,1),"paint_brush.orange","paintBrush/orange.png");
    public static final Item magentaPaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 4,2),"paint_brush.magenta","paintBrush/magenta.png");
    public static final Item lightBluePaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 5,3),"paint_brush.lightblue","paintBrush/lightblue.png");
    public static final Item yellowPaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 6,4),"paint_brush.yellow","paintBrush/yellow.png");
    public static final Item limePaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 7,5),"paint_brush.lime","paintBrush/lime.png");
    public static final Item pinkPaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 8,6),"paint_brush.pink","paintBrush/pink.png");
    public static final Item grayPaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 9,7),"paint_brush.gray","paintBrush/gray.png");
    public static final Item silverPaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 10,8),"paint_brush.silver","paintBrush/silver.png");
    public static final Item cyanPaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 11,9),"paint_brush.cyan","paintBrush/cyan.png");
    public static final Item purplePaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 12,10),"paint_brush.purple","paintBrush/purple.png");
    public static final Item bluePaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 13,11),"paint_brush.blue","paintBrush/blue.png");
    public static final Item brownPaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 14,12),"paint_brush.brown","paintBrush/brown.png");
    public static final Item greenPaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 15,13),"paint_brush.green","paintBrush/green.png");
    public static final Item redPaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 16,14),"paint_brush.red","paintBrush/red.png");
    public static final Item blackPaintBrush = ItemHelper.createItem( MOD_ID, new ItemPaintBrush(randomOdditiesIds + 17,15),"paint_brush.black","paintBrush/black.png");

    private final Item[] PaintBrushColors = { whitePaintBrush, orangePaintBrush, magentaPaintBrush, lightBluePaintBrush, yellowPaintBrush, limePaintBrush, pinkPaintBrush, grayPaintBrush, silverPaintBrush,
            cyanPaintBrush, purplePaintBrush, bluePaintBrush, brownPaintBrush, greenPaintBrush, redPaintBrush, blackPaintBrush };

    public static final Item paintScrapper = new ItemPaintScrapper(randomOdditiesIds + 18).setIconCoord(16, 16).setItemName(name("paintScrapper"));

    // Spawn eggs
    public static final Item pigSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 19, EntityPig.class).setIconCoord(17, 2).setItemName(name("pigSpawnEgg"));
    public static final Item cowSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 20, EntityCow.class).setIconCoord(17, 3).setItemName(name("cowSpawnEgg"));
    public static final Item chickenSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 21, EntityChicken.class).setIconCoord(17, 4).setItemName(name("chickenSpawnEgg"));
    public static final Item sheepSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 22, EntitySheep.class).setIconCoord(17, 5).setItemName(name("sheepSpawnEgg"));
    public static final Item squidSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 23, EntitySquid.class).setIconCoord(17, 6).setItemName(name("squidSpawnEgg"));
    public static final Item wolfSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 24, EntityWolf.class).setIconCoord(17, 7).setItemName(name("wolfSpawnEgg"));
    public static final Item zombieSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 25, EntityZombie.class).setIconCoord(17, 8).setItemName(name("zombieSpawnEgg"));
    public static final Item creeperSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 26, EntityCreeper.class).setIconCoord(17, 9).setItemName(name("creeperSpawnEgg"));
    public static final Item skeletonSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 27, EntitySkeleton.class).setIconCoord(17, 10).setItemName(name("skeletonSpawnEgg"));
    public static final Item spiderSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 28, EntitySpider.class).setIconCoord(17, 11).setItemName(name("spiderSpawnEgg"));
    public static final Item slimeSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 29, EntitySlime.class).setIconCoord(17, 12).setItemName(name("slimeSpawnEgg"));
    public static final Item zombiePigmanSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 30, EntityPigZombie.class).setIconCoord(17, 13).setItemName(name("zombiePigmanSpawnEgg"));
    public static final Item ghastSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 31, EntityGhast.class).setIconCoord(17, 14).setItemName(name("ghastSpawnEgg"));
    public static final Item giantZombieSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 32, EntityGiantZombie.class).setIconCoord(17, 15).setItemName(name("giantZombieSpawnEgg"));
    public static final Item armouredZombieSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 33, EntityArmouredZombie.class).setIconCoord(17, 16).setItemName(name("armouredZombieSpawnEgg"));
    public static final Item humanSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 34, EntityMob.class).setIconCoord(17, 17).setItemName(name("humanSpawnEgg"));
    public static final Item boarSpawnEgg = new ItemSpawnEgg(randomOdditiesIds + 35, EntityBoar.class).setIconCoord(17, 18).setItemName(name("boarSpawnEgg"));

    // wind bottle
    private static final int windLampCharges = 3;
    public static final Item windLamp = new ItemWindLamp(randomOdditiesIds + 36, windLampCharges).setItemName(name("windLamp"));

    public static final Item glider = new ItemTestGlider(randomOdditiesIds + 37).setItemName(name("glider"));


    public void onInitialize() {
        LOGGER.info("RandomOddities initialized.");

        // items
        RandomOddities.itemPumpkinPie.setMaxStackSize(1);
        RandomOddities.pumpkinPie.notInCreativeMenu = true;

        Item.itemsList[paintedGlass.blockID] = new ItemBlockPainted(paintedGlass.blockID - blocksList.length, true);

        // hehe... it's buggy
        RandomOddities.platform.notInCreativeMenu = true;
        RandomOddities.glider.notInCreativeMenu = true;

        // add in entities
        EntityHelper.createEntity(EntityBoar.class, new RenderLiving(new ModelQuadruped(6, 0), 0.5f), 61, "Boar");

        TileEntityAccessor.callAddMapping(TileEntityLauncher.class, "TileEntityLauncher");
        TileEntityAccessor.callAddMapping(TileEntityResizableChest.class, "TileEntityResizableChest");
        TileEntityAccessor.callAddMapping(TileEntityBubbleColumn.class, "TileEntityBubbleColumn");

        // load textures
        TextureHelper.addTextureToTerrain(MOD_ID, "obsidianChestTop.png", 31, 18);
        TextureHelper.addTextureToTerrain(MOD_ID, "obsidianChestFront.png", 31, 19);
        TextureHelper.addTextureToTerrain(MOD_ID, "obsidianChestSides.png", 31, 20);

        TextureHelper.addTextureToTerrain(MOD_ID, "ironChestTop.png", 31, 23);
        TextureHelper.addTextureToTerrain(MOD_ID, "ironChestFront.png", 31, 24);
        TextureHelper.addTextureToTerrain(MOD_ID, "ironChestSides.png", 31, 25);

        TextureHelper.addTextureToTerrain(MOD_ID, "pumpkinPieEaten.png", 31, 29);

        TextureHelper.addTextureToTerrain(MOD_ID, "FishTrapEngaged.png", 30,31);
        TextureHelper.addTextureToTerrain(MOD_ID, "FishTrap.png", 30,30);
        TextureHelper.addTextureToTerrain(MOD_ID, "FishTrapFull.png", 30,29);

        TextureHelper.addTextureToTerrain(MOD_ID, "fireman.png", 30,28);

        TextureHelper.addTextureToTerrain(MOD_ID, "vines.png", 30,27);
        TextureHelper.addTextureToTerrain(MOD_ID, "vines1.png", 30,26);
        TextureHelper.addTextureToTerrain(MOD_ID, "vines2.png", 30,25);
        TextureHelper.addTextureToTerrain(MOD_ID, "vines3.png", 30,24);
        TextureHelper.addTextureToTerrain(MOD_ID, "vines4.png", 30,23);

        // Item textures
        TextureHelper.addTextureToItems(MOD_ID, "paintBrush/paint_scrapper.png", 16,16);

        TextureHelper.addTextureToItems(MOD_ID, "emptyWindBottle.png", 17,0);
        TextureHelper.addTextureToItems(MOD_ID, "windBottle.png", 17,1);

        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/pig.png", 17,2);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/cow.png", 17,3);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/chicken.png", 17,4);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/sheep.png", 17,5);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/squid.png", 17,6);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/wolf.png", 17,7);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/zombie.png", 17,8);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/creeper.png", 17,9);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/skeleton.png", 17,10);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/spider.png", 17,11); // spoider
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/slime.png", 17,12);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/zombiePigman.png", 17,13);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/ghast.png", 17,14);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/giant.png", 17,15);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/armouredZombie.png", 17,16);
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/monster.png", 17,17); // hooman
        TextureHelper.addTextureToItems(MOD_ID, "spawnEggs/boar.png", 17,18);

        // load crafting recipes
        RecipeHelper.Crafting.createRecipe(flintBlock, 1, new Object[]{"FF", "FF", 'F', Item.flint}); // flint to flint block
        RecipeHelper.Crafting.createShapelessRecipe(Item.flint, 4, new Object[]{new ItemStack(flintBlock, 1)}); // flint block to flint

        RecipeHelper.Crafting.createRecipe(trampoline, 1, new Object[]{"LFL", "IFI", 'F', Item.featherChicken, 'I', Item.ingotIron, 'L', new ItemStack(Item.dye, 1, 4)}); // Trampoline
        RecipeHelper.Crafting.createRecipe(pillow, 4, new Object[]{"WWW", "FFF", 'W', Block.wool, 'F', Item.featherChicken}); //pillow

        RecipeHelper.Crafting.createRecipe(ironChest, 1, new Object[]{"ISI", "SCS", "ISI", 'I', new ItemStack(Block.blockIron, 1), 'S', Item.ingotSteelCrude, 'C', new ItemStack(Block.chestPlanksOak, 1)}); // Iron Chest
        RecipeHelper.Crafting.createRecipe(obsidianChest, 1, new Object[]{"ODO", "DCD", "ODO", 'O', new ItemStack(Block.obsidian, 1), 'D', Item.diamond, 'C', new ItemStack(ironChest, 1)}); // Obsidian Chest

        RecipeHelper.Crafting.createRecipe(itemPumpkinPie, 1, new Object[]{"EPE", "WMW", 'P', new ItemStack(Block.pumpkin, 1), 'E', Item.eggChicken, 'W', Item.wheat, 'M', Item.bucketMilk});  // Pumpkin Pie

        RecipeHelper.Crafting.createRecipe(bubbleGenerator, 1, new Object[]{"IFI", "IWI", "BFB", 'B', new ItemStack(Item.dye, 1, 4), 'I', Item.ingotIron, 'F',  Item.featherChicken, 'W', new ItemStack(Block.mesh, 1)}); // Bubble generator

        RecipeHelper.Crafting.createRecipe(fishTrap, 1, new Object[]{" A ", "AMA", " A ", 'A', Block.algae, 'M', Block.mesh }); // Fish trap

        RecipeHelper.Crafting.createRecipe(fireStriker, 1, new Object[]{"CCC", "CSC", "CRC", 'C', Block.cobbleStone, 'S', Item.toolFirestriker, 'R', Item.dustRedstone }); // Fire striker block

        RecipeHelper.Crafting.createRecipe(paintScrapper, 1, new Object[]{" I", "S ", 'S', Item.stick, 'I', Item.ingotIron}); // Paint Scrapper

        // create crafting recipes for all paint brushes, you are sorely mistaken if you think im going to add another 16 lines for this.
        int Color;
        for ( Color = 0; Color <= 15; Color++)
            RecipeHelper.Crafting.createRecipe(PaintBrushColors[Color], 1, new Object[]{" C", "SD", 'D', new ItemStack(Item.dye, 1, 15 - Color), 'C', Item.cloth, 'S', Item.stick});

        // creates crafting recipes for all painted glass.
        for ( Color = 1; Color <= 15; Color++)
            ((CraftingManagerAccessor) RecipeHelper.craftingManager).callAddShapelessRecipe(new ItemStack(paintedGlass, 1, Color), new Object[]{Block.glass, new ItemStack(Item.dye, 1, 15 - Color)});

        ((ReparableRecipeMixin)RecipeHelper.craftingManager).callAddRepairableStackableRecipe(windLamp,  new ItemStack(Item.featherChicken)); // Wind Bottle

        // April fools thing
        if ( LocalDate.now().getMonth() == Month.APRIL && LocalDate.now().getDayOfMonth() == 1 ) {

            // windows joke, this joke is certainly funnier to me than it is to you.
            if ( System.getProperty("os.name").contains("Windows") && random.nextInt(11) == 0 )
                throw new RuntimeException("Unbased variable reference. Please check your OS and try again.");
        }

    }

}