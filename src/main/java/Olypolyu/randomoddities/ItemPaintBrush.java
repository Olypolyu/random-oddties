package Olypolyu.randomoddities;

import net.minecraft.src.*;

public class ItemPaintBrush extends Item {
    public ItemPaintBrush(int id) {
        super(id);
        this.maxStackSize = 1;
        this.notInCreativeMenu = true;
        this.setMaxDamage(16);
    }

    public int getIconIndex(ItemStack itemstack) {
        return Item.iconCoordToIndex(16, itemstack.tag.getInteger("color") );
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

        int color = itemstack.tag.getInteger("color");
        System.out.println(color);

        if (block == 50) {
            world.setBlock(i, j, k, 51);
            world.setBlockMetadataWithNotify(i, j, k, color);
            itemstack.damageItem( 1, entityplayer);
            return true;
        }

        if (canPaint(block)) {
            world.setBlockMetadataWithNotify(i, j, k, color);
            itemstack.damageItem( 1, entityplayer);
            return true;
        } else
            return false;

    }

}
