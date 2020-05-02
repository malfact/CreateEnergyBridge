package net.malfact.createenergybridge;

import net.malfact.createenergybridge.blocks.DynamoBlock;
import net.malfact.createenergybridge.init.ModBlocks;
import net.malfact.createenergybridge.init.ModItemGroups;
import net.malfact.createenergybridge.tileentity.DynamoTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = CreateEnergyBridge.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(
                setup(new DynamoBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(3.5F)), "dynamo")
        );
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event){
    final IForgeRegistry<Item> registry = event.getRegistry();

        registry.registerAll(
                setup(new Item(new Item.Properties().group(ModItemGroups.CREATE_ENERGY_BRIDGE_ITEM_GROUP)), "test_item")
        );

        for (final Block block : ForgeRegistries.BLOCKS.getValues()) {
            final ResourceLocation blockRegistryName = block.getRegistryName();

            if (!blockRegistryName.getNamespace().equals(CreateEnergyBridge.MOD_ID)){
                continue;
            }

            final Item.Properties properties = new Item.Properties().group(ModItemGroups.CREATE_ENERGY_BRIDGE_ITEM_GROUP);
            final BlockItem blockItem = new BlockItem(block, properties);

            registry.register(setup(blockItem, blockRegistryName));
        }
    }

    @SubscribeEvent
    public static void onRegisterTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
                setup(TileEntityType.Builder.create(DynamoTileEntity::new, ModBlocks.DYNAMO).build(null), "dynamo")
        );
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name){
        return setup(entry, new ResourceLocation(CreateEnergyBridge.MOD_ID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName){
        entry.setRegistryName(registryName);
        return entry;
    }
}
