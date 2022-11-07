package Olypolyu.randomoddities.entities;

import net.minecraft.src.*;
import net.minecraft.src.helper.DamageType;

import java.util.List;

public class EntityBoar extends EntityMob {
    private int angerLevel = 0;
    private int forgivenessLevel;
    private boolean enraged = false;

    public EntityBoar(World world) {
        super(world);
        this.texture = "/mob/boar.png";
        this.health = 35;
        this.moveSpeed = 0.25F;
        this.attackStrength = 7;
        this.scoreValue = 30;
    }

    public void setAngerStats(){
        this.texture = "/mob/boarAngry.png";
        this.moveSpeed = 1.5F;
        this.scoreValue = 300;
        this.enraged = true;
    }
    public void setPassiveStats() {
        this.angerLevel = 0;
        this.texture = "/mob/boar.png";
        this.health = 35;
        this.moveSpeed = 0.25F;
        this.scoreValue = 30;
        this.entityToAttack = null;
        this.enraged = false;
    }
        public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setShort("Anger", (short)this.angerLevel);
        System.out.println(this.angerLevel);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        this.angerLevel = nbttagcompound.getShort("Anger");
        if (this.angerLevel >= 1) this.setAngerStats();
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
        this.angerLevel = 4 + this.rand.nextInt(7);
        this.setAngerStats();
    }

    public void onLivingUpdate() {
        if (this.forgivenessLevel < 100) this.forgivenessLevel++;
        if (this.forgivenessLevel >= 100 && this.angerLevel >= 1) { this.angerLevel--; this.forgivenessLevel = 0;}
        if (this.angerLevel < 1 && this.enraged) this.setPassiveStats();
        super.onLivingUpdate();
    }

    protected String getLivingSound() {
        return "mob.pig";
    }

    protected String getHurtSound() {
        return "mob.pig";
    }

    protected String getDeathSound() { return "mob.pigdeath"; }

    protected int getDropItemId() {
        return this.fire > 0 ? Item.foodPorkchopCooked.itemID : Item.foodPorkchopRaw.itemID;
    }

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
