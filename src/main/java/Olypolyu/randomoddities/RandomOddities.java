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
import turniplabs.halplibe.mixin.helper.TileEntityInterface;


public class RandomOddities implements ModInitializer {

    public static final String MOD_ID = "RandomOddities";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static String name(String name) {
        return RandomOddities.MOD_ID + "." + name;
    }

    // blocks
    static int RandomOdditiesIds = 700;

    public static final Block RandomOdditiesFlintBlock = BlockHelper.createBlock(
            new Block(RandomOdditiesIds + 1, Material.rock),
            name("FlintBlock"),
            31,14,
            Block.soundStoneFootstep,
            3f,
            2f,
            0f);

    public static final Block RandomOdditiesTrampoline =  BlockHelper.createBlock(
            new BlockEntityLauncher(RandomOdditiesIds + 2,
            Material.iron,
            0,1.25,0),
            name("Trampoline"),
            31, 15, 31, 17, 31, 16,
            Block.soundMetalFootstep,
            2.5f,
            5f,
            0);

    public static final Block RandomOdditiesPillow = BlockHelper.createBlock(
            new BlockEntityLauncher(RandomOdditiesIds + 3,
            Material.cloth,
            0,0,0),
            name("Pillow"),
            31, 21, 31, 21, 31, 22,
            Block.soundClothFootstep,
            1F,
            1f,
            0f);

    public static final Block RandomOdditiesObsidianChest = BlockHelper.createBlock(
            new BlockResizableChest(RandomOddities.RandomOdditiesIds + 4,
            Material.iron,
            108,31, 18, 31, 20, 31, 19),
            name("ObsidianChest"),
            420,69,
            Block.soundMetalFootstep,
            5.0F,
            150.0F,
            0F);

    public static final Block RandomOdditiesIronChest = BlockHelper.createBlock(
            new BlockResizableChest(RandomOddities.RandomOdditiesIds + 5,
            Material.iron,
            45,31, 23, 31, 25, 31, 24),
            name("IronChest"),
            420,69,
            Block.soundMetalFootstep,
            2.5F,
            2.5F,
            0F);

    public void onInitialize() {
        LOGGER.info("RandomOddities initialized.");

        // items
       // Item.itemsList[RandomOdditiesIronChest.blockID] = new ItemBlock(RandomOdditiesIronChest.blockID - Block.blocksList.length);
        // Item.itemsList[RandomOdditiesObsidianChest.blockID] = new ItemBlock(RandomOdditiesObsidianChest.blockID - Block.blocksList.length);

        // add in entities
        EntityHelper.createEntity(EntityBoar.class, new RenderLiving(new ModelQuadruped(6, 0), 0.5f), 61, "Boar");
        TileEntityInterface.callAddMapping(TileEntityLauncher.class,"TileEntityLauncher");
        TileEntityInterface.callAddMapping(TileEntityResizableChest.class,"TileEntityResizableChest");

        // load textures
        TextureHelper.addTextureToTerrain(MOD_ID, "FlintBlock.png",31,14);
        TextureHelper.addTextureToTerrain(MOD_ID, "TrampolineTop.png",31,15);
        TextureHelper.addTextureToTerrain(MOD_ID, "TrampolineSides.png",31,16);
        TextureHelper.addTextureToTerrain(MOD_ID, "TrampolineBottom.png",31,17);

        TextureHelper.addTextureToTerrain(MOD_ID, "obsidianChestTop.png",31,18);
        TextureHelper.addTextureToTerrain(MOD_ID, "obsidianChestFront.png",31,19);
        TextureHelper.addTextureToTerrain(MOD_ID, "obsidianChestSides.png",31,20);

        TextureHelper.addTextureToTerrain(MOD_ID, "pillowTop.png",31,21);
        TextureHelper.addTextureToTerrain(MOD_ID, "pillowSides.png",31,22);

        TextureHelper.addTextureToTerrain(MOD_ID, "ironChestTop.png",31,23);
        TextureHelper.addTextureToTerrain(MOD_ID, "ironChestFront.png",31,24);
        TextureHelper.addTextureToTerrain(MOD_ID, "ironChestSides.png",31,25);

        // load crafting recipes
        RecipeHelper.Crafting.createRecipe(RandomOdditiesFlintBlock, 1, new Object[]{"FF", "FF", 'F', Item.flint}); // flint to flint block
        RecipeHelper.Crafting.createShapelessRecipe(Item.flint, 4, new Object[]{new ItemStack(RandomOdditiesFlintBlock, 1 )}); // flint block to flint

        RecipeHelper.Crafting.createRecipe(RandomOdditiesTrampoline, 1, new Object[]{"LFL", "IFI", 'F', Item.featherChicken, 'I', Item.ingotIron, 'L', new ItemStack(Item.dye, 1, 4)}); // Trampoline
        RecipeHelper.Crafting.createRecipe(RandomOdditiesPillow, 4, new Object[]{"WWW", "FFF", 'W', Block.wool, 'F', Item.featherChicken}); //pillow

        RecipeHelper.Crafting.createRecipe(RandomOdditiesIronChest, 1, new Object[]{"ISI", "SCS", "ISI", 'I', new ItemStack(Block.blockIron, 1), 'S', Item.ingotSteelCrude, 'C', new ItemStack(Block.chestPlanksOak, 1)}); // Iron Chest
        RecipeHelper.Crafting.createRecipe(RandomOdditiesObsidianChest, 1, new Object[]{"ODO", "DCD", "ODO", 'O', new ItemStack(Block.obsidian, 1), 'D', Item.diamond, 'C', new ItemStack(RandomOdditiesIronChest, 1)}); // Obsidian Chest

    }

}