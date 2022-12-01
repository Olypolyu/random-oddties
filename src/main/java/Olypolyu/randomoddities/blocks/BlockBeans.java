package Olypolyu.randomoddities.blocks;

import net.minecraft.src.*;
import net.minecraft.src.helper.Direction;

import java.util.ArrayList;
import java.util.Random;

public class BlockBeans extends Block {

    public BlockBeans(int i, Material material) {
        super(i, material);
        this.setTickOnLoad(true);
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        int side = world.getBlockMetadata(i, j, k) & 0b1111;
        int growStage = world.getBlockMetadata(i, j, k) >> 4;
        System.out.println(side);
        System.out.println(growStage);
    }

    public ArrayList<Block> growsOn = new ArrayList<>();
    {
        this.growsOn.add(Block.logOakMossy);
        this.growsOn.add(Block.logBirch);
        this.growsOn.add(Block.logCherry);
        this.growsOn.add(Block.logOak);
        this.growsOn.add(Block.logEucalyptus);
        this.growsOn.add(Block.logPine);
    }

    public int getRenderType() {
        return 8;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube () {
        return false;
    }


    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        return growsOn.contains( Block.getBlock( world.getBlockId(i - 1, j, k) ))
            || growsOn.contains( Block.getBlock( world.getBlockId(i + 1, j, k) ))
            || growsOn.contains( Block.getBlock( world.getBlockId(i, j, k - 1) ))
            || growsOn.contains( Block.getBlock( world.getBlockId(i, j, k + 1) ));
    }

    public void onBlockPlaced(World world, int i, int j, int k, Direction side, EntityLiving player, double sideHeight) {

        // North
        if ( growsOn.contains( Block.getBlock( world.getBlockId( i, j, k + 1) )) )  {
            world.setBlockMetadataWithNotify(i, j, k, ( 5 << 4) | 2);
            return;
        }

        // South
        if ( growsOn.contains( Block.getBlock( world.getBlockId( i, j,k - 1 ) )) ) {
            world.setBlockMetadataWithNotify(i, j, k, ( 5 << 4) | 3);
            return;
        }

        // West
        if ( growsOn.contains( Block.getBlock( world.getBlockId(i + 1, j, k ) )) ) {
            world.setBlockMetadataWithNotify(i, j, k, ( 5 << 4) | 4);
            return;
            }

        // East
        if ( growsOn.contains( Block.getBlock( world.getBlockId(i - 1, j, k ) )) ) {
            world.setBlockMetadataWithNotify(i, j, k, ( 5 << 4) | 5);
            return;
            }

        this.dropBlockAsItem(world, i, j, k, 0);
        world.setBlockWithNotify(i, j, k, 0);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
        int side = world.getBlockMetadata(i, j, k) & 0b1111;
        switch (side){

            // North
            case 2:
                if ( !world.isBlockNormalCube(i, j, k + 1) )
                    world.setBlockWithNotify(i, j, k, 0);
                break;

            // South
            case 3:
                if ( !world.isBlockNormalCube( i, j, k - 1 ) )
                    world.setBlockWithNotify(i, j, k, 0);
                break;

            // West
            case 4:
                if ( !world.isBlockNormalCube( i + 1, j, k ) )
                    world.setBlockWithNotify(i, j, k, 0);
                break;

            // East
            case 5:
                if ( !world.isBlockNormalCube( i - 1, j, k ) )
                    world.setBlockWithNotify(i, j, k, 0);
                break;

        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        return null;
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {
        int side = world.getBlockMetadata(i, j, k) & 0b1111;
        float f = 0.125F;

        switch (side) {
            case 2:
                this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
                break;

            case 3:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
                break;

            case 4:
                this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                break;

            case 5:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
                break;
        }

        return super.getSelectedBoundingBoxFromPool(world, i, j, k);
    }

}
