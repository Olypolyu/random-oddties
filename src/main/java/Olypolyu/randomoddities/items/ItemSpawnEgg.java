package Olypolyu.randomoddities.items;

import net.minecraft.src.*;
import net.minecraft.src.command.commands.SummonCommand;

public class ItemSpawnEgg extends Item {

    private final Class entityClass;

    public ItemSpawnEgg(int i, Class entity) {
        super(i);
        this.entityClass = entity;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, double heightPlaced) {
        j++; //helps with monsters spawning inside walls.
        switch (l) {
            case 0:
                --j;
                break;

            case 1:
                ++j;
                break;

            case 2:
                --k;
                break;

            case 3:
                k+=2;
                break;

            case 4:
                --i;
                break;

            case 5:
                i+=2;
                break;
            }

        Entity entity = SummonCommand.createEntity(this.entityClass, world);
        entity.entityInitOnSpawn();
        entity.setLocationAndAngles(i, j, k, 0.0F, 0.0F);
        world.entityJoinedWorld(entity);
        return true;
    }

}
