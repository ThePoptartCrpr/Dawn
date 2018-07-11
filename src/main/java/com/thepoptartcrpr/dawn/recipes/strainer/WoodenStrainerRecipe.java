package com.thepoptartcrpr.dawn.recipes.strainer;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class WoodenStrainerRecipe {

    private enum RecipeTypes {
        ITEM,
        FLUID
    }

    public final ItemStack input, output;

    public final RecipeTypes type;

    public final int clickBuffer;

    public static ArrayList<WoodenStrainerRecipe> recipeList = new ArrayList<>();

    public static void registerRecipes() {
        addRecipe(new ItemStack(Blocks.GRAVEL), new ItemStack(Items.FLINT), RecipeTypes.ITEM, 3);
    }

    public WoodenStrainerRecipe(ItemStack input, ItemStack output, RecipeTypes type, int clickBuffer) {
        this.input = input;
        this.output = output;
        this.type = type;
        this.clickBuffer = clickBuffer;
    }

    public static ItemStack getResult(ItemStack stack) {
        for (WoodenStrainerRecipe recipe : recipeList) {
            if (ItemStack.areItemsEqual(recipe.input, stack)) return recipe.output;
        }
        return null;
    }

    public static boolean isInputValid(ItemStack stack) {
        for (WoodenStrainerRecipe recipe : recipeList) {
            if (ItemStack.areItemsEqual(recipe.input, stack)) return true;
        }
        return false;
    }

    @Nullable
    public static RecipeTypes getRecipeType(ItemStack stack) {
        for (WoodenStrainerRecipe recipe : recipeList) {
            if (ItemStack.areItemsEqual(recipe.input, stack)) return recipe.type;
        }
        return null;
    }

    public static int getClickBuffer(ItemStack stack) {
        for (WoodenStrainerRecipe recipe : recipeList) {
            if (ItemStack.areItemsEqual(recipe.input, stack)) return recipe.clickBuffer;
        }
        return 0;
    }

    public static void addRecipe(ItemStack input, ItemStack output, RecipeTypes type, int clickBuffer) {
        WoodenStrainerRecipe recipe = new WoodenStrainerRecipe(input, output, type, clickBuffer);
        recipeList.add(recipe);
    }

}
