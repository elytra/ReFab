package com.elytradev.refab.item;

import com.elytradev.refab.util.EFabCraftingPattern;
import com.raoulvdberge.refinedstorage.api.autocrafting.ICraftingPattern;
import com.raoulvdberge.refinedstorage.api.autocrafting.ICraftingPatternContainer;
import com.raoulvdberge.refinedstorage.api.autocrafting.ICraftingPatternProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEFabCraftingPattern extends ItemBase implements ICraftingPatternProvider {

    public ItemEFabCraftingPattern() {
        super("efab_crafting_pattern",1);
    }

    @Override
    public ICraftingPattern create(World world, ItemStack stack, ICraftingPatternContainer container) {
        return new EFabCraftingPattern(world, container, stack);
    }

    public static boolean isOredict(ItemStack pattern) {
        return pattern.hasTagCompound() && pattern.getTagCompound().hasKey("IsOredict") && pattern.getTagCompound().getBoolean("IsOredict");
    }

}
