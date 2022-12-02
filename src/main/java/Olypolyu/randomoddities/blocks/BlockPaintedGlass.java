package Olypolyu.randomoddities.blocks;

import net.minecraft.src.BlockPlanksPainted;

import java.util.Random;

public class BlockPaintedGlass extends BlockPlanksPainted {
    public BlockPaintedGlass(int id) {
        super(id);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public int quantityDropped(int metadata, Random random) {
        return 0;
    }

    public int getRenderBlockPass() {
        return 1;
    }
}
