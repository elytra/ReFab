package com.elytradev.refab;

import com.elytradev.concrete.inventory.IContainerInventoryHolder;
import com.elytradev.concrete.inventory.gui.client.ConcreteGui;
import com.elytradev.refab.block.ModBlocks;
import com.elytradev.refab.client.ReFabTab;
import com.elytradev.refab.item.ModItems;
import com.elytradev.refab.proxy.CommonProxy;
import com.elytradev.refab.util.ReFabLog;
import com.elytradev.refab.util.ReFabRecipes;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;


@Mod(modid = ReFab.modId, name = ReFab.name, version = ReFab.version)
public class ReFab {
    public static final String modId = "refab";
    public static final String name  = "ReFab";
    public static final String version = "@VERSION@";


    @Mod.Instance(modId)
    public static ReFab instance;

    public static final ReFabTab creativeTab = new ReFabTab();

    @SidedProxy(serverSide = "com.elytradev.refab.proxy.CommonProxy", clientSide = "com.elytradev.refab.proxy.ClientProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ReFabLog.info(name + " is loading!");

        MinecraftForge.EVENT_BUS.register(ReFabRecipes.class);
//        NetworkRegistry.INSTANCE.registerGuiHandler(this, new IGuiHandler() {
//            public static final int DISTILLER = 0;
//            @Nullable
//            @Override
//            public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//                switch (ID) {
//                    case DISTILLER:
//                        return new DistillerContainer(
//                                player.inventory, ((IContainerInventoryHolder)world.getTileEntity(new BlockPos(x,y,z))).getContainerInventory(),
//                                (TileEntityDistiller)world.getTileEntity(new BlockPos(x,y,z)));
//                    default:
//                        return null;
//                }
//
//            }
//
//            @Nullable
//            @Override
//            @SideOnly(Side.CLIENT)
//            public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//                switch (ID) {
//                    case DISTILLER:
//                        DistillerContainer distillerContainer = new DistillerContainer(
//                                player.inventory, ((IContainerInventoryHolder)world.getTileEntity(new BlockPos(x,y,z))).getContainerInventory(),
//                                (TileEntityDistiller)world.getTileEntity(new BlockPos(x,y,z)));
//                        return new ConcreteGui(distillerContainer);
//                    default:
//                        return null;
//                }
//
//            }
//        });
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        //MinecraftForge.EVENT_BUS.register(new SoundRegisterListener());
        //MinecraftForge.EVENT_BUS.register(LightHandler.class);
        ModItems.registerOreDict(); // register oredict
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerModels();
        }

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.register(event.getRegistry());
        }
    }
}
