package com.thepoptartcrpr.dawn.render;

import com.thepoptartcrpr.dawn.tileentity.TileEntityWoodenStrainer;
import com.thepoptartcrpr.dawn.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWoodenStrainerRenderer extends TileEntitySpecialRenderer<TileEntityWoodenStrainer> {

    @Override
    public void render(TileEntityWoodenStrainer te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);

        // Utils.getConsole().info(te.getStack());

        if (!te.getStack().isEmpty()) this.renderItem(te, te.getStack(), 0.5F, 0.2F, 0.5F);

        GlStateManager.popMatrix();

        super.render(te, x, y, z, partialTicks, destroyStage, alpha);
    }

    private void renderItem(TileEntity te, ItemStack stack, float x, float y, float z) {
        GlStateManager.translate(x, y, z);
        GlStateManager.pushMatrix();

        GlStateManager.scale(1.37F, 0.2F, 1.37F);

        Minecraft.getMinecraft().getRenderItem().renderItem(stack.copy(), ItemCameraTransforms.TransformType.FIXED);
        GlStateManager.popMatrix();
    }

}
