package Olypolyu.randomoddities;

import Olypolyu.randomoddities.entities.TileEntityLauncher;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;

public class BlockPillow extends BlockTrampoline{
    public BlockPillow(int i, Material material){
        super(i, material);
    }

    protected TileEntity getBlockEntity() {
        return new TileEntityLauncher(0, 0, 0);
    }

}
