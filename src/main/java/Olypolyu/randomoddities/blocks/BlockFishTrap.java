package Olypolyu.randomoddities.blocks;

import Olypolyu.randomoddities.technical.FishTrapLootTable;
import Olypolyu.randomoddities.technical.LootStack;
import net.minecraft.src.*;

import java.util.Random;

public class BlockFishTrap extends Block {
    private final Random random = new Random();

    public BlockFishTrap(int i, Material material) {
        super(i, material);
        this.setTickOnLoad(true);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    private boolean isInWater(World world, int i, int j, int k){
    int z;
    int x;
    int water = 0;

        for ( z = k - 2; z < k + 2; z++) {
            for ( x = i - 2; x < i + 2; x++  ) {
                Material mat = world.getBlockMaterial(x, j, z);

                if ( mat == Block.fluidWaterStill.blockMaterial || mat == Block.fluidWaterFlowing.blockMaterial )
                    water = water + 1;
                }
            }

        return water >= 8;
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        return isInWater(world, i, j, k);
    }

    public void updateTick(World world, int i, int j, int k, Random random) {

        if (isInWater(world, i, j, k))
            if (random.nextInt(100) == 0 && world.getBlockMetadata(i, j, k) == 1)
                world.setBlockMetadataWithNotify(i, j, k, 2);
    }

    public int getBlockTextureFromSideAndMetadata(int side, int meta) {
        if (meta == 1)
            return texCoordToIndex(30, 31);
        else if (meta == 2)
            return texCoordToIndex(30,29);
        else
            return texCoordToIndex(30,28);
        }


    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
        ItemStack currentItem = entityplayer.getCurrentEquippedItem();

        if ( currentItem != null && world.getBlockMetadata(i, j, k) == 0) {
            if (currentItem.getItem() == Item.string) {
                entityplayer.inventory.getCurrentItem().consumeItem(entityplayer);
                world.setBlockMetadataWithNotify(i, j, k, 1);
                this.getBlockTextureFromSideAndMetadata(1,0);
                return true;
                }
            }

        if ( world.getBlockMetadata(i, j, k) == 2){
            world.setBlockMetadataWithNotify(i, j, k, 0);
            ItemStack returnStack = null;

            while (returnStack == null) {
                LootStack loot = FishTrapLootTable.FishingLoot.get(random.nextInt(FishTrapLootTable.FishingLoot.size()));
                if (loot != null) returnStack = loot.generateLoot();
                }

            world.dropItem(i, j + 1, k, returnStack);
            return true;
            }

        return false;
    }

}
