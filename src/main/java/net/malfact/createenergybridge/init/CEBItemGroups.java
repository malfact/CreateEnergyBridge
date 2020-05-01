package net.malfact.createenergybridge.init;

import net.malfact.createenergybridge.CreateEnergyBridge;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.function.Supplier;

public class CEBItemGroups {

    public static final ItemGroup CREATE_ENERGY_BRIDGE_ITEM_GROUP = new CEBItemGroup(CreateEnergyBridge.MOD_ID,
            () -> new ItemStack(CEBItems.TEST_ITEM));

    public static class CEBItemGroup extends ItemGroup {

        private final Supplier<ItemStack> iconSupplier;

        public CEBItemGroup(String name, Supplier<ItemStack> iconSupplier){
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack createIcon() {
            return iconSupplier.get();
        }
    }
}
