package Olypolyu.randomoddities;

import Olypolyu.randomoddities.mixin.RandomOdditiesEntityMixin;
import net.minecraft.src.*;

import java.util.Random;

public class BlockTrampoline extends Block {
    private final Random random = new Random();

    public BlockTrampoline(int i) {
        super(i, Material.web);
        this.setBlockBounds(0F, 0F, 0F, 1.0F, 0.5F, 1.0F);
        this.setLightOpacity(1);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
        // RandomOddities.LOGGER.info(entity.getDistance(i, j, k) + " " + entity);
        if (entity instanceof EntityLiving) {
            ((RandomOdditiesEntityMixin) entity).callFallDistance(0f);
            entity.motionY = entity.motionY + 1.5 - this.random.nextFloat();
            for (int i1 = 0; i1 < 8 + this.random.nextInt(7); i1++) {
                world.spawnParticle("flame",
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