package com.thepoptartcrpr.dawn.blocks;

import com.thepoptartcrpr.dawn.Dawn;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWoodenStrainer extends Block {

    public AxisAlignedBB bounds;

    public BlockWoodenStrainer() {
        // Sneaky workaround so you can't place two on top of the other
        super(Material.CIRCUITS);

        this.setUnlocalizedName("wooden_strainer");
        this.setRegistryName(new ResourceLocation(Dawn.Reference.MODID, "wooden_strainer"));

        this.bounds = new AxisAlignedBB(1.5D/16.0D, 0.0D, 1.5D/16.0D, 14.5D/16.0D, 4.0D/16.0D, 14.5D/16.0D);
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        return (world.getBlockState(pos.down()).getMaterial().isSolid() && super.canPlaceBlockAt(world, pos));
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

}
