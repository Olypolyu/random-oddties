package Olypolyu.randomoddities.blocks;

import Olypolyu.randomoddities.entities.TileEntityResizableChest;
import net.minecraft.src.*;
import net.minecraft.src.helper.Direction;

import java.util.Random;

public class BlockResizableChest extends BlockContainer {
    private final Random random = new Random();
    private final int chestSize;
    private final int topX;
    private final int topY;
    private final int sideX;
    private final int sideY;
    private final int frontX;
    private final int frontY;

    public BlockResizableChest(int i, Material material, int chestSize, int topX, int topY, int sideX, int sideY, int frontX, int frontY) {
        super(i, material);
        this.chestSize = chestSize;
        this.topX = topX;
        this.topY = topY;
        this.sideX = sideX;
        this.sideY = sideY;
        this.frontX = frontX;
        this.frontY = frontY;
    }

    public void onBlockRemoval(World world, int i, int j, int k) {
        TileEntityResizableChest tileentityironchest = (TileEntityResizableChest) world.getBlockTileEntity(i, j, k);

        for (int l = 0; l < tileentityironchest.getSizeInventory(); ++l) {
            ItemStack itemstack = tileentityironchest.getStackInSlot(l);
            if (itemstack != null) {
                float f = this.random.nextFloat() * 0.8F + 0.1F;
                float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                while (itemstack.stackSize > 0) {
                    int i1 = this.random.nextInt(21) + 10;
                    if (i1 > itemstack.stackSize) {
                        i1 = itemstack.stackSize;
                    }

                    itemstack.stackSize -= i1;
                    EntityItem entityitem = new EntityItem(world, (float) i + f, (float) j + f1, (float) k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getMetadata()));
                    float f3 = 0.05F;
                    entityitem.motionX = (float) this.random.nextGaussian() * f3;
                    entityitem.motionY = (float) this.random.nextGaussian() * f3 + 0.4F;
                    entityitem.motionZ = (float) this.random.nextGaussian() * f3;
                    world.entityJoinedWorld(entityitem);
                }
            }
        }
        super.onBlockRemoval(world, i, j, k);
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
        TileEntityResizableChest chest = (TileEntityResizableChest) world.getBlockTileEntity(i, j, k);
        if (!world.isMultiplayerAndNotHost) {
            entityplayer.displayGUIChest(chest);
        }
        return true;
    }

    @Override
    public void onBlockPlaced(World world, int x, int y, int z, Direction side, EntityLiving player, double sideHeight) {
        int metadata = player.getHorizontalPlacementDirection(side).getOpposite().index;
        world.setBlockMetadataWithNotify(x, y, z, metadata);
    }

    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int meta) {
        if (side == 0 || side == 1)
            return texCoordToIndex(this.topX, this.topY);
        else {

            // block's facing :
            switch (meta){

                //north
                case 0:
                    if (side == 3)
                         return texCoordToIndex(this.frontX, this.frontY);
                    else return texCoordToIndex(this.sideX, this.sideY);

                //east
                case 1:
                    if (side == 4)
                         return texCoordToIndex(this.frontX, this.frontY);
                    else return texCoordToIndex(this.sideX, this.sideY);

                //south
                case 2:
                    if (side == 2)
                         return texCoordToIndex(this.frontX, this.frontY);
                    else return texCoordToIndex(this.sideX, this.sideY);

                //west
                case 3:
                    if (side == 5)
                         return texCoordToIndex(this.frontX, this.frontY);
                    else return texCoordToIndex(this.sideX, this.sideY);
                }
            }

        // this line will never actually be reached. it is in here to avoid errors.
        return 80085;
    }

    // Sides           Metadata
    // 0 - bottom      0 - north
    // 1 - top         1 - east
    // 2 - north       2 - south
    // 3 - south       3 - west
    // 4 - west        4 - up
    // 5 - east        5 - down




    protected TileEntity getBlockEntity() {
        TileEntityResizableChest chest = new TileEntityResizableChest();
        chest.SetTileEntityResizableChest(this.chestSize);
        return chest;
    }
}
