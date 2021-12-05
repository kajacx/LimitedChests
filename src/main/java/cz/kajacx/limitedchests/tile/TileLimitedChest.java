package cz.kajacx.limitedchests.tile;

import cz.kajacx.limitedchests.container.ContainerLimitedChest;
import cz.kajacx.limitedchests.limit.ILimitableTile;
import cz.kajacx.limitedchests.limit.InventoryLimits;
import cz.kajacx.limitedchests.override.Overrides;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;

public class TileLimitedChest extends ChestTileEntity implements ILimitableTile {

    private InventoryLimits limits;

    public TileLimitedChest() {
        super(ModTiles.LIMITED_CHEST.get());

        limits = new InventoryLimits(this.getContainerSize());
        
        //test
        limits.setFilter(0, Items.DIAMOND, 10);
        limits.setRestrict(1, Items.CARROT, 11);
        limits.setCount(2, 12);
        for (int i = 3; i < 5; i++) {
            limits.setCount(i, i + 10);
        }
    }
    
    protected Container createMenu(int windowId, PlayerInventory playerInventory) {
        return ContainerLimitedChest.threeRows(windowId, playerInventory);
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
