package com.thepoptartcrpr.dawn.init;

import com.thepoptartcrpr.dawn.Dawn;
import com.thepoptartcrpr.dawn.items.tools.DawnHatchet;
import com.thepoptartcrpr.dawn.utils.Utils;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DawnItems {

    public static final ToolMaterial flintMaterial = EnumHelper.addToolMaterial(Dawn.Reference.MODID + ":flint", 1, 50, 2, 0.5F, 12);

    public static ItemAxe flintHatchet = new DawnHatchet(flintMaterial, "flint_hatchet");

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                flintHatchet.setCreativeTab(Dawn.getInstance().getCreativeTab())
        );
    }

    @SubscribeEvent
    public void registerRenders(ModelRegistryEvent event) {
        registerRender(flintHatchet);
    }

    private void registerRender(Item item) {
        ResourceLocation location = item.getRegistryName();
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(location, "inventory"));
    }

}
