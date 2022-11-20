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
        this.bFull3D = true;
    }

    public int getIconIndex(ItemStack itemstack) {
        return Item.iconCoordToIndex(16, this.color );
    }

    public boolean useItemOnEntity(ItemStack itemstack, EntityLiving entityliving, EntityPlayer entityplayer) {

        // if sheep set flee color to the color of paint brush
        if (entityliving instanceof EntitySheep) {
            EntitySheep entitysheep = (EntitySheep) entityliving;

            if (entitysheep.getFleeceColor() != this.color) {
                itemstack.damageItem(1, entityplayer);
                entitysheep.setFleeceColor(this.color);
                return true;
                }

            }
        return false;
    }

    private void paint(int result, boolean isComplex, World world, int x, int y, int z, ItemStack itemstack, EntityPlayer entityplayer) {
        int blockData = world.getBlockMetadata(x, y, z);

        // using raw here so chests don't drop their contents on the floor, might be a bad idea, dunno.
        world.setBlockRaw(x, y, z, result);

        // all blocks have their color variants spaced on 16 by 16.
        // I just figure out the difference between the current block and a block of "id 0" on the desired color and set to that.
        if(isComplex) {
            world.setBlockMetadataWithNotify(x, y, z, blockData - ((blockData / 16) * 16) + (this.color * 16));
            } else
                world.setBlockMetadataWithNotify(x, y, z, this.color);

        itemstack.damageItem(1, entityplayer);
    }



    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, double heightPlaced) {
        int block = world.getBlockId(i, j, k);

        // base blocks
        switch (block) {

            case 50: // planks
            case 80: // fences
                paint(block + 1, false, world, i, j, k, itemstack, entityplayer);
                return true;

            case 160: // wooden stairs
                paint(171, true, world, i, j, k, itemstack, entityplayer);
                return true;

            case 140: // wooden slab
                paint(154, true, world, i, j, k, itemstack, entityplayer);
                return true;

            case 680: // chests
            case 90: // fence gates
                paint(block + 1, true, world, i, j, k, itemstack, entityplayer);
                return true;

            }

        // if the block is already painted then don't paint it again.
        if(world.getBlockMetadata(i, j, k) == this.color) return false;

        // already painted blocks.
        switch (block) {

            case 81: // fence
            case 51: // planks
            case 850: // lamps
            case 851: // active lamps
            case 110: // wool
                paint(block, false, world, i, j, k, itemstack, entityplayer);
                return true;

            case 681: // painted chest
            case 91:  // painted fence gates
            case 154: // painted slabs
            case 171: // Painted stairs
                paint(block, true, world, i, j, k, itemstack, entityplayer);
                return true;
                }

        return false;
        }
}