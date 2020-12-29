package cz.kajacx.limitedchests.tileentities;

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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class LimitedChest extends TileEntity implements ITickable, IInventory {

    static final ItemStack EMPTY_STACK = new ItemStack((Item) null, 0);

    private ItemStack[] inventory;

    private String customName;

    private EnumFacing facing;
    private int[] limit; //limit of items in each inventory
    private Item[] validItems; //certain item or null for any

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public EnumFacing getFacing() {
        return facing;
    }

    public void setFacing(EnumFacing facing) {
        this.facing = facing;
    }

    public LimitedChest() {
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
                inventory[i] = EMPTY_STACK.copy();
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
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
        super.readFromNBT(compound);

        NBTTagList list = NBTReader.readListOr(compound, "Items", new NBTTagList(), NBTReader.TYPE_COMPOUND);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot") & 255;
            setInventorySlotContents(slot, NBTReader.loadItemStackFromNBT(stackTag));
        }

        setCustomName(NBTReader.readStringOr(compound, "CustomName", null));

        int facingInt = compound.getInteger("Facing");
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
        return this.hasCustomName() ? this.customName : "container.limited_chest";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.equals("");
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    @Override
    public int getSizeInventory() {
        return 9;
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack: inventory) {
            if (stack != null && stack.getCount() > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= this.getSizeInventory())
            return null;
        return this.inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (this.getStackInSlot(index).getCount() <= count) {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, EMPTY_STACK.copy());
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).getCount() <= 0) {
                    this.setInventorySlotContents(index, EMPTY_STACK.copy());
                } else {
                    //Just to show that changes happened
                    this.setInventorySlotContents(index, this.getStackInSlot(index));
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = this.getStackInSlot(index);
        this.setInventorySlotContents(index, EMPTY_STACK.copy());
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index < 0 || index >= this.getSizeInventory()) {
            return;
        }

        if (stack != null && stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (stack != null && stack.getCount() == 0) {
            stack = EMPTY_STACK.copy();
        }

        if (stack == null && validItems[index] != null) {
            stack = new ItemStack(validItems[index], 0);
        }

        this.inventory[index] = stack;
        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public int getInventoryStackLimit(int index) {
        if (index < 0 || index >= getSizeInventory()) {
            return getInventoryStackLimit();
        }
        return limit[index];
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (stack == null || index < 0 || index >= getSizeInventory()) {
            return false;
        }

        //work-around to fix broken hopper mechanics
        if (itemsInSlot(index) >= getInventoryStackLimit(index)) {
            return false;
        }

        return validItems[index] == null || validItems[index] == stack.getItem();
    }

    public int itemsInSlot(int index) {
        if (index < 0 || index >= getSizeInventory() || inventory[index] == null) {
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
        for (int i = 0; i < this.getSizeInventory(); i++) {
            this.setInventorySlotContents(i, EMPTY_STACK.copy());
        }
    }

    @Override
    public void update() {

    }
}
