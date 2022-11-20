package Olypolyu.randomoddities;

import Olypolyu.randomoddities.mixin.RandomOdditiesEntityMixin;
import net.minecraft.src.*;

import java.util.Random;

public class ItemWindBottle extends Item {
    private final Random random = new Random();

    public ItemWindBottle(int i, int Charge) {
        super(i);
        this.maxStackSize = 1;
        this.setMaxDamage(Charge);
        }

    public int getIconFromDamage(int i) {
        if ( i == this.getMaxDamage()) return Item.iconCoordToIndex(17, 0);
        else return Item.iconCoordToIndex(17, 1);
    }

        public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {

            if ( entityplayer.motionY > 0.5F ) return itemstack;

            if ( itemstack.getMetadata() >= this.getMaxDamage() ) return itemstack;
                else itemstack.damageItem(1,entityplayer);

            ((RandomOdditiesEntityMixin) entityplayer).setFallDistance(0f);

            entityplayer.motionY = 0.5F;

            world.playSoundEffect(entityplayer.posX + 0.5,entityplayer.posY + 0.5,entityplayer.posZ + 0.5, "random.bow", 0.3F, 0.6F);

            for (int i = 0; i < 8 + this.random.nextInt(7); i++){
                world.spawnParticle("snowballpoof",
                    entityplayer.posX,
                entityplayer.posY - 1,
                    entityplayer.posZ,
                    (this.random.nextFloat() - 0.5F),
                    (this.random.nextFloat() - 0.8F),
                    (this.random.nextFloat() - 0.5F));
                }

            return itemstack;
        }


    }
