package com.thepoptartcrpr.dawn.items;

import com.thepoptartcrpr.dawn.Dawn;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class DawnItem extends Item {

    public DawnItem(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(new ResourceLocation(Dawn.Reference.MODID, name));
    }

}
