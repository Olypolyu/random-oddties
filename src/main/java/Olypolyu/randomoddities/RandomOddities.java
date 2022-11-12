package Olypolyu.randomoddities;

import Olypolyu.randomoddities.entities.EntityBoar;
import Olypolyu.randomoddities.entities.TileEntityIronChest;
import Olypolyu.randomoddities.entities.TileEntityLauncher;
import net.fabricmc.api.ModInitializer;
import net.minecraft.src.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockHelper;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.RecipeHelper;
import turniplabs.halplibe.helper.TextureHelper;
import turniplabs.halplibe.mixin.helper.BlockInterface;
import turniplabs.halplibe.mixin.helper.TileEntityInterface;


public class RandomOddities implements ModInitializer {

    public static final String MOD_ID = "RandomOddities";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static String name(String name) {
        return RandomOddities.MOD_ID + "." + name;
    }

    // blocks
    static int RandomOdditiesIds = 700;
    public static final Block RandomOdditiesTrampoline = new BlockTrampoline(RandomOddities.RandomOdditiesIds + 1, Material.iron);

    public static final Block RandomOdditiesFlintBlock = BlockHelper.createBlock(RandomOdditiesIds + 2, name("FlintBlock"), 31,14, Material.rock, Block.soundStoneFootstep, 3f, 2f, 0f);

    public static final Block RandomOdditiesObsidianChest = new BlockIronChest(RandomOddities.RandomOdditiesIds + 3, Material.iron, 108);

    public static final Block RandomOdditiesPillowBLock = new BlockPillow(RandomOddities.RandomOdditiesIds + 4, Material.cloth);
    public static final Block RandomOdditiesIronChest = new BlockIronChest(RandomOddities.RandomOdditiesIds + 5, Material.iron, 45);




    public void onInitialize() {
        LOGGER.info("RandomOddities initialized.");

        // add in entities
        EntityHelper.createEntity(EntityBoar.class, new RenderLiving(new ModelQuadruped(6, 0), 0.5f), 61, "Boar");
        TileEntityInterface.callAddMapping(TileEntityLauncher.class, "Trampoline");
        TileEntityInterface.callAddMapping(TileEntityIronChest.class, "Iron Chest");


        // load textures
        TextureHelper.addTextureToTerrain(MOD_ID, "FlintBlock.png",31,14);
        TextureHelper.addTextureToTerrain(MOD_ID, "TrampolineTop.png",31,15);
        TextureHelper.addTextureToTerrain(MOD_ID, "TrampolineSides.png",31,16);
        TextureHelper.addTextureToTerrain(MOD_ID, "TrampolineBottom.png",31,17);

        TextureHelper.addTextureToTerrain(MOD_ID, "chestTop.png",31,18);
        TextureHelper.addTextureToTerrain(MOD_ID, "chestFront.png",31,19);
        TextureHelper.addTextureToTerrain(MOD_ID, "chestSides.png",31,20);

        TextureHelper.addTextureToTerrain(MOD_ID, "pillowTop.png",31,21);
        TextureHelper.addTextureToTerrain(MOD_ID, "pillowSides.png",31,22);


        // load crafting recipes
        RecipeHelper.Crafting.createRecipe(RandomOdditiesFlintBlock, 1, new Object[]{"FF", "FF", 'F', Item.flint}); // flint to flint block
        RecipeHelper.Crafting.createShapelessRecipe(Item.flint, 4, new Object[]{new ItemStack(RandomOdditiesFlintBlock, 1 )}); // flint block to flint

        // items
        Item.itemsList[RandomOdditiesTrampoline.blockID] = new ItemBlock(RandomOdditiesTrampoline.blockID - Block.blocksList.length);
        Item.itemsList[RandomOdditiesObsidianChest.blockID] = new ItemBlock(RandomOdditiesObsidianChest.blockID - Block.blocksList.length);
        Item.itemsList[RandomOdditiesIronChest.blockID] = new ItemBlock(RandomOdditiesIronChest.blockID - Block.blocksList.length);
        Item.itemsList[RandomOdditiesPillowBLock.blockID] = new ItemBlock(RandomOdditiesPillowBLock.blockID - Block.blocksList.length);


        //Custom Block Fuckery.
        RandomOdditiesTrampoline.setBlockName("RandomOddities.Trampoline");
        RandomOdditiesTrampoline.setTexCoords(31, 15, 31, 17, 31, 16);
        ((BlockInterface) RandomOdditiesTrampoline).callSetHardness(2.5F);
        ((BlockInterface) RandomOdditiesTrampoline).callSetResistance(5.0F);
        ((BlockInterface) RandomOdditiesTrampoline).callSetStepSound(Block.soundMetalFootstep);

        RandomOdditiesPillowBLock.setBlockName("RandomOddities.Pillow");
        RandomOdditiesPillowBLock.setTexCoords(31, 21, 31, 21, 31, 22);
        ((BlockInterface) RandomOdditiesPillowBLock).callSetHardness(1F);
        ((BlockInterface) RandomOdditiesPillowBLock).callSetResistance(1F);
        ((BlockInterface) RandomOdditiesPillowBLock).callSetStepSound(Block.soundClothFootstep);

        RandomOdditiesObsidianChest.setBlockName("RandomOddities.ObsidianChest");
        RandomOdditiesObsidianChest.setTexCoords(31, 18, 31, 18, 31, 20);
        ((BlockInterface) RandomOdditiesObsidianChest).callSetHardness(2000.0F);
        ((BlockInterface) RandomOdditiesObsidianChest).callSetResistance(10.0F);
        ((BlockInterface) RandomOdditiesObsidianChest).callSetStepSound(Block.soundMetalFootstep);

        RandomOdditiesIronChest.setBlockName("RandomOddities.IronChest");
        RandomOdditiesIronChest.setTexCoords(31, 18, 31, 18, 31, 20);
        ((BlockInterface) RandomOdditiesIronChest).callSetHardness(1.0F);
        ((BlockInterface) RandomOdditiesIronChest).callSetResistance(2.0F);
        ((BlockInterface) RandomOdditiesIronChest).callSetStepSound(Block.soundMetalFootstep);


    }

}