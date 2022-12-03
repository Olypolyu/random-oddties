package Olypolyu.randomoddities.mixin;

import Olypolyu.randomoddities.RandomOddities;
import Olypolyu.randomoddities.blocks.BlockBeans;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemDye.class, remap = false)
public class ItemDyeMixin{

    @Inject(method = "onItemUse", at = @At("TAIL"))
    private boolean callOnItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, double heightPlaced, CallbackInfoReturnable<Boolean> info){

        if (itemstack.getMetadata() == 3) {

            if ( BlockBeans.growsOn.contains(Block.getBlock( world.getBlockId(i, j, k) ) ) ) {

                switch (l) {
                    default:
                        return false;

                    case 2:
                        --k;
                        break;

                    case 3:
                        ++k;
                        break;

                    case 4:
                        --i;
                        break;

                    case 5:
                        ++i;
                        break;
                    }
                --itemstack.stackSize;
                world.setBlockAndMetadataWithNotify( i, j, k, RandomOddities.CocoBeans.blockID, l );
                return true;
                }
            }
        return false;
        }

}
