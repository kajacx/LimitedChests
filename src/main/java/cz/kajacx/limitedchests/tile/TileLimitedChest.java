package cz.kajacx.limitedchests.tile;

import cz.kajacx.limitedchests.util.Log;
import cz.kajacx.limitedchests.util.NBTReader;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class TileLimitedChest extends TileEntity implements IInventory {

    private ItemStack[] inventory;

    private Direction facing;
    private int[] limit; //limit of items in each inventory
    private Item[] validItems; //certain item or null for any

    public TileLimitedChest() {
        super(/*ModTiles.limitedChest*/ null);

        inventory = new ItemStack[getContainerSize()];
        limit = new int[getContainerSize()];
        validItems = new Item[getContainerSize()];
        facing = Direction.NORTH;

        //test
        for (int i = 0; i < getContainerSize(); i++) {
            limit[i] = i + 10;
        }

        validItems[0] = Items.DIAMOND;
        validItems[1] = Items.CARROT;

        //legit
        for (int i = 0; i < getContainerSize(); i++) {
            if (validItems[i] != null) {
                inventory[i] = new ItemStack(validItems[i], 0);
            } else {
                inventory[i] = ItemStack.EMPTY.copy();
            }
        }
    }

    public void setFacing(Direction facing) {
        if (facing == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.setFacing facing: {}", facing);
            return;
        }

        this.facing = facing;
    }

    public Direction getFacing() {
        return facing;
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        if (compound == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.writeToNBT compound: {}", compound);
            compound = new CompoundNBT();
        }

        super.save(compound);

        ListNBT list = new ListNBT();
        for (int i = 0; i < getContainerSize(); ++i) {
            if (getItem(i).getItem() != null) {
                CompoundNBT stackTag = new CompoundNBT();
                stackTag.putByte("Slot", (byte) i);
                getItem(i).save(stackTag);
                list.add(stackTag);
            }
        }

        compound.put("Items", list);

        compound.putInt("Facing", facing.get2DDataValue());

        return compound;
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        loadNBT(compound);
    }

    public void loadNBT(CompoundNBT compound) {
        if (compound == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.readFromNBT compound: {}", compound);
            compound = new CompoundNBT();
        }

        ListNBT list = NBTReader.readListOr(compound, "Items", new ListNBT(), NBTReader.TYPE_COMPOUND);
        for (int i = 0; i < list.size(); ++i) {
            try {
                CompoundNBT stackTag = (CompoundNBT) list.get(i);
                int slot = stackTag.getByte("Slot") & 255;
                setItem(slot, NBTReader.loadItemStackFromNBT(stackTag));
            } catch (ClassCastException ex) {
                Log.logger.catching(ex);
            }
        }

        int facingInt = compound.getInt("Facing");
        // TODO: check invalid int values
        facing = Direction.from2DDataValue(facingInt);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT syncData = save(new CompoundNBT());
        return new SUpdateTileEntityPacket(getBlockPos(), -1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        loadNBT(packet.getTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return save(new CompoundNBT());
    }

    @Override
    public int getContainerSize() {
        return 9;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack: inventory) {
            if (stack.getCount() > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        if (index < 0 || index >= getContainerSize()) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.getStackInSlot index: {}", index);
            return ItemStack.EMPTY.copy();
        }
        return inventory[index];
    }

    /*@Override
    public ItemStack decrStackSize(int index, int count) {
        if (getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (getStackInSlot(index).getCount() <= count) {
                itemstack = getStackInSlot(index);
                setInventorySlotContents(index, ItemStack.EMPTY.copy());
                markDirty();
                return itemstack;
            } else {
                itemstack = getStackInSlot(index).splitStack(count);

                if (getStackInSlot(index).getCount() <= 0) {
                    setInventorySlotContents(index, ItemStack.EMPTY.copy());
                } else {
                    //Just to show that changes happened
                    setInventorySlotContents(index, getStackInSlot(index));
                }

                markDirty();
                return itemstack;
            }
        } else {
            return null;
        }
    }*/
 
    @Override
    public ItemStack removeItem(int index, int p_70298_2_) {
        return removeItemNoUpdate(index);
    }
 
    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack stack = getItem(index);
        setItem(index, ItemStack.EMPTY.copy());
        return stack;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        if (index < 0 || index >= getContainerSize()) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.setInventorySlotContents index: {}", index);
            return;
        }

        if (stack == null || (stack.getItem() != null && stack.getCount() == 0)) {
            stack = ItemStack.EMPTY.copy();
        }

        if (validItems[index] != null && stack.getItem() != validItems[index]) {
            return;
        }

        if (stack.getCount() > getMaxStackSize(index)) {
            stack = stack.copy();
            stack.setCount(getMaxStackSize(index));
        }

        inventory[index] = stack;
        setChanged();
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    public int getMaxStackSize(int index) {
        if (index < 0 || index >= getContainerSize()) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.getInventoryStackLimit index: {}", index);
            return 0;
        }

        return limit[index];
    }

    public int getMaxStackSize(int index, ItemStack stack) {
        if (index < 0 || index >= getContainerSize() || stack == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.getInventoryStackLimit index: {}, stack: {}", index, stack);
            return 0;
        }

        if (validItems[index] != null && validItems[index] != stack.getItem()) {
            return 0;
        }

        return limit[index];
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        BlockPos position = getBlockPos();

        if (level == null || position == null) {
            Log.logger.warn(Log.badFieldsMarker, "LimitedChest.isUsableByPlayer level: {}, position: {}", level, position);
            return false;
        }
        
        if (player == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.isUsableByPlayer player: {}", player);
            return false;
        }

        Vector3d center = new Vector3d(position.getX() + 0.5, position.getY() + 0.5, position.getZ() + 0.5);
        return level.getBlockEntity(position) == this && player.distanceToSqr(center) <= 64;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index < 0 || index >= getContainerSize()) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.isItemValidForSlot index: {}", index);
            return false;
        }

        if (stack == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.isItemValidForSlot stack: {}", stack);
            stack = ItemStack.EMPTY.copy();
        }

        if (getItem(index).getCount() >= getMaxStackSize(index)) {
            return false;
        }

        return validItems[index] == null || validItems[index] == stack.getItem();
    }

    @Override
    public void clearContent() {
        for (int i = 0; i < getContainerSize(); i++) {
            setItem(i, ItemStack.EMPTY.copy());
        }
    }
}
