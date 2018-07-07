package com.thepoptartcrpr.dawn.events;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerEvents {

    @SubscribeEvent
    public void onBlockBreaking(PlayerEvent.BreakSpeed event) {
        Block block = event.getState().getBlock();
        Item heldItem = event.getEntityPlayer().getHeldItemMainhand().getItem();
        ItemStack heldItemStack = event.getEntityPlayer().getHeldItemMainhand();
        if (block instanceof BlockLog) {
            if (!(heldItem.getToolClasses(heldItemStack).contains("axe"))) event.setNewSpeed(0);
        }
    }

}
