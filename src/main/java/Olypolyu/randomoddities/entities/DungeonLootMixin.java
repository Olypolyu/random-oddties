package Olypolyu.randomoddities.entities;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

import java.util.HashMap;
import java.util.Random;

public class DungeonLootMixin {
    private static final HashMap<Integer, ItemStack> dungeonLoot;

    static {
        dungeonLoot =  new HashMap<Integer, ItemStack>(){{

            put(0, new ItemStack(Item.saddle) );
            put(1, new ItemStack(Item.ingotIron) );
            put(2, new ItemStack(Item.foodBread) );
            put(3, new ItemStack(Item.wheat) );
            put(4, new ItemStack(Item.sulphur) );
            put(5, new ItemStack(Item.string) );
            put(6, new ItemStack(Item.bucket) );
            put(7, new ItemStack(Item.foodAppleGold) );
            put(8, new ItemStack(Item.dustRedstone) );
            put(9, new ItemStack(Item.foodApple) );
            put(10, new ItemStack(Item.dye, 1, 3) );
            put(11, new ItemStack(Block.spongeDry) );
            put(12, new ItemStack(Item.bone) );

            //disks
            put(13, new ItemStack(Item.record13) );
            put(14, new ItemStack(Item.recordCat) );
            put(15, new ItemStack(Item.recordBlocks) );
            put(16, new ItemStack(Item.recordChirp) );
            put(17, new ItemStack(Item.recordFar) );
            put(18, new ItemStack(Item.recordMall) );
            put(19, new ItemStack(Item.recordMellohi) );
            put(20, new ItemStack(Item.recordStal) );
            put(21, new ItemStack(Item.recordStrad) );
            put(22, new ItemStack(Item.recordWard) );

            put(23, null);

        }};
    }

    private static final HashMap<ItemStack, Integer> dungeonLootRarity;

    static {
        dungeonLootRarity =  new HashMap<ItemStack, Integer>(){{

            put( new ItemStack(Item.foodAppleGold), 100);
            put( new ItemStack(Item.dustRedstone), 2);

            // disks
            put( new ItemStack(Item.record13), 100 );
            put( new ItemStack(Item.recordCat), 100 );
            put( new ItemStack(Item.recordBlocks), 100 );
            put( new ItemStack(Item.recordChirp), 100 );
            put( new ItemStack(Item.recordFar), 100 );
            put( new ItemStack(Item.recordMall), 100 );
            put( new ItemStack(Item.recordMellohi), 100 );
            put( new ItemStack(Item.recordStal), 100 );
            put( new ItemStack(Item.recordStrad), 100 );
            put( new ItemStack(Item.recordWard), 100 );
        }};
    }

    private static final HashMap<ItemStack, Integer> dungeonLootMaxQuantity;

    static {

        dungeonLootMaxQuantity = new HashMap<ItemStack, Integer>() {{
            put(new ItemStack(Item.ingotIron), 4);
            put(new ItemStack(Item.wheat), 4);
            put(new ItemStack(Item.sulphur), 4);
            put(new ItemStack(Item.string), 4);
            put(new ItemStack(Item.string), 4);
            put(new ItemStack(Item.dustRedstone), 4);
            put(new ItemStack(Block.spongeDry), 4);
            put(new ItemStack(Item.bone), 4);
        }};
    }



    public static ItemStack getDungeonLoot(int i){
        return dungeonLoot.get(i);
    }

    public static void addDungeonLoot(ItemStack itemStack){
        dungeonLoot.put( dungeonLoot.size(), itemStack );
    }

    public static void setRarity( ItemStack itemStack, int rarity) {
        dungeonLootRarity.put(itemStack, rarity);
    }



    public static ItemStack pickMobSpawner(Random random) {
        ItemStack returnStack = dungeonLoot.get(random.nextInt(dungeonLoot.size()));
        System.out.println(returnStack);

        if (dungeonLootRarity.get(returnStack) == null) {
            if ( dungeonLootMaxQuantity.get(returnStack) != null ) {
                System.out.println(1 + random.nextInt(dungeonLootMaxQuantity.get(returnStack)));
                returnStack.stackSize = 1 + random.nextInt(dungeonLootMaxQuantity.get(returnStack));
                }
            return returnStack;
            }

        if(random.nextInt(dungeonLootRarity.get(returnStack)) == 0) {
            System.out.println("DEBUG");
            if ( dungeonLootMaxQuantity.get(returnStack) != null ) {
                returnStack.stackSize = 1 + random.nextInt(dungeonLootMaxQuantity.get(returnStack));
                }
            return returnStack;
            } else return null;
    }

}
