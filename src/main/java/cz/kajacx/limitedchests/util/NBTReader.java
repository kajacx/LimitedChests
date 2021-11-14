package cz.kajacx.limitedchests.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public class NBTReader {
    public static int TYPE_BYTE = 99;
    public static int TYPE_SHORT = 99;
    public static int TYPE_INT = 99;
    public static int TYPE_LONG = 99;
    public static int TYPE_FLOAT = 99;
    public static int TYPE_DOUBLE = 99;
    public static int TYPE_BYTE_ARR = 99;
    public static int TYPE_STRING = 99;
    public static int TYPE_LIST = 99;
    public static int TYPE_COMPOUND = 99;
    public static int TYPE_INT_ARR = 99;

    public static int readIntOr(CompoundNBT tag, String name, int orDefault) {
        if (tag == null || !tag.contains(name, TYPE_INT)) {
            return orDefault;
        }
        return tag.getInt(name);
    }

    public static float readFloatOr(CompoundNBT tag, String name, float orDefault) {
        if (tag == null || !tag.contains(name, TYPE_FLOAT)) {
            return orDefault;
        }
        return tag.getFloat(name);
    }

    public static double readDoubleOr(CompoundNBT tag, String name, double orDefault) {
        if (tag == null || !tag.contains(name, TYPE_DOUBLE)) {
            return orDefault;
        }
        return tag.getDouble(name);
    }

    public static String readStringOr(CompoundNBT tag, String name, String orDefault) {
        if (tag == null || !tag.contains(name, TYPE_STRING)) {
            return orDefault;
        }
        return tag.getString(name);
    }

    public static CompoundNBT readTagOr(CompoundNBT tag, String name, CompoundNBT orDefault) {
        if (tag == null || !tag.contains(name, TYPE_COMPOUND)) {
            return orDefault;
        }
        return tag.getCompound(name);
    }

    public static ListNBT readListOr(CompoundNBT tag, String name, ListNBT orDefault, int listType) {
        if (tag == null || !tag.contains(name, TYPE_LIST)) {
            return orDefault;
        }
        return tag.getList(name, listType);
    }

    public static CompoundNBT putBlockPosWithPrefix(CompoundNBT tag, BlockPos pos, String prefix) {
        tag.putInt(prefix + "X", pos.getX());
        tag.putInt(prefix + "Y", pos.getY());
        tag.putInt(prefix + "Z", pos.getZ());
        return tag;
    }

    public static BlockPos readBlockPosWithPrefix(CompoundNBT tag, String prefix) {
        int x = NBTReader.readIntOr(tag, prefix + "X", 0);
        int y = NBTReader.readIntOr(tag, prefix + "Y", 0);
        int z = NBTReader.readIntOr(tag, prefix + "Z", 0);
        return new BlockPos(x, y, z);
    }

    public static CompoundNBT putVec3fWithPrefix(CompoundNBT tag, Vector3f vec3f, String prefix) {
        tag.putFloat(prefix + "X", vec3f.x());
        tag.putFloat(prefix + "Y", vec3f.y());
        tag.putFloat(prefix + "Z", vec3f.z());
        return tag;
    }

    public static Vector3f readVec3fWithPrefix(CompoundNBT tag, String prefix) {
        float x = NBTReader.readFloatOr(tag, prefix + "X", 0);
        float y = NBTReader.readFloatOr(tag, prefix + "Y", 0);
        float z = NBTReader.readFloatOr(tag, prefix + "Z", 0);
        return new Vector3f(x, y, z);
    }

    public static ItemStack loadItemStackFromNBT(CompoundNBT nbt) {
        ItemStack itemstack = ItemStack.of(nbt);
        return itemstack.getItem() != null ? itemstack : ItemStack.EMPTY.copy();
    }
}
