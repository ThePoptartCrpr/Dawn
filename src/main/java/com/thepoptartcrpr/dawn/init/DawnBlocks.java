package com.thepoptartcrpr.dawn.init;

import com.thepoptartcrpr.dawn.Dawn;
import com.thepoptartcrpr.dawn.blocks.BlockWoodenStrainer;
import com.thepoptartcrpr.dawn.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DawnBlocks {

    public static Block woodenStrainer = new BlockWoodenStrainer();

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
            woodenStrainer.setCreativeTab(Dawn.getInstance().getCreativeTab())
        );
    }

    @SubscribeEvent
    public void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                blockToItem(woodenStrainer)
        );
    }

    @SubscribeEvent
    public void registerRenders(ModelRegistryEvent event) {
        registerRender(woodenStrainer);
    }

    private Item blockToItem(Block block) {
        ResourceLocation location = block.getRegistryName();
        return new ItemBlock(block).setRegistryName(location);
    }

    private void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        ResourceLocation location = item.getRegistryName();
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(location, "inventory"));
    }

}
