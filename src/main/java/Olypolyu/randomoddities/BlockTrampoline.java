package Olypolyu.randomoddities;

import Olypolyu.randomoddities.entities.TileEntityLauncher;
import net.minecraft.src.*;

import java.util.Random;

public class BlockTrampoline extends BlockContainerRotatable {
    private final Random random = new Random();

    public BlockTrampoline(int i) {
        super(i, Material.web);
        this.setBlockBounds(0F, 0F, 0F, 1.0F, 0.5F, 1.0F);
        this.setLightOpacity(1);
    }
    public boolean isOpaqueCube() {
        return false;
    }

    protected TileEntity getBlockEntity() {
        return new TileEntityLauncher(0, 1.5, 0);
    }
}