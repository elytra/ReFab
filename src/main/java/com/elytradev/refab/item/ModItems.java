package com.elytradev.refab.item;

import com.elytradev.refab.ReFab;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

//        public static ItemBase EXHAUSTED_LAPIS = new ItemBase("exhausted_lapis", "dyeBlue").setCreativeTab(ReFab.creativeTab);
//        public static ItemBase QUARTZ_LAPIS = new ItemBase("quartz_lapis").setCreativeTab(ReFab.creativeTab);

    public static ItemBase[] allItems = {
//            EXHAUSTED_LAPIS, QUARTZ_LAPIS
    };

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(allItems);
    }

    public static void registerModels() {
        for (int i = 0; i < allItems.length ; i++) {
            allItems[i].registerItemModel();
        }
    }

    public static void registerOreDict() {
        for (int i = 0; i < allItems.length ; i++) {
            allItems[i].initOreDict();
        }
    }
}
