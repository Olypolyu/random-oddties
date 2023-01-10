package Olypolyu.randomoddities.items;

import net.minecraft.src.*;

public class ItemPaintScrapper extends Item {

    public ItemPaintScrapper(int i) {
        super(i);
        this.maxStackSize = 1;
        this.bFull3D = true;
        this.setMaxDamage(24);
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, double heightPlaced) {
        int block = world.getBlockId(i, j, k);
        int meta = world.getBlockMetadata(i, j, k);

        // base blocks
        switch (block) {

            case 51: // planks
            case 81: // fences
                world.setBlockWithNotify(i, j, k, block - 1);
                itemstack.damageItem(1, entityplayer);
                return true;

            case 171: // wooden stairs
                world.setBlockAndMetadataWithNotify(i, j, k, 160, meta % 16);
                itemstack.damageItem(1, entityplayer);
                return true;

            case 154: // wooden slab
                world.setBlockAndMetadataWithNotify(i, j, k, 140, meta % 16);
                itemstack.damageItem(1, entityplayer);
                return true;

            case 681: // chests
            case 91: // fence gates
                world.setBlockRaw(i, j, k, block - 1);
                world.setBlockMetadataWithNotify(i, j, k, meta % 16);
                itemstack.damageItem(1, entityplayer);
                return true;

        }

        return false;
    }

}
