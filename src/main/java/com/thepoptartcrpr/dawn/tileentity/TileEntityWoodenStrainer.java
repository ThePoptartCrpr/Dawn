package com.thepoptartcrpr.dawn.tileentity;

import com.thepoptartcrpr.dawn.recipes.strainer.WoodenStrainerRecipe;
import com.thepoptartcrpr.dawn.utils.Utils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Random;

public class TileEntityWoodenStrainer extends TileEntity implements ITickable {

    private ItemStackHandler inventory = new ItemStackHandler(1);
    private Random rand = new Random();

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
                this.world.playSound(null, this.getPos(), sound, SoundCategory.BLOCKS, 1, 1);
                if ((rand.nextInt(3) + 1) == 3) this.finishRecipe();
            }
        }
    }

    public void finishRecipe() {
        Utils.getConsole().info(WoodenStrainerRecipe.getResult(this.inventory.getStackInSlot(0)));
        this.inventory.setStackInSlot(0, WoodenStrainerRecipe.getResult(this.inventory.getStackInSlot(0)));
    }

}
