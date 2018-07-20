package com.thepoptartcrpr.dawn.render;

import com.thepoptartcrpr.dawn.tileentity.TileEntityWoodenStrainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class TileEntityWoodenStrainerRenderer extends TileEntitySpecialRenderer<TileEntityWoodenStrainer> {

    @Override
    public void render(TileEntityWoodenStrainer te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);

        if (!te.getStack().isEmpty()) RenderHelper.renderItem(te, te.getStack(), 0.5F, 0.2F, 0.5F, 1.37F, 0.2F, 1.37F);

        GlStateManager.popMatrix();

        super.render(te, x, y, z, partialTicks, destroyStage, alpha);
    }

}
