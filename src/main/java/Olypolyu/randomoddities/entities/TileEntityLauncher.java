package Olypolyu.randomoddities.entities;

import Olypolyu.randomoddities.mixin.RandomOdditiesEntityMixin;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Entity;
import net.minecraft.src.TileEntity;

import java.util.List;
import java.util.Random;

public class TileEntityLauncher extends TileEntity {

    private final Random random = new Random();

    static double launchX = 0;
    static double launchY = 0;
    static double launchZ = 0;
    static String soundEffect = "random.bow";
    static String particle = "flame";

    public TileEntityLauncher( double launchX, double launchY, double launchZ) {
        TileEntityLauncher.launchX = launchX;
        TileEntityLauncher.launchY = launchY;
        TileEntityLauncher.launchZ = launchZ;
    }

    public TileEntityLauncher( double launchX, double launchY, double launchZ, String soundEffect, String particle) {
        TileEntityLauncher.launchX = launchX;
        TileEntityLauncher.launchY = launchY;
        TileEntityLauncher.launchZ = launchZ;
        TileEntityLauncher.soundEffect = soundEffect;
        TileEntityLauncher.particle = particle;
    }


    public void updateEntity() {

            // get entities within bounding box
            List<Entity> list = this.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(this.xCoord - 0.25, this.yCoord, this.zCoord - 0.25, this.xCoord + 1.25, this.yCoord + 2.5, this.zCoord + 1.25));

            for (int j = 0; j < list.size(); ++j) {
                Entity entity = list.get(j);

                // yeet entities within bounding box
                ((RandomOdditiesEntityMixin) entity).setFallDistance(0f);

                if (TileEntityLauncher.launchY != 0) entity.motionY = TileEntityLauncher.launchY;
                if (TileEntityLauncher.launchX != 0) entity.motionX = TileEntityLauncher.launchX;
                if (TileEntityLauncher.launchZ != 0) entity.motionZ = TileEntityLauncher.launchZ;


                // generate particles and play sound.
                this.worldObj.playSoundEffect((double) this.xCoord + 0.5, (double) this.yCoord + 0.5, (double) this.zCoord + 0.5, soundEffect, 0.3F, 0.6F);
                for (int i1 = 0; i1 < 8 + this.random.nextInt(7); i1++) {
                    this.worldObj.spawnParticle(particle,
                            entity.posX,
                            entity.posY - 0.5f,
                            entity.posZ,
                            (this.random.nextFloat() - 0.5),
                            (this.random.nextFloat() - 0.5),
                            (this.random.nextFloat() - 0.5));
                }
            }
    }
}
