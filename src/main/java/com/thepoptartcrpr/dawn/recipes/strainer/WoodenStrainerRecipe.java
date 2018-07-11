package com.thepoptartcrpr.dawn.recipes.strainer;

import com.thepoptartcrpr.dawn.utils.Utils;
import lombok.Getter;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class WoodenStrainerRecipe {

    private enum RecipeTypes {
        ITEM,
        FLUID
    }

    @Getter
    private final ItemStack input;

    @Getter
    private final Item output;

    @Getter
    private final RecipeTypes type;

    @Getter
    private final int clickBuffer;

    public static ArrayList<WoodenStrainerRecipe> recipeList = new ArrayList<>();

    public static void registerRecipes() {
        addRecipe(new ItemStack(Blocks.GRAVEL), Items.FLINT, RecipeTypes.ITEM, 3);
    }

    public WoodenStrainerRecipe(ItemStack input, Item output, RecipeTypes type, int clickBuffer) {
        this.input = input;
        this.output = output;
        this.type = type;
        this.clickBuffer = clickBuffer;
    }

    public static Item getResult(ItemStack stack) {
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

    public static WoodenStrainerRecipe getRecipe(ItemStack input) {
        for (WoodenStrainerRecipe recipe : recipeList) {
            Utils.getConsole().info("The stack is " + input + " and the recipe input is " + recipe.input + "The output is " + recipe.output + ". Do they match? " + ItemStack.areItemsEqual(recipe.input, input));
            if (ItemStack.areItemsEqual(recipe.input, input)) return recipe;
        }
        return null;
    }

    public static void addRecipe(ItemStack input, Item output, RecipeTypes type, int clickBuffer) {
        WoodenStrainerRecipe recipe = new WoodenStrainerRecipe(input, output, type, clickBuffer);
        recipeList.add(recipe);
    }

}
