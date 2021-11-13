package cz.kajacx.limitedchests.tileentities;

import cz.kajacx.limitedchests.utils.Log;
import cz.kajacx.limitedchests.utils.NBTReader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class LimitedChest extends TileEntity implements ITickable, IInventory {

    private ItemStack[] inventory;

    private String customName;

    private EnumFacing facing;
    private int[] limit; //limit of items in each inventory
    private Item[] validItems; //certain item or null for any

    public LimitedChest() {
        super();

        inventory = new ItemStack[getSizeInventory()];
        limit = new int[getSizeInventory()];
        validItems = new Item[getSizeInventory()];
        facing = EnumFacing.getHorizontal(0);

        //test
        for (int i = 0; i < getSizeInventory(); i++) {
            limit[i] = i + 10;
        }

        validItems[0] = Items.DIAMOND;
        validItems[1] = Items.CARROT;

        //legit
        for (int i = 0; i < getSizeInventory(); i++) {
            if (validItems[i] != null) {
                inventory[i] = new ItemStack(validItems[i], 0);
            } else {
                inventory[i] = ItemStack.EMPTY.copy();
            }
        }
    }

    @Override
    public void setPos(BlockPos posIn)
    {
        if (posIn == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.setPos posIn: {}", posIn);
            return;
        }

        super.setPos(posIn);
    }

    public void setCustomName(String customName) {
        if (customName != null) {
            customName = customName.trim();
        }
        this.customName = customName;
    }

    public String getCustomName() {
        return customName;
    }

    public void setFacing(EnumFacing facing) {
        if (facing == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.setFacing facing: {}", facing);
            return;
        }

        this.facing = facing;
    }

    public EnumFacing getFacing() {
        return facing;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        if (compound == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.writeToNBT compound: {}", compound);
            compound = new NBTTagCompound();
        }

        super.writeToNBT(compound);

        NBTTagList list = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); ++i) {
            if (getStackInSlot(i) != null) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);
                getStackInSlot(i).writeToNBT(stackTag);
                list.appendTag(stackTag);
            }
        }

        compound.setTag("Items", list);

        if (hasCustomName()) {
            compound.setString("CustomName", customName);
        }

        compound.setInteger("Facing", facing.getHorizontalIndex());

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        if (compound == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.readFromNBT compound: {}", compound);
            compound = new NBTTagCompound();
        }

        super.readFromNBT(compound);

        NBTTagList list = NBTReader.readListOr(compound, "Items", new NBTTagList(), NBTReader.TYPE_COMPOUND);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot") & 255;
            setInventorySlotContents(slot, NBTReader.loadItemStackFromNBT(stackTag));
        }

        setCustomName(NBTReader.readStringOr(compound, "CustomName", null));

        int facingInt = compound.getInteger("Facing");
        // TODO: check invalid int values
        facing = EnumFacing.getHorizontal(facingInt);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound syncData = writeToNBT(new NBTTagCompound());
        return new SPacketUpdateTileEntity(pos, getBlockMetadata(), syncData);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public String getName() {
        return hasCustomName() ? customName : "container.limited_chest";
    }

    @Override
    public boolean hasCustomName() {
        return customName != null && !customName.equals("");
    }

    @Override
    public ITextComponent getDisplayName() {
        return hasCustomName() ? new TextComponentString(getName()) : new TextComponentTranslation(getName());
    }

    @Override
    public int getSizeInventory() {
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
    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= getSizeInventory()) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.getStackInSlot index: {}", index);
            return null;
        }
        return inventory[index];
    }

    @Override
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
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = getStackInSlot(index);
        setInventorySlotContents(index, ItemStack.EMPTY.copy());
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index < 0 || index >= getSizeInventory()) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.setInventorySlotContents index: {}", index);
            return;
        }

        if (stack == null || (stack.getItem() != null && stack.getCount() == 0)) {
            stack = ItemStack.EMPTY.copy();
        }

        if (validItems[index] != null && stack.getItem() != validItems[index]) {
            return;
        }

        if (stack.getCount() > getInventoryStackLimit()) {
            stack = stack.copy();
            stack.setCount(getInventoryStackLimit());
        }

        inventory[index] = stack;
        markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public int getInventoryStackLimit(int index) {
        if (index < 0 || index >= getSizeInventory()) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.getInventoryStackLimit index: {}", index);
            return getInventoryStackLimit();
        }
        return limit[index];
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        if (world == null) {
            Log.logger.warn(Log.badFieldsMarker, "LimitedChest.isUsableByPlayer world: {}", world);
            return false;
        }
        
        if (player == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.isUsableByPlayer player: {}", player);
            return false;
        }

        return world.getTileEntity(getPos()) == this && player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index < 0 || index >= getSizeInventory()) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.isItemValidForSlot index: {}", index);
            return false;
        }

        if (stack == null) {
            // TODO: really log message here?
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.isItemValidForSlot stack: {}", stack);
            stack = ItemStack.EMPTY.copy();
        }

        //work-around to fix broken hopper mechanics
        if (itemsInSlot(index) >= getInventoryStackLimit(index)) {
            return false;
        }

        return validItems[index] == null || validItems[index] == stack.getItem();
    }

    public int itemsInSlot(int index) {
        if (index < 0 || index >= getSizeInventory()) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChest.itemsInSlot index: {}", index);
            return 0;
        }

        return inventory[index].getCount();
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < getSizeInventory(); i++) {
            setInventorySlotContents(i, ItemStack.EMPTY.copy());
        }
    }

    @Override
    public void update() {

    }
}
