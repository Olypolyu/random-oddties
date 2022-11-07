package Olypolyu.randomoddities;

import Olypolyu.randomoddities.entities.EntityBoar;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.*;
import net.minecraft.src.*;



public class RandomOddities implements ModInitializer {
    public static final String MOD_ID = "Kheprep's Emporium of Random Oddities.";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static String name(String name) {
        return RandomOddities.MOD_ID + "." + name;
    }

    @Override
    public void onInitialize() {
        LOGGER.info("RandomOddities initialized.");
        EntityHelper.createEntity(EntityBoar.class, new RenderLiving(new ModelQuadruped(6, 0), 0.5f), 61, "Boar");
    }

}
