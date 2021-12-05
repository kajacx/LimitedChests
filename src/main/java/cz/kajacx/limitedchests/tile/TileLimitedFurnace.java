package cz.kajacx.limitedchests.tile;

import cz.kajacx.limitedchests.container.ContainerLimitedFurnace;
import cz.kajacx.limitedchests.limit.ILimitableTile;
import cz.kajacx.limitedchests.limit.InventoryLimits;
import cz.kajacx.limitedchests.override.Overrides;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.FurnaceTileEntity;

public class TileLimitedFurnace extends FurnaceTileEntity implements ILimitableTile {
    
    private InventoryLimits limits;

    public TileLimitedFurnace() {
        limits = new InventoryLimits(this.getContainerSize());
    }
    
    @Override
    protected Container createMenu(int windowId, PlayerInventory playerInventory) {
        return new ContainerLimitedFurnace(windowId, playerInventory);
    }

    @Override
    public InventoryLimits getLimits() {
        return limits;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        Overrides.setItem(super::setItem, limits, index, stack);
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return Overrides.canPlaceItem(super::canPlaceItem, limits, index, stack);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        return Overrides.save(super::save, limits, compound);
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        Overrides.load(super::load, limits, state, compound);
    }
    
}
