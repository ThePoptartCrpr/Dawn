package com.thepoptartcrpr.dawn.events;

import com.thepoptartcrpr.dawn.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockEvents {

    @SubscribeEvent
    public void onBlockDrop(BlockEvent.HarvestDropsEvent event) {
        Block block = event.getState().getBlock();

        // TODO: Below code throws NullPointerException due to ininstantiated ItemStack on heldItem being passed in, handle correctly

        // ItemStack heldItem = event.getHarvester().getHeldItemMainhand();

        /*if (block == Blocks.GRAVEL && !(heldItem.getItem().getToolClasses(heldItem).contains("shovel"))) {
            Utils.getConsole().info(event.getDrops().toString());
            event.getDrops().clear();
        }*/
    }

}
