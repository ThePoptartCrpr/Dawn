package com.thepoptartcrpr.dawn;

import com.thepoptartcrpr.dawn.creativetabs.TabDawn;
import com.thepoptartcrpr.dawn.events.BlockEvents;
import com.thepoptartcrpr.dawn.events.PlayerEvents;
import com.thepoptartcrpr.dawn.init.DawnBlocks;
import com.thepoptartcrpr.dawn.init.DawnItems;
import com.thepoptartcrpr.dawn.proxy.CommonProxy;
import com.thepoptartcrpr.dawn.recipes.strainer.WoodenStrainerRecipe;
import lombok.Getter;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Dawn.Reference.MODID, name = Dawn.Reference.NAME, version = Dawn.Reference.VERSION)
public class Dawn {

    @Mod.Instance @Getter
    private static Dawn instance;

    @SidedProxy(serverSide = Reference.SERVER_PROXY_CLASS, clientSide = Reference.CLIENT_PROXY_CLASS)
    public static CommonProxy proxy;

    @Getter
    private CreativeTabs creativeTab = new TabDawn();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
        proxy.registerTileEntities();

        MinecraftForge.EVENT_BUS.register(new DawnItems());
        MinecraftForge.EVENT_BUS.register(new DawnBlocks());

        WoodenStrainerRecipe.registerRecipes();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();

        MinecraftForge.EVENT_BUS.register(new BlockEvents());
        MinecraftForge.EVENT_BUS.register(new PlayerEvents());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    public static class Reference {
        public static final String NAME = "Dawn";
        public static final String MODID = "dawn";
        public static final String VERSION = "0.0.1";

        public static final String SERVER_PROXY_CLASS = "com.thepoptartcrpr.dawn.proxy.ServerProxy";
        public static final String CLIENT_PROXY_CLASS = "com.thepoptartcrpr.dawn.proxy.ClientProxy";
    }

}
