package com.thepoptartcrpr.dawn.items.tools;

import com.thepoptartcrpr.dawn.Dawn;
import net.minecraft.item.ItemAxe;
import net.minecraft.util.ResourceLocation;

public class DawnHatchet extends ItemAxe {

    public DawnHatchet(ToolMaterial material, String name) {
        super(material, material.getAttackDamage(), -3.5F);
        this.setUnlocalizedName(name);
        this.setRegistryName(new ResourceLocation(Dawn.Reference.MODID, name));
    }

}
