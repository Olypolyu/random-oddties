package Olypolyu.randomoddities.items;

import Olypolyu.randomoddities.mixin.RandomOdditiesEntityMixin;
import net.minecraft.src.*;

import java.util.Random;

public class ItemWindLamp extends Item {
    private final Random random = new Random();

    public ItemWindLamp(int i, int Charge) {
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
            entityplayer.air = ((RandomOdditiesEntityMixin) entityplayer).getMaxAir();
            entityplayer.motionY = 0.5F;

            world.playSoundEffect(entityplayer.posX + 0.5,entityplayer.posY + 0.5,entityplayer.posZ + 0.5, "random.fizz", 0.3F, 1.0F);

            for (int i = 0; i < 8 + this.random.nextInt(7); i++){

                // shows bubbles if the player is underwater.
                if ( entityplayer.isInWater() ) {
                    world.spawnParticle("bubble",
                            entityplayer.posX,
                            entityplayer.posY - 1,
                            entityplayer.posZ,
                            (this.random.nextFloat() - 0.5F),
                            (this.random.nextFloat() - 0.5F),
                            (this.random.nextFloat() - 0.5F));
                    } else

                        // shows smoke if the player is not underwater.
                        world.spawnParticle("largesmoke",
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
