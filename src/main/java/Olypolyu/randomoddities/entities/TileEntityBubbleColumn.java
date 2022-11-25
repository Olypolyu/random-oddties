package Olypolyu.randomoddities.entities;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.TileEntity;

import java.util.List;
import java.util.Random;

public class TileEntityBubbleColumn extends TileEntity {
    private final Random random = new Random();
    private int columnLength;

    private void getColumnLength() {
        boolean reachedTop = false;

        while (!reachedTop) {
            if (this.worldObj.getBlockId( this.xCoord, this.yCoord + 1 + this.columnLength, this.zCoord ) == Block.fluidWaterStill.blockID && columnLength < 21)
                this.columnLength = this.columnLength + 1;
            else reachedTop = true;
            }
        }

    public void updateEntity() {
        getColumnLength();

        if (this.columnLength > 1) {
            // get entities within bounding box, then yeet.
            List<Entity> list = this.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(this.xCoord + 0.25, this.yCoord, this.zCoord + 0.25, this.xCoord + 0.75, this.yCoord + 1 + this.columnLength, this.zCoord + 0.75));

            for (int j = 0; j < list.size(); ++j) {
                Entity entity = list.get(j);
                entity.motionY = entity.motionY + 0.25 + (float)((this.columnLength - entity.getDistance(this.xCoord, this.yCoord, this.zCoord)) * 0.10);
                }

            int i;
            if (this.columnLength > 0)
                for (i = 0; i < random.nextInt(this.columnLength); i++)
                    this.worldObj.spawnParticle("bubble",
                            this.xCoord + this.random.nextFloat(),
                            this.yCoord + 1,
                            this.zCoord + this.random.nextFloat(),
                            0,
                            this.columnLength,
                            0);
            }
        }
}
