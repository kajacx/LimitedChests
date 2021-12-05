package cz.kajacx.limitedchests.tile;

import cz.kajacx.limitedchests.limit.ILimitableTile;
import cz.kajacx.limitedchests.limit.InventoryLimits;
import net.minecraft.block.BlockState;
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

    @Override
    public InventoryLimits getLimits() {
        return limits;
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        limits.save(compound);  
        return compound;
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        limits.load(compound);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        if (!canPlaceItem(index, stack)) {
            return;
        }

        super.setItem(index, stack);
        
        int maxSpace = limits.getMaxSize(index, stack);
        if (maxSpace < stack.getCount()) {
            stack.setCount(maxSpace);
        }
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return limits.getMaxSize(index, stack) > 0;
    }

}
