package com.thepoptartcrpr.dawn;

import com.thepoptartcrpr.dawn.events.PlayerEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Dawn.Reference.MODID, name = Dawn.Reference.NAME, version = Dawn.Reference.VERSION)
public class Dawn {

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new PlayerEvents());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    public static class Reference {
        public static final String NAME = "Dawn";
        public static final String MODID = "dawn";
        public static final String VERSION = "0.0.1";
    }

}
