package Olypolyu.randomoddities;

import Olypolyu.randomoddities.entities.TileEntityLauncher;
import net.minecraft.src.BlockContainerRotatable;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;

public class BlockTrampoline extends BlockContainerRotatable {

    public BlockTrampoline(int i, Material material) {
        super(i, material);
        this.setBlockBounds(0F, 0F, 0F, 1.0F, 0.5F, 1.0F);
        this.setLightOpacity(1);
    }
    public boolean isOpaqueCube() {
        return false;
    }

    protected TileEntity getBlockEntity() {
        return new TileEntityLauncher(0, 1, 0);
    }
}