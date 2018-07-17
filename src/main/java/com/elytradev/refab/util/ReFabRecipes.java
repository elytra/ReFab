package com.elytradev.refab.util;

import com.elytradev.refab.block.ModBlocks;
import com.elytradev.refab.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

public class ReFabRecipes {

    @SubscribeEvent
    public static void onRegisterRecipes(RegistryEvent.Register<IRecipe> event) {

        IForgeRegistry<IRecipe> r = event.getRegistry();

        // Crafting bench recipes

//        recipe(r, new ShapedOreRecipe(new ResourceLocation("opaline:blocks"), new ItemStack(ModBlocks.DISTILLER, 1),
//                "bc ", "g b", "sss",
//                'b', new ItemStack(Items.GLASS_BOTTLE),
//                's', new ItemStack(Blocks.STONE_SLAB),
//                'c', "ingotCopper",
//                'g', "glass"));

    }


    public static <T extends IRecipe> T recipe(IForgeRegistry<IRecipe> registry, T t) {
        t.setRegistryName(new ResourceLocation(t.getRecipeOutput().getItem().getRegistryName()+"_"+t.getRecipeOutput().getItemDamage()));
        registry.register(t);
        return t;
    }
}
