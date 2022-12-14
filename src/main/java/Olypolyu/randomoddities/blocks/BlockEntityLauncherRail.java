package Olypolyu.randomoddities.blocks;

import net.minecraft.src.*;

public class BlockEntityLauncherRail extends BlockRail {

    public BlockEntityLauncherRail(int i, boolean flag) {
        super(i, flag);
    }
    public int getBlockTextureFromSideAndMetadata(int i, int j) {
        return this.atlasIndices[i];
    }

    private int coolDown = 0;
    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {

        // coolDown so we don't unload the ENTIRE cart at once.
        if (coolDown <= 0 && entity instanceof EntityMinecart) {
            if ( ((EntityMinecart) entity).minecartType == 1) {

                ItemStack itemstack = null;
                int invSize = ((EntityMinecart) entity).getSizeInventory();
                int invSlot;

                // picks up first available stack
                for ( invSlot = 0; invSlot < invSize; invSlot++ ) {
                    itemstack = ((EntityMinecart) entity).getStackInSlot(invSlot);

                    // break if we have something
                    if (itemstack != null) break;
                    }

                if (itemstack != null) {
                    // decrease stack by 1 then drop item.
                    ((EntityMinecart) entity).decrStackSize(invSlot, 1);

                    // put stuff inside chest
                    int meta = world.getBlockMetadata(i, j, k);

                    int xOffset = 0;
                    int zOffset = 0;

                    if (meta == 1)
                         zOffset = 1;
                    else xOffset = 1;


                    // look if there's a chest adjacent to the rail block
                    TileEntityChest chest = null;

                    int adjacentId = world.getBlockId(i + xOffset, j, k + zOffset);

                    if (Block.blocksList[adjacentId] instanceof BlockChest)
                        chest = (TileEntityChest)world.getBlockTileEntity(i + xOffset, j, k + zOffset);

                    else {
                        adjacentId = world.getBlockId(i - xOffset, j, k - zOffset);

                        if (Block.blocksList[adjacentId] instanceof BlockChest)
                            chest = (TileEntityChest)world.getBlockTileEntity(i - xOffset, j, k - zOffset);
                    }

                    if (chest != null) {
                        int chestInvSlot;

                        for (chestInvSlot = 0; chestInvSlot < chest.getSizeInventory(); chestInvSlot ++){

                            if (chest.getStackInSlot(chestInvSlot) == null ) {
                                chest.setInventorySlotContents(chestInvSlot, new ItemStack(itemstack.getItem(), 1, itemstack.getMetadata()));
                                break;
                            }

                            if (chest.getStackInSlot(chestInvSlot).getItem() == itemstack.getItem() && chest.getStackInSlot(chestInvSlot).getMetadata() == itemstack.getMetadata() && chest.getStackInSlot(chestInvSlot).stackSize != chest.getStackInSlot(chestInvSlot).getMaxStackSize()) {
                                chest.setInventorySlotContents(chestInvSlot, new ItemStack(itemstack.getItem(), chest.getStackInSlot(chestInvSlot).stackSize + 1, itemstack.getMetadata()) );
                                break;
                            }

                        }

                        if ( chestInvSlot == chest.getSizeInventory()) {
                            EntityItem entityitem = new EntityItem(world,i + 0.5, j - 1,k + 0.5, new ItemStack( itemstack.itemID, 1, itemstack.getMetadata()));
                            world.entityJoinedWorld(entityitem);
                        }

                    } else {
                        EntityItem entityitem = new EntityItem(world, i + 0.5, j - 1, k + 0.5, new ItemStack(itemstack.itemID, 1, itemstack.getMetadata()));
                        world.entityJoinedWorld(entityitem);
                    }
                }
            }
            coolDown = 20;
        } else
            if (coolDown > 0 && entity instanceof EntityMinecart) coolDown--;


    }

}
