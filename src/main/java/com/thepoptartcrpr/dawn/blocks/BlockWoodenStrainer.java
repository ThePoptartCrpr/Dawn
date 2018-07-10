package com.thepoptartcrpr.dawn.blocks;

import com.thepoptartcrpr.dawn.Dawn;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;

public class BlockWoodenStrainer extends Block {

    public BlockWoodenStrainer() {
        super(Material.WOOD);

        this.setUnlocalizedName("wooden_strainer");
        this.setRegistryName(new ResourceLocation(Dawn.Reference.MODID, "wooden_strainer"));
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

}
