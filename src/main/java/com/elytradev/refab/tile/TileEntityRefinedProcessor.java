package com.elytradev.refab.tile;

import com.raoulvdberge.refinedstorage.api.network.node.INetworkNodeManager;
import com.raoulvdberge.refinedstorage.api.network.node.INetworkNodeProxy;
import com.raoulvdberge.refinedstorage.apiimpl.API;
import com.raoulvdberge.refinedstorage.capability.CapabilityNetworkNodeProxy;
import mcjty.efab.blocks.grid.GridCrafterHelper;
import mcjty.efab.recipes.IEFabRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TileEntityRefinedProcessor extends TileEntity implements INetworkNodeProxy, ITickable {

//    private final GridCrafterHelper crafterHelper = new GridCrafterHelper(this);
//
//    @Nonnull
//    private List<IEFabRecipe> findCurrentRecipesSorted() {
//        List<IEFabRecipe> recipes = crafterHelper.findCurrentRecipes(getWorld());
//        recipes.sort((r1, r2) -> {
//            boolean error1 = getErrorsForOutput(r1, null);
//            boolean error2 = getErrorsForOutput(r2, null);
//            return error1 == error2 ? 0 : (error2 ? -1 : 1);
//        });
//        return recipes;
//    }

    //RS API

    CraftingNode node;
    CraftingNode clientNode;

    @Override
    public CraftingNode getNode() {
        if (world.isRemote) {
            if (clientNode == null) {
                clientNode = new CraftingNode(world, getPos());
            }
            return clientNode;
        }
        INetworkNodeManager manager = API.instance().getNetworkNodeManager(this.world);
        CraftingNode node = (CraftingNode) manager.getNode(this.pos);
        if (node == null) {
            manager.setNode(this.pos, node = new CraftingNode(world, getPos()));
            manager.markForSaving();
        }

        return node;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityNetworkNodeProxy.NETWORK_NODE_PROXY_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityNetworkNodeProxy.NETWORK_NODE_PROXY_CAPABILITY) {
            return (T) this;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void update() {

    }
}
