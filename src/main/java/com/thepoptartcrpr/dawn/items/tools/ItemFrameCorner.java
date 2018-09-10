package com.thepoptartcrpr.dawn.items.tools;

import com.thepoptartcrpr.dawn.items.DawnItem;
import com.thepoptartcrpr.dawn.items.types.FrameCornerTypes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemFrameCorner extends DawnItem {

    public ItemFrameCorner(String name) {
        super(name);

        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        for (int i = 0; i < FrameCornerTypes.values().length; i++) {
            if (stack.getItemDamage() == i) return this.getUnlocalizedName() + "." + FrameCornerTypes.values()[i].getId();
        }
        return null;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
        if (!this.isInCreativeTab(tab)) return;

        for (int i = 0; i < FrameCornerTypes.values().length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

}
