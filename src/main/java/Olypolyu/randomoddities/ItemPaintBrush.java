package Olypolyu.randomoddities;

import net.minecraft.src.*;

public class ItemPaintBrush extends Item {

    private final int color;

    public ItemPaintBrush(int id, int color) {
        super(id);
        this.maxStackSize = 1;
        this.notInCreativeMenu = false;
        this.setMaxDamage(24);
        this.color = color;
    }

    public int getIconIndex(ItemStack itemstack) {
        return Item.iconCoordToIndex(16, this.color );
    }

    private boolean canPaint(int id) {
        switch(id){
            default:
                return false;

            case 110: //wool
                return true;

            case 51: //planks
                return  true;

            case 850: //lamps
                return true;
        }
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, double heightPlaced) {
        int block = world.getBlockId(i, j, k);

        if (block == 50) {
            world.setBlock(i, j, k, 51);
            world.setBlockMetadataWithNotify(i, j, k, this.color);
            itemstack.damageItem( 1, entityplayer);
            return true;
        }

        if (canPaint(block)) {
            if(world.getBlockMetadata(i, j, k) == this.color) return false;
            world.setBlockMetadataWithNotify(i, j, k, this.color);
            itemstack.damageItem( 1, entityplayer);
            return true;
        } else
            return false;

    }
}
