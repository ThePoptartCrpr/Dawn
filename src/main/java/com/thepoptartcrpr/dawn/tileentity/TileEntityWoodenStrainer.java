package com.thepoptartcrpr.dawn.tileentity;

import com.thepoptartcrpr.dawn.recipes.strainer.WoodenStrainerRecipe;
import com.thepoptartcrpr.dawn.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Random;

public class TileEntityWoodenStrainer extends TileEntity {

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
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new SPacketUpdateTileEntity(this.pos, this.getBlockMetadata(), nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound nbt) {
        this.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound getTileData() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void markDirty() {
        final IBlockState state = getWorld().getBlockState(getPos());
        getWorld().notifyBlockUpdate(getPos(), state, state, 2);
        super.markDirty();
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
                        5,
                        Block.getStateId(Blocks.GRAVEL.getDefaultState())
                );

                this.clickBuffer++;
                if (clickBuffer <= WoodenStrainerRecipe.getRecipe(this.inventory.getStackInSlot(0)).getClickBuffer()) return;
                if ((rand.nextInt(3) + 1) == 3) this.finishRecipe();
            }
        }
    }

    public void finishRecipe() {
        this.getStack();
        ItemStack stack = new ItemStack(WoodenStrainerRecipe.getRecipe(this.inventory.getStackInSlot(0)).getOutput());
        this.inventory.setStackInSlot(0, ItemStack.EMPTY);
        this.clickBuffer = 0;
        EntityItem item = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ()+ 0.5, stack);
        world.spawnEntity(item);
        this.markDirty();
    }

    public ItemStack getStack() {
        return this.inventory.getStackInSlot(0);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

}
