package com.thepoptartcrpr.dawn.recipes.strainer;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class WoodenStrainerRecipe {

    private enum RecipeTypes {
        ITEM,
        FLUID
    }

    public final ItemStack input, output;

    public static ArrayList<WoodenStrainerRecipe> recipeList = new ArrayList<WoodenStrainerRecipe>();

    public static void registerRecipes() {
        addRecipe(new ItemStack(Blocks.GRAVEL), new ItemStack(Items.FLINT), RecipeTypes.ITEM);
    }

    public WoodenStrainerRecipe(ItemStack input, ItemStack output, RecipeTypes type) {
        this.input = input;
        this.output = output;
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

    public static void addRecipe(ItemStack input, ItemStack output, RecipeTypes type) {
        WoodenStrainerRecipe recipe = new WoodenStrainerRecipe(input, output, type);
        recipeList.add(recipe);
    }

}
