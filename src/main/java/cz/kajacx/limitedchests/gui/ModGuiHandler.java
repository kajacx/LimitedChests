package cz.kajacx.limitedchests.gui;

import cz.kajacx.limitedchests.tileentities.LimitedChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {

    public static final int LIMITED_CHEST_GUI = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        System.out.println("Getting getServerGuiElement");

        if (ID == LIMITED_CHEST_GUI) {
            TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
            if (entity instanceof LimitedChest) {
                System.out.println("ITS not NULL");
                return new ContainerLimitedChest(player.inventory, (LimitedChest) entity);
            }
        }
        System.out.println("ITS NULL");

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == LIMITED_CHEST_GUI) {
            TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
            if (entity instanceof LimitedChest) {
                return new GuiLimitedChest(player.inventory, (LimitedChest) entity);
            }
        }

        return null;
    }
}
