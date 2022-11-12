package Olypolyu.randomoddities.entities;

import Olypolyu.randomoddities.mixin.RandomOdditiesEntityMixin;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Entity;
import net.minecraft.src.TileEntity;

import java.util.List;
import java.util.Random;

public class TileEntityLauncher extends TileEntity {

    private final Random random = new Random();

    private final double launchX;
    private final double launchY;
    private final double launchZ;

    private String soundEffect = "random.bow";
    private String particle = "flame";

    public TileEntityLauncher( double launchX, double launchY, double launchZ ) {
        this.launchX = launchX;
        this.launchY = launchY;
        this.launchZ = launchZ;
    }

    public TileEntityLauncher( double launchX, double launchY, double launchZ, String soundEffect, String particle) {
        this.launchX = launchX;
        this.launchY = launchY;
        this.launchZ = launchZ;
        this.soundEffect = soundEffect;
        this.particle = particle;
    }


    public void updateEntity() {

            // get entities within bounding box, then yeet.
            List<Entity> list = this.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(this.xCoord - 0.25, this.yCoord, this.zCoord - 0.25, this.xCoord + 1.25, this.yCoord + 2.5, this.zCoord + 1.25));

            for (int j = 0; j < list.size(); ++j) {
                Entity entity = list.get(j);

                // negates fall damage.
                ((RandomOdditiesEntityMixin) entity).setFallDistance(0f);

                // will send the entity back with 1/4 of it's falling back + it's launch force
                if (this.launchY != 0 && entity.motionY < launchY * 0.75) {
                        entity.motionY = ( (entity.motionY * -1) / 4 ) + this.launchY;
                        doVisualEffects(entity);
                    }

                    // x axis
                    if (this.launchX != 0 && entity.motionX < launchX * 0.75) {
                        entity.motionX = this.launchX;
                        doVisualEffects(entity);
                    }

                    // Z axis
                    if (this.launchZ != 0 && entity.motionZ < launchZ * 0.75) {
                        entity.motionZ = this.launchZ;
                        doVisualEffects(entity);
                    }
                }
            }

        // generate particles and play sound.
        private void doVisualEffects(Entity entity) {
            this.worldObj.playSoundEffect((double) this.xCoord + 0.5, (double) this.yCoord + 0.5, (double) this.zCoord + 0.5, this.soundEffect, 0.3F, 0.6F);
            for (int i = 0; i < 8 + this.random.nextInt(7); i++){
                this.worldObj.spawnParticle(this.particle,
                entity.posX,
                entity.posY-0.5f,
                entity.posZ,
                (this.random.nextFloat()-0.5),
                (this.random.nextFloat()-0.5),
                (this.random.nextFloat()-0.5));
            }
        }
}