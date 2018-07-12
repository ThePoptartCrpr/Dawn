package com.thepoptartcrpr.dawn.proxy;

import com.thepoptartcrpr.dawn.render.TileEntityWoodenStrainerRenderer;
import com.thepoptartcrpr.dawn.tileentity.TileEntityWoodenStrainer;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodenStrainer.class, new TileEntityWoodenStrainerRenderer());

        super.preInit();
    }

    @Override
    public void init() {
        super.init();
    }

}
