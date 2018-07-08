package com.thepoptartcrpr.dawn.creativetabs;

import com.thepoptartcrpr.dawn.init.DawnItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabDawn extends CreativeTabs {

    public TabDawn() {
        super("dawn");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(DawnItems.flintHatchet);
    }

}
