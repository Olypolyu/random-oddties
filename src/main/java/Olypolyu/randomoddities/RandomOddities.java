package Olypolyu.randomoddities;

import Olypolyu.randomoddities.entities.EntityBoar;
import Olypolyu.randomoddities.entities.TileEntityLauncher;
import Olypolyu.randomoddities.entities.TileEntityResizableChest;
import net.fabricmc.api.ModInitializer;
import net.minecraft.src.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockHelper;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.RecipeHelper;
import turniplabs.halplibe.helper.TextureHelper;
import turniplabs.halplibe.mixin.helper.CraftingManagerInterface;
import turniplabs.halplibe.mixin.helper.TileEntityInterface;

public class RandomOddities implements ModInitializer {

    public static final String MOD_ID = "RandomOddities";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    public static String name(String name) {
        return RandomOddities.MOD_ID + "." + name;
    }

    // blocks
    static int RandomOdditiesIds = 700;

    public static final Block FlintBlock = BlockHelper.createBlock(
            new Block(RandomOdditiesIds + 1, Material.rock),
            name("FlintBlock"),
            31,14,
            Block.soundStoneFootstep,
            3f,
            2f,
            0f);

    public static final Block Trampoline =  BlockHelper.createBlock(
            new BlockEntityLauncher(RandomOdditiesIds + 2,
            Material.iron,
            0,1.25,0),
            name("Trampoline"),
            31, 15, 31, 17, 31, 16,
            Block.soundMetalFootstep,
            2.5f,
            5f,
            0);

    public static final Block Pillow = BlockHelper.createBlock(
            new BlockEntityLauncher(RandomOdditiesIds + 3,
            Material.cloth,
            0,0,0),
            name("Pillow"),
            31, 21, 31, 21, 31, 22,
            Block.soundClothFootstep,
            1F,
            1f,
            0f);

    public static final Block ObsidianChest = BlockHelper.createBlock(
            new BlockResizableChest(RandomOddities.RandomOdditiesIds + 4,
            Material.iron,
            108,31, 18, 31, 20, 31, 19),
            name("ObsidianChest"),
            420,69,
            Block.soundMetalFootstep,
            15.0F,
            150.0F,
            0F);

    public static final Block IronChest = BlockHelper.createBlock(
            new BlockResizableChest(RandomOddities.RandomOdditiesIds + 5,
            Material.iron,
            45,31, 23, 31, 25, 31, 24),
            name("IronChest"),
            420,69,
            Block.soundMetalFootstep,
            5F,
            2.5F,
            0F);

    public static final Block PumpkinPie = BlockHelper.createBlock(
            new BlockPumpkinPie(RandomOdditiesIds + 6),
            name("PumpkinPie"),
            31,26,31,27,31,28,
            Block.soundClothFootstep,
            1F,
            2f,
            0f);

    // items
    public static final Item ItemPumpkinPie = new ItemSugarcane(RandomOdditiesIds + 7,PumpkinPie).setIconCoord(13, 3).setItemName(name("PumpkinPie"));

    // paint brushes
    public static final Item ItemPaintBrush = new ItemPaintBrush(RandomOdditiesIds + 8).setIconCoord(16, 0).setItemName(name("PaintBrush"));

    private ItemStack painBrush( int color) {
        ItemStack paintBrush = new ItemStack(ItemPaintBrush, 1, 0);
        paintBrush.tag.setInteger("color", color);

        return paintBrush;
    }

    public void onInitialize() {

        RandomOddities.ItemPumpkinPie.setMaxStackSize(1);
        RandomOddities.PumpkinPie.notInCreativeMenu = true;

        LOGGER.info("RandomOddities initialized.");

        // add in entities
        EntityHelper.createEntity(EntityBoar.class, new RenderLiving(new ModelQuadruped(6, 0), 0.5f), 61, "Boar");

        TileEntityInterface.callAddMapping(TileEntityLauncher.class, "TileEntityLauncher");
        TileEntityInterface.callAddMapping(TileEntityResizableChest.class, "TileEntityResizableChest");

        // load textures
        TextureHelper.addTextureToTerrain(MOD_ID, "FlintBlock.png", 31, 14);
        TextureHelper.addTextureToTerrain(MOD_ID, "TrampolineTop.png", 31, 15);
        TextureHelper.addTextureToTerrain(MOD_ID, "TrampolineSides.png", 31, 16);
        TextureHelper.addTextureToTerrain(MOD_ID, "TrampolineBottom.png", 31, 17);

        TextureHelper.addTextureToTerrain(MOD_ID, "obsidianChestTop.png", 31, 18);
        TextureHelper.addTextureToTerrain(MOD_ID, "obsidianChestFront.png", 31, 19);
        TextureHelper.addTextureToTerrain(MOD_ID, "obsidianChestSides.png", 31, 20);

        TextureHelper.addTextureToTerrain(MOD_ID, "pillowTop.png", 31, 21);
        TextureHelper.addTextureToTerrain(MOD_ID, "pillowSides.png", 31, 22);

        TextureHelper.addTextureToTerrain(MOD_ID, "ironChestTop.png", 31, 23);
        TextureHelper.addTextureToTerrain(MOD_ID, "ironChestFront.png", 31, 24);
        TextureHelper.addTextureToTerrain(MOD_ID, "ironChestSides.png", 31, 25);

        TextureHelper.addTextureToTerrain(MOD_ID, "pumpkinPieTop.png", 31, 26);
        TextureHelper.addTextureToTerrain(MOD_ID, "pumpkinPieBottom.png", 31, 27);
        TextureHelper.addTextureToTerrain(MOD_ID, "pumpkinPieSides.png", 31, 28);
        TextureHelper.addTextureToTerrain(MOD_ID, "pumpkinPieEaten.png", 31, 29);

        TextureHelper.addTextureToItems(MOD_ID, "paintBrush.png", 16,0);
        TextureHelper.addTextureToItems(MOD_ID, "orangePaintBrush.png", 16,1);
        TextureHelper.addTextureToItems(MOD_ID, "magentaPaintBrush.png", 16, 2);
        TextureHelper.addTextureToItems(MOD_ID, "lightBluePaintBrush.png", 16,3);
        TextureHelper.addTextureToItems(MOD_ID, "yellowPaintBrush.png", 16,4);
        TextureHelper.addTextureToItems(MOD_ID, "limePaintBrush.png", 16,5);
        TextureHelper.addTextureToItems(MOD_ID, "pinkPaintBrush.png", 16,6);
        TextureHelper.addTextureToItems(MOD_ID, "grayPaintBrush.png", 16,7);
        TextureHelper.addTextureToItems(MOD_ID, "lightGrayPaintBrush.png", 16,8);
        TextureHelper.addTextureToItems(MOD_ID, "cyanPaintBrush.png", 16,9);
        TextureHelper.addTextureToItems(MOD_ID, "purplePaintBrush.png", 16,10);
        TextureHelper.addTextureToItems(MOD_ID, "bluePaintBrush.png", 16,11);
        TextureHelper.addTextureToItems(MOD_ID, "brownPaintBrush.png", 16,12);
        TextureHelper.addTextureToItems(MOD_ID, "greenPaintBrush.png", 16,13);
        TextureHelper.addTextureToItems(MOD_ID, "redPaintBrush.png", 16,14);
        TextureHelper.addTextureToItems(MOD_ID, "blackPaintBrush.png", 16,15);

        // load crafting recipes
        RecipeHelper.Crafting.createRecipe(FlintBlock, 1, new Object[]{"FF", "FF", 'F', Item.flint}); // flint to flint block
        RecipeHelper.Crafting.createShapelessRecipe(Item.flint, 4, new Object[]{new ItemStack(FlintBlock, 1)}); // flint block to flint

        RecipeHelper.Crafting.createRecipe(Trampoline, 1, new Object[]{"LFL", "IFI", 'F', Item.featherChicken, 'I', Item.ingotIron, 'L', new ItemStack(Item.dye, 1, 4)}); // Trampoline
        RecipeHelper.Crafting.createRecipe(Pillow, 4, new Object[]{"WWW", "FFF", 'W', Block.wool, 'F', Item.featherChicken}); //pillow

        RecipeHelper.Crafting.createRecipe(IronChest, 1, new Object[]{"ISI", "SCS", "ISI", 'I', new ItemStack(Block.blockIron, 1), 'S', Item.ingotSteelCrude, 'C', new ItemStack(Block.chestPlanksOak, 1)}); // Iron Chest
        RecipeHelper.Crafting.createRecipe(ObsidianChest, 1, new Object[]{"ODO", "DCD", "ODO", 'O', new ItemStack(Block.obsidian, 1), 'D', Item.diamond, 'C', new ItemStack(IronChest, 1)}); // Obsidian Chest

        RecipeHelper.Crafting.createRecipe(ItemPumpkinPie, 1, new Object[]{"EPE", "WMW", 'P', new ItemStack(Block.pumpkin, 1), 'E', Item.eggChicken, 'W', Item.wheat, 'M', Item.bucketMilk});  // Pumpkin Pie


        int craftPainBrushes;
        for ( craftPainBrushes = 0; craftPainBrushes <= 15; craftPainBrushes++) {

            ((CraftingManagerInterface)RecipeHelper.craftingManager).callAddRecipe(
                    painBrush(craftPainBrushes),
                    new Object[]{
                            " C",
                            "SD",

                            'D', new ItemStack(Item.dye, 1, 15 - craftPainBrushes),
                            'C', Item.cloth,
                            'S', Item.stick});
        }

    }

}