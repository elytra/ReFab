package com.elytradev.refab.block;

import com.elytradev.refab.ReFab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

    public static BlockRefinedProcessor REFINED_PROCESSOR = new BlockRefinedProcessor();

    public static Block[] allBlocks = {
        REFINED_PROCESSOR
    };

    public static void register(IForgeRegistry<Block> registry) {
        for (Block block : allBlocks) {
            registry.register(block);
        }

        GameRegistry.registerTileEntity(REFINED_PROCESSOR.getTileEntityClass(), REFINED_PROCESSOR.getRegistryName().toString());
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        for (Block block: allBlocks) {
            if (block instanceof IBlockBase) registry.register(((IBlockBase)block).createItemBlock());
            else registry.register(new ItemBlock(block));
        }

    }

    public static void registerModels() {
        for (Block block: allBlocks) {
            if (block instanceof IBlockBase) ((IBlockBase)block).registerItemModel(Item.getItemFromBlock(block));
        }
    }
}
