package Olypolyu.randomoddities;

import Olypolyu.randomoddities.entities.TileEntityIronChest;
import net.minecraft.src.*;

import java.util.Random;

public class BlockIronChest extends BlockContainerRotatable {
    private final Random random = new Random();
    private final int id;
    private final int chestSize;

    public BlockIronChest(int i,Material material, int chestSize) {
        super(i, material);
        this.id = i;
        this.chestSize = chestSize;
    }

    public void onBlockRemoval(World world, int i, int j, int k) {
        TileEntityIronChest tileentityironchest = (TileEntityIronChest)world.getBlockTileEntity(i, j, k);

        for(int l = 0; l < tileentityironchest.getSizeInventory(); ++l) {
            ItemStack itemstack = tileentityironchest.getStackInSlot(l);
            if (itemstack != null) {
                float f = this.random.nextFloat() * 0.8F + 0.1F;
                float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                while(itemstack.stackSize > 0) {
                    int i1 = this.random.nextInt(21) + 10;
                    if (i1 > itemstack.stackSize) {
                        i1 = itemstack.stackSize;
                    }

                    itemstack.stackSize -= i1;
                    EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getMetadata()));
                    float f3 = 0.05F;
                    entityitem.motionX = (float)this.random.nextGaussian() * f3;
                    entityitem.motionY = (float)this.random.nextGaussian() * f3 + 0.4F;
                    entityitem.motionZ = (float)this.random.nextGaussian() * f3;
                    world.entityJoinedWorld(entityitem);
                }
            }
        }
        super.onBlockRemoval(world, i, j, k);
    }


    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
        TileEntityIronChest chest = (TileEntityIronChest) world.getBlockTileEntity(i, j, k);

        if (!world.isMultiplayerAndNotHost) {
            entityplayer.displayGUIChest(chest);
            }
            return true;
        }


    protected TileEntity getBlockEntity() {
        return new TileEntityIronChest(this.chestSize, translateBlockName(this.id));
    }
}
