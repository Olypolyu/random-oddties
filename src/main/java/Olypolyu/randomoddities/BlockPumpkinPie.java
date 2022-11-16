package Olypolyu.randomoddities;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BlockCake;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public class BlockPumpkinPie extends BlockCake {

    public BlockPumpkinPie(int i) {
            super(i);
            this.setTickOnLoad(true);
    }

    public void setBlockBoundsBasedOnState(World world, int i, int j, int k) {
        int l = world.getBlockMetadata(i, j, k);
        float f = 0.0625F;
        float f1 = (float)(1 + l * 3.5) / 16.0F;
        float f2 = 0.3125F;
        this.setBlockBounds(f1, 0.0F, f, 1.0F - f, f2 ,1.0F - f);
    }

    public void setBlockBoundsForItemRender() {
        float f = 0.0625F;
        float f1 = 0.3125F;
        this.setBlockBounds(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {
        int l = world.getBlockMetadata(i, j, k);
        float f = 0.0625F;
        float f1 = (float)(1 + l * 3.5) / 16.0F;
        float f2 = 0.3125F;
        return AxisAlignedBB.getBoundingBoxFromPool((float)i + f1, j, (float)k + f, (float)(i + 1) - f, (float)j + f2, (float)(k + 1) - f);
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
        this.eatSlice(world, i, j, k, entityplayer);
        return true;
    }

    private void eatSlice(World world, int i, int j, int k, EntityPlayer entityplayer) {
        if (entityplayer.health < 20) {
            entityplayer.heal(5);
            int l = world.getBlockMetadata(i, j, k) + 1;
            if (l >= 4) {
                world.setBlockWithNotify(i, j, k, 0);
            } else {
                world.setBlockMetadataWithNotify(i, j, k, l);
                world.markBlockAsNeedsUpdate(i, j, k);
            }
        }

    }

    public int getBlockTextureFromSideAndMetadata(int i, int j) {
        return j > 0 && i == 4 ? texCoordToIndex(31, 29) : this.atlasIndices[i];
    }

}
