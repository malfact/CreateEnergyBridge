package net.malfact.createenergybridge;

import net.malfact.createenergybridge.init.CEBItemGroups;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = CreateEnergyBridge.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CEBEventSubscriber {

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event){

        event.getRegistry().registerAll(
                setup(new Item(new Item.Properties().group(CEBItemGroups.CREATE_ENERGY_BRIDGE_ITEM_GROUP)), "test_item")
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
