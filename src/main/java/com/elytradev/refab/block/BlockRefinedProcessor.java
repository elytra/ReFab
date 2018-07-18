package com.elytradev.refab.block;

import com.elytradev.refab.tile.TileEntityRefinedProcessor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

public class BlockRefinedProcessor extends BlockTileEntity<TileEntityRefinedProcessor> {

    public BlockRefinedProcessor() {
        super(Material.IRON, "refined_processor");
    }

    @Override
    public Class<TileEntityRefinedProcessor> getTileEntityClass() {
        return TileEntityRefinedProcessor.class;
    }

    @Override
    public TileEntityRefinedProcessor createTileEntity(World world, IBlockState state) {
        return new TileEntityRefinedProcessor();
    }

}
