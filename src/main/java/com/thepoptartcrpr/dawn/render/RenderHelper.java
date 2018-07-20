package com.thepoptartcrpr.dawn.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class RenderHelper {

    public static void renderItem(TileEntity te, ItemStack stack, float x, float y, float z) {
        GlStateManager.translate(x, y, z);
        GlStateManager.pushMatrix();

        GlStateManager.scale(1, 1, 1);

        Minecraft.getMinecraft().getRenderItem().renderItem(stack.copy(), ItemCameraTransforms.TransformType.FIXED);
        GlStateManager.popMatrix();
    }

    public static void renderItem(TileEntity te, ItemStack stack, float x, float y, float z, float xScale, float yScale, float zScale) {
        GlStateManager.translate(x, y, z);
        GlStateManager.pushMatrix();

        GlStateManager.scale(xScale, yScale, zScale);

        Minecraft.getMinecraft().getRenderItem().renderItem(stack.copy(), ItemCameraTransforms.TransformType.FIXED);
        GlStateManager.popMatrix();
    }

}
