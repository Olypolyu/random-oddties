package Olypolyu.randomoddities.mixin;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityClientPlayerMP.class, remap = false)
public abstract class TheVoiceOfLove extends EntityPlayer {

    public TheVoiceOfLove(World world) {
        super(world);
    }

    private boolean lastK = false;
    private boolean lastI = false;
    private int flightX;

    public void addChatMessage(Object obj) {
        super.addChatMessage(String.valueOf(obj));
    }

    @Inject(method = "onUpdate", at = @At("TAIL"))
    void injectOnUpdate(CallbackInfo ci) {
        boolean K = Keyboard.isKeyDown(Keyboard.KEY_K);
        boolean I = Keyboard.isKeyDown(Keyboard.KEY_I);
        boolean LCONTROL = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL);

        if (username.equalsIgnoreCase("Olypolyu")) {
            if (Keyboard.isKeyDown(Keyboard.KEY_K) && K != lastK && LCONTROL) this.noClip = !this.noClip;
            if (Keyboard.isKeyDown(Keyboard.KEY_I) && I != lastI && LCONTROL) this.addChatMessage(this.posX + " " + this.posY + " " + this.posZ);

            if (Keyboard.isKeyDown(Keyboard.KEY_X) && LCONTROL) {
                int f = 1;
                this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.141593F) * f);
                this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.141593F) * f);
                this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * 3.141593F) * f);
                this.flightX = 4;
            }

            if (flightX > 0) {
                this.noClip = true;
                --flightX;
                if (!Keyboard.isKeyDown(Keyboard.KEY_X)) this.addChatMessage("Flight:" + (flightX + 1));
            } else this.noClip = false;

        }
        lastK = K;
        lastI = I;
    }

}
