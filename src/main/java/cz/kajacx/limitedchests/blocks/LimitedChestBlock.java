package cz.kajacx.limitedchests.blocks;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.gui.LimitedChestsTab;
import cz.kajacx.limitedchests.gui.ModGuiHandler;
import cz.kajacx.limitedchests.tileentities.LimitedChest;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LimitedChestBlock extends BlockContainer {

    protected LimitedChestBlock(String unlocalizedName) {
        super(Material.WOOD);
        setUnlocalizedName(unlocalizedName);
        setRegistryName(unlocalizedName);
        setHardness(2.0f);
        setResistance(6.0f);
        setCreativeTab(LimitedChestsTab.instance);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new LimitedChest();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof IInventory) {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        EnumFacing facing = EnumFacing.fromAngle(placer.rotationYaw).getOpposite();

        // set display name
        if (stack.hasDisplayName()) {
            ((LimitedChest) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
        }

        // set facing
        ((LimitedChest) worldIn.getTileEntity(pos)).setFacing(facing);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(LimitedChests.instance, ModGuiHandler.LIMITED_CHEST_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult result, World world, BlockPos pos, EntityPlayer player) {
        ItemStack stack = new ItemStack(Item.getItemFromBlock(this), 1);
        if (world.getTileEntity(pos) instanceof LimitedChest) {
            LimitedChest te = (LimitedChest) world.getTileEntity(pos);
            if (te.hasCustomName()) {
                stack.setStackDisplayName(te.getCustomName());
            }
        }
        return stack;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

}
