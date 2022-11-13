package Olypolyu.randomoddities.entities;

import net.minecraft.src.*;

public class TileEntityResizableChest extends TileEntity implements IInventory {
    private final String InvName;
    private final int chestSize;

    public TileEntityResizableChest(int chestSize, String InvName) {
        this.chestSize = chestSize;
        this.InvName = InvName;
        this.chestContents = new ItemStack[this.chestSize];
    }
    private ItemStack[] chestContents;

    public int getSizeInventory() {
        return this.chestSize;
    }

    public ItemStack getStackInSlot(int i) {
        return this.chestContents[i];
    }

    public ItemStack decrStackSize(int i, int j) {
        if (this.chestContents[i] != null) {
            ItemStack itemstack1;
            if (this.chestContents[i].stackSize <= j) {
                itemstack1 = this.chestContents[i];
                this.chestContents[i] = null;
                this.onInventoryChanged();
                return itemstack1;
            } else {
                itemstack1 = this.chestContents[i].splitStack(j);
                if (this.chestContents[i].stackSize == 0) {
                    this.chestContents[i] = null;
                }

                this.onInventoryChanged();
                return itemstack1;
            }
        } else {
            return null;
        }
    }

    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.chestContents[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    public String getInvName() {
        return this.InvName;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
        this.chestContents = new ItemStack[this.getSizeInventory()];

        for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;
            if (j < this.chestContents.length) {
                this.chestContents[j] = new ItemStack(nbttagcompound1);
            }
        }

    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();

        for(int i = 0; i < this.chestContents.length; ++i) {
            if (this.chestContents[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.chestContents[i].writeToNBT(nbttagcompound1);
                nbttaglist.setTag(nbttagcompound1);
            }
        }

        nbttagcompound.setTag("Items", nbttaglist);
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        if (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) {
            return false;
        } else {
            return entityplayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
        }
    }
}

