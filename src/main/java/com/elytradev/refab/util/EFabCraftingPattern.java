package com.elytradev.refab.util;

import com.elytradev.refab.item.ItemEFabCraftingPattern;
import com.raoulvdberge.refinedstorage.api.autocrafting.ICraftingPattern;
import com.raoulvdberge.refinedstorage.api.autocrafting.ICraftingPatternContainer;
import com.raoulvdberge.refinedstorage.apiimpl.API;
import com.raoulvdberge.refinedstorage.apiimpl.autocrafting.CraftingPattern;
import com.raoulvdberge.refinedstorage.apiimpl.autocrafting.registry.CraftingTaskFactory;
import com.raoulvdberge.refinedstorage.item.ItemPattern;
import mcjty.efab.recipes.IEFabRecipe;
import mcjty.efab.recipes.RecipeManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EFabCraftingPattern implements ICraftingPattern {
    private ICraftingPatternContainer container;
    private ItemStack stack;
    private boolean processing;
    private boolean oredict;
    private boolean valid;
    private IEFabRecipe recipe;
    private List<NonNullList<ItemStack>> inputs = new ArrayList<>();
    private NonNullList<ItemStack> outputs = NonNullList.create();
    private NonNullList<ItemStack> byproducts = NonNullList.create();

    public EFabCraftingPattern(World world, ICraftingPatternContainer container, ItemStack stack) {
        this.container=container;
        this.stack=stack;
        this.oredict = ItemEFabCraftingPattern.isOredict(stack);

        InventoryCrafting inv = new InventoryCraftingDummy();

        for (int i = 0; i < 9; ++i) {
            ItemStack input = ItemPattern.getInputSlot(stack, i);

            inputs.add(input == null ? NonNullList.create() : NonNullList.from(ItemStack.EMPTY, input));

            if (input != null) {
                inv.setInventorySlotContents(i, input);
            }
        }

        valid = false;
        List<IEFabRecipe> validRecipes = RecipeManager.findValidRecipes(inv, world);
        if (validRecipes.size() > 0) {
            IEFabRecipe value = validRecipes.get(0);
            IRecipe recipe = value.cast();
            ItemStack output = recipe.getCraftingResult(inv);
            if (!output.isEmpty()) {
                outputs.add(output);
                valid = true;
            }
        }

    }

    @Override
    public ICraftingPatternContainer getContainer() {
        return container;
    }

    @Override
    public ItemStack getStack() {
        return stack;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean isProcessing() {
        return false;
    }

    @Override
    public boolean isOredict() {
        return oredict;
    }

    @Override
    public List<NonNullList<ItemStack>> getInputs() {
        return inputs;
    }

    @Override
    public NonNullList<ItemStack> getOutputs() {
        return null;
    }

    @Override
    public ItemStack getOutput(NonNullList<ItemStack> took) {
        return null;
    }

    @Override
    public NonNullList<ItemStack> getByproducts() {
        return null;
    }

    @Override
    public NonNullList<ItemStack> getByproducts(NonNullList<ItemStack> took) {
        return null;
    }

    @Override
    public String getId() {
        return CraftingTaskFactory.ID;
    }

    @Override
    public boolean canBeInChainWith(ICraftingPattern other) {
        return false;
    }

    @Override
    public int getChainHashCode() {
        int result = 0;

        result = 31 * result + (processing ? 1 : 0);
        result = 31 * result + (oredict ? 1 : 0);

        for (List<ItemStack> inputs : this.inputs) {
            for (ItemStack input : inputs) {
                result = 31 * result + API.instance().getItemStackHashCode(input);
            }
        }

        for (ItemStack output : this.outputs) {
            result = 31 * result + API.instance().getItemStackHashCode(output);
        }

        for (ItemStack byproduct : this.byproducts) {
            result = 31 * result + API.instance().getItemStackHashCode(byproduct);
        }

        return result;
    }

    private class InventoryCraftingDummy extends InventoryCrafting {
        public InventoryCraftingDummy() {
            super(new Container() {
                /**
                 * Determines whether supplied player can use this container
                 */
                @Override
                public boolean canInteractWith(EntityPlayer player) {
                    return true;
                }
            }, 3, 3);
        }
    }
}
