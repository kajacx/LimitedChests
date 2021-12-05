package cz.kajacx.limitedchests.override;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;

public interface ILoad {
    public void load(BlockState state, CompoundNBT compound);
}
