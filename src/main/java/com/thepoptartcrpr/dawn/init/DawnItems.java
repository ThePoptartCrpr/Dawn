package com.thepoptartcrpr.dawn.init;

import com.thepoptartcrpr.dawn.Dawn;
import com.thepoptartcrpr.dawn.items.DawnItem;
import com.thepoptartcrpr.dawn.items.tools.ItemFrameCorner;
import com.thepoptartcrpr.dawn.items.tools.ItemHatchet;
import com.thepoptartcrpr.dawn.items.types.FrameCornerTypes;
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

    // Tool materials
    public static final ToolMaterial flintMaterial = EnumHelper.addToolMaterial(Dawn.Reference.MODID + ":flint", 1, 50, 2, 0.5F, 12);

    // Tools
    public static ItemAxe flintHatchet = new ItemHatchet(flintMaterial, "flint_hatchet");

    // Non-Utility Items
    public static Item meshWooden = new DawnItem("mesh_wooden");
    public static Item frameCorner = new ItemFrameCorner("frame_corner");
    public static Item frameWooden = new DawnItem("frame_wooden");

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                flintHatchet.setCreativeTab(Dawn.getInstance().getCreativeTab()),
                meshWooden.setCreativeTab(Dawn.getInstance().getCreativeTab()),
                frameCorner.setCreativeTab(Dawn.getInstance().getCreativeTab()),
                frameWooden.setCreativeTab(Dawn.getInstance().getCreativeTab())
        );
    }

    @SubscribeEvent
    public void registerRenders(ModelRegistryEvent event) {
        registerRender(flintHatchet);
        registerRender(meshWooden);
        for (int i = 0; i < FrameCornerTypes.values().length; i++) {
            registerRender(frameCorner, i, frameCorner.getRegistryName() + "_" + i);
        }
        registerRender(frameWooden);
    }

    private void registerRender(Item item) {
        ResourceLocation location = item.getRegistryName();
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(location, "inventory"));
    }

    private void registerRender(Item item, int meta, String location) {
        this.registerRender(item, meta, new ResourceLocation(location));
    }

    private void registerRender(Item item, int meta, ResourceLocation location) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(location, "inventory"));
    }

}
