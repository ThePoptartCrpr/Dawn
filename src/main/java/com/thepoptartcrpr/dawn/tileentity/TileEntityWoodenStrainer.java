package com.thepoptartcrpr.dawn.tileentity;

import com.thepoptartcrpr.dawn.recipes.strainer.WoodenStrainerRecipe;
import com.thepoptartcrpr.dawn.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Random;

public class TileEntityWoodenStrainer extends TileEntity implements ITickable {

    private ItemStackHandler inventory = new ItemStackHandler(1);
    private Random rand = new Random();

    private int clickBuffer = 0;

    public TileEntityWoodenStrainer() {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setTag("Inventory", inventory.serializeNBT());
        return super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        inventory.deserializeNBT(nbt.getCompoundTag("Inventory"));
        super.readFromNBT(nbt);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        if (!this.world.isRemote) {

        }
    }

    public void onClick() {
        if (!this.world.isRemote) {
            if (WoodenStrainerRecipe.isInputValid(this.inventory.getStackInSlot(0))) {
                SoundEvent sound = Blocks.GRAVEL.getSoundType(Blocks.GRAVEL.getDefaultState(), this.world, this.pos, null).getPlaceSound();
                this.world.playSound(null, this.getPos(), sound, SoundCategory.BLOCKS, 1, 0.8F);

                WorldServer serverWorld = (WorldServer) this.world;

                serverWorld.spawnParticle(
                        EnumParticleTypes.BLOCK_CRACK,
                        this.getPos().getX() + 0.5,
                        this.getPos().getY() + 0.4,
                        this.getPos().getZ() + 0.5,
                        3,
                        0.0D,
                        0.0D,
                        0.0D,
                        2,
                        Block.getStateId(Blocks.GRAVEL.getDefaultState())
                );

                this.clickBuffer++;
                if (clickBuffer <= WoodenStrainerRecipe.getRecipe(this.inventory.getStackInSlot(0)).getClickBuffer()) return;
                if ((rand.nextInt(3) + 1) == 3) this.finishRecipe();
            }
        }
    }

    public void finishRecipe() {
        ItemStack stack = new ItemStack(WoodenStrainerRecipe.getRecipe(this.inventory.getStackInSlot(0)).getOutput());
        this.inventory.setStackInSlot(0, ItemStack.EMPTY);
        this.clickBuffer = 0;
        EntityItem item = new EntityItem(world, pos.getX(), pos.getY() + 0.4, pos.getZ(), stack);
        world.spawnEntity(item);
        this.markDirty();
    }

}
