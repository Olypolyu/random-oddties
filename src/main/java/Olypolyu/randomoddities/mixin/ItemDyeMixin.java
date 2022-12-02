package Olypolyu.randomoddities.mixin;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemDye;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemDye.class, remap = false)
public class ItemDyeMixin{

    @Inject(method = "onItemUse", at = @At("HEAD"))
    private void callOnItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, double heightPlaced, CallbackInfoReturnable<Float> info){
        System.out.println("hi!");
    }

}
