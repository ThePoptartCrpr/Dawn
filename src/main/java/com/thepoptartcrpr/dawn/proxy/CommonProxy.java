package com.thepoptartcrpr.dawn.proxy;

import com.thepoptartcrpr.dawn.Dawn;
import com.thepoptartcrpr.dawn.tileentity.TileEntityWoodenStrainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void preInit() {

    }

    public void init() {

    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityWoodenStrainer.class, new ResourceLocation(Dawn.Reference.MODID, ":wooden_strainer"));
    }

}
