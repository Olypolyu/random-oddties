package Olypolyu.randomoddities.blocks;

import Olypolyu.randomoddities.entities.TileEntityBubbleColumn;
import net.minecraft.src.*;

public class BlockBubbleGenerator extends BlockContainer {
    public BlockBubbleGenerator(int i, Material material) {
        super(i, material);
    }

    protected TileEntity getBlockEntity() {
        return new TileEntityBubbleColumn();
        }
}

