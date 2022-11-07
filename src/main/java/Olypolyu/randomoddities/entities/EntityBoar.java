package Olypolyu.randomoddities.entities;

import net.minecraft.src.*;
import net.minecraft.src.helper.DamageType;
import java.util.List;

public class EntityBoar extends EntityMob {
    private int angerLevel = 0;

    public EntityBoar(World world) {
        super(world);
        this.texture = "/mob/boar.png";
        this.health = 35;
        this.moveSpeed = 0.25F;
        this.attackStrength = 5;
        this.scoreValue = 30;
    }

    public void setAngerStats(){
        this.texture = "/mob/boarAngry.png";
        this.moveSpeed = 1F;
        this.scoreValue = 300;
    }

        public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setShort("Anger", (short)this.angerLevel);
        System.out.println(this.angerLevel);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        this.angerLevel = nbttagcompound.getShort("Anger");
    }
    protected Entity findPlayerToAttack() {
        return this.angerLevel == 0 ? null : super.findPlayerToAttack();
    }

    public boolean attackEntityFrom(Entity entity, int i, DamageType type) {
        if (entity instanceof EntityPlayer) {
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0, 32.0, 32.0));

            for(int j = 0; j < list.size(); ++j) {
                Entity entity1 = (Entity)list.get(j);
                if (entity1 instanceof EntityBoar) {
                    EntityBoar entityboar = (EntityBoar)entity1;
                    entityboar.becomeAngryAt(entity);
                }
            }

            this.becomeAngryAt(entity);
        }

        return super.attackEntityFrom(entity, i, type);
    }

    private void becomeAngryAt(Entity entity) {
        this.entityToAttack = entity;
        this.angerLevel = 400 + this.rand.nextInt(400);
    }

    public void onLivingUpdate() {
        if (this.angerLevel >= 1) this.setAngerStats();
        super.onLivingUpdate();
    }

    protected String getLivingSound() {
        return "mob.pig";
    }

    protected String getHurtSound() {
        return "mob.pig";
    }

    protected String getDeathSound() { return "mob.pigdeath"; }

    @Override
    protected int getDropItemId() {
        return this.fire > 0 ? Item.foodPorkchopCooked.itemID : Item.foodPorkchopRaw.itemID;
    }

    @Override
    protected void dropFewItems() {
        int i = this.getDropItemId();
        if (i > 0) {
            int j = rand.nextInt(5);
            for( int k = 0; k < j; k++) {
                this.dropItem(i, 1);
            }
        }
    }

}
