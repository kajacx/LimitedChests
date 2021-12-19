package cz.kajacx.limitedchests.container;

import cz.kajacx.limitedchests.gui.LimitedSlot;
import cz.kajacx.limitedchests.limit.ILimitableTile;
import cz.kajacx.limitedchests.util.Log;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerLimitedFurnace extends AbstractFurnaceContainer {

    public ContainerLimitedFurnace(int windowId, PlayerInventory playerInventory, ILimitableTile container, IIntArray data) {
        super(ModContainers.LIMITED_FURNACE.get(), IRecipeType.SMELTING, RecipeBookCategory.FURNACE, windowId, playerInventory, container, data);
        
        // TODO: hard-coded constant 3? And what about the other furnace constructor?
        for (int i = 0; i < 3; i++) {
            slots.set(i, new LimitedSlot(slots.get(i)));
        }
    }

    public static ContainerLimitedFurnace create(int windowId, PlayerInventory inv, PacketBuffer data) {
        try (Log log = Log.enter("ContainerLimitedFurnace.create", windowId, inv, data)) {
            BlockPos pos = data.readBlockPos();
            World level = inv.player.level;

            TileEntity tile = level.getBlockEntity(pos);
            return new ContainerLimitedFurnace(windowId, inv, (ILimitableTile) tile, new IntArray(4));
        }
    }

}
