package com.thepoptartcrpr.dawn.blocks;

import com.thepoptartcrpr.dawn.Dawn;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockStump extends Block {

    public AxisAlignedBB bounds;

    public BlockStump() {
        super(Material.WOOD);

        this.setUnlocalizedName("stump");
        this.setRegistryName(new ResourceLocation(Dawn.Reference.MODID, "stump"));

        this.bounds = new AxisAlignedBB(1.0D/16.0D, 0.0D, 1.0D/16.0D, 15.0D/16.0D, 8.0D/16.0D, 15.0D/16.0D);

        this.setSoundType(SoundType.WOOD);
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
