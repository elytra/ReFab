package com.elytradev.refab.client;


import com.elytradev.refab.ReFab;
import com.elytradev.refab.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

public class ReFabTab extends CreativeTabs {
    public ReFabTab() {
        super(ReFab.modId);
        //setBackgroundImageName("opaline.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Blocks.HOPPER);
    }
}
