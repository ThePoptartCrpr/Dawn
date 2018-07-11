package com.thepoptartcrpr.dawn.blocks;

import com.thepoptartcrpr.dawn.Dawn;
import com.thepoptartcrpr.dawn.recipes.strainer.WoodenStrainerRecipe;
import com.thepoptartcrpr.dawn.tileentity.TileEntityWoodenStrainer;
import com.thepoptartcrpr.dawn.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockWoodenStrainer extends Block implements ITileEntityProvider {

    public AxisAlignedBB bounds;

    public BlockWoodenStrainer() {
        // Sneaky workaround so you can't place two on top of the other
        // super(Material.CIRCUITS);
        super(Material.WOOD);

        this.setUnlocalizedName("wooden_strainer");
        this.setRegistryName(new ResourceLocation(Dawn.Reference.MODID, "wooden_strainer"));

        this.bounds = new AxisAlignedBB(1.5D/16.0D, 0.0D, 1.5D/16.0D, 14.5D/16.0D, 4.0D/16.0D, 14.5D/16.0D);
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        return (world.getBlockState(pos.down()).isFullCube() && super.canPlaceBlockAt(world, pos));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntityWoodenStrainer te = (TileEntityWoodenStrainer) world.getTileEntity(pos);
            IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
            ItemStack item = player.getHeldItemMainhand();

            Utils.getConsole().info(handler.getStackInSlot(0));

            if (!WoodenStrainerRecipe.isInputValid(item)) {
                te.onClick();
                return false;
            }

            if (handler.getStackInSlot(0).getItem() == Items.AIR && player.getHeldItemMainhand().getItem() != Items.AIR) {
                handler.insertItem(0, new ItemStack(item.getItem()), false);
                player.setHeldItem(hand, new ItemStack(item.getItem(), item.getCount() - 1));
            } else if (handler.getStackInSlot(0).getItem() != Items.AIR && item.getItem() == Items.AIR) {
                player.setHeldItem(hand, handler.extractItem(0, 1, false));
            }

            te.markDirty();
        }
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityWoodenStrainer te = (TileEntityWoodenStrainer) world.getTileEntity(pos);
        IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.SOUTH);
        ItemStack stack = handler.getStackInSlot(0);
        EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
        world.spawnEntity(item);
        super.breakBlock(world, pos, state);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return this.bounds;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return this.bounds;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return super.createTileEntity(world, getStateFromMeta(meta));
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityWoodenStrainer();
    }

}
