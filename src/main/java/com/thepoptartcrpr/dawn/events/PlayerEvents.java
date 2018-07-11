package com.thepoptartcrpr.dawn.events;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.ArrayUtils;

public class PlayerEvents {

    @SubscribeEvent
    public void onBlockBreaking(PlayerEvent.BreakSpeed event) {
        Block block = event.getState().getBlock();
        Item heldItem = event.getEntityPlayer().getHeldItemMainhand().getItem();
        ItemStack heldItemStack = event.getEntityPlayer().getHeldItemMainhand();

        if (block.getUnlocalizedName().matches("tile.dynamictrees:[a-z]+branch")) {
            if (!(heldItem.getToolClasses(heldItemStack).contains("axe"))) event.setNewSpeed(0);
        }
        else if (ArrayUtils.contains(OreDictionary.getOreIDs(new ItemStack(block)), OreDictionary.getOreID("logWood"))) {
            if (!(heldItem.getToolClasses(heldItemStack).contains("axe"))) event.setNewSpeed(0);
        }
    }

}
