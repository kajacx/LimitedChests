package cz.kajacx.limitedchests.tile;

import cz.kajacx.limitedchests.container.ContainerLimitedFurnace;
import cz.kajacx.limitedchests.limit.ILimitableTile;
import cz.kajacx.limitedchests.limit.InventoryLimits;
import cz.kajacx.limitedchests.override.Overrides;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class TileLimitedFurnace extends AbstractFurnaceTileEntity implements ILimitableTile {
    
    private InventoryLimits limits;

    public TileLimitedFurnace() {
        super(ModTiles.LIMITED_FURNACE.get(), IRecipeType.SMELTING);
        limits = new InventoryLimits(this.getContainerSize());

        // TEST
        limits.setCount(0, 2);
        limits.setCount(1, 3);
    }

    protected ITextComponent getDefaultName() {
       return new TranslationTextComponent("block.limitedchests.limited_furnace");
    }
    
    @Override
    protected Container createMenu(int windowId, PlayerInventory playerInventory) {
        return new ContainerLimitedFurnace(windowId, playerInventory, this, this.dataAccess);
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
