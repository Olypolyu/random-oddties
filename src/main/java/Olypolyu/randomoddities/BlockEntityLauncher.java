package Olypolyu.randomoddities;

import Olypolyu.randomoddities.entities.TileEntityLauncher;
import net.minecraft.src.BlockContainerRotatable;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;

public class BlockEntityLauncher extends BlockContainerRotatable {

    private final double launchX;
    private final double launchY;
    private final double launchZ;

    public BlockEntityLauncher(int i, Material material, double launchX, double launchY, double launchZ) {
        super(i, material);
        this.setBlockBounds(0F, 0F, 0F, 1.0F, 0.5F, 1.0F);
        this.setLightOpacity(1);
        this.launchX = launchX;
        this.launchY = launchY;
        this.launchZ = launchZ;

    }

    public boolean isOpaqueCube() {
        return false;
    }

    protected TileEntity getBlockEntity() {
        TileEntityLauncher launcher = new TileEntityLauncher();
        launcher.setLauncherParameters(this.launchX, this.launchY, this.launchZ);
        return launcher;
    }
}