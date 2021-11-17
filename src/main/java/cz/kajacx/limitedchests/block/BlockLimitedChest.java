package cz.kajacx.limitedchests.block;

import cz.kajacx.limitedchests.LimitedChests;
import cz.kajacx.limitedchests.gui.TabLimitedChests;
import cz.kajacx.limitedchests.tile.TileLimitedChest;
import cz.kajacx.limitedchests.util.Log;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockLimitedChest extends Block {

    protected BlockLimitedChest(Block.Properties properties) {
        super(properties);
        /*setUnlocalizedName(unlocalizedName);
        setRegistryName(unlocalizedName);
        setHardness(2.0f);
        setResistance(6.0f);
        setCreativeTab(LimitedChestsTab.instance);*/
    }

    /*@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new LimitedChest();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (worldIn == null || pos == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChestBlock.breakBlock worldIn: {}, pos: {}", worldIn, pos);
            return;
        }

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof IInventory) {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (worldIn == null || pos == null || placer == null || stack == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChestBlock.onBlockPlacedBy worldIn: {}, pos: {}, placer: {}, stack: {}", worldIn, pos, placer, stack);
            return;
        }

        EnumFacing facing = EnumFacing.fromAngle(placer.rotationYaw).getOpposite();

        // set display name
        if (stack.hasDisplayName()) {
            ((LimitedChest) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
        }

        // set facing
        ((LimitedChest) worldIn.getTileEntity(pos)).setFacing(facing);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, PlayerEntity playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn == null || pos == null || playerIn == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChestBlock.onBlockActivated worldIn: {}, pos: {}, playerIn: {}", worldIn, pos, playerIn);
            return false;
        }

        if (!worldIn.isRemote) {
            playerIn.openGui(LimitedChests.instance, ModGuiHandler.LIMITED_CHEST_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult result, World world, BlockPos pos, PlayerEntity player) {
        ItemStack stack = new ItemStack(Item.getItemFromBlock(this), 1);

        if (world == null || pos == null) {
            Log.logger.warn(Log.badArgsMarker, "LimitedChestBlock.getPickBlock world: {}, pos: {}, playerIn: {}", world, pos);
            return stack;
        }
        
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
    }*/
}
