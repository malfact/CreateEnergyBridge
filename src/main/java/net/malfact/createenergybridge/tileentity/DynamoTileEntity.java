package net.malfact.createenergybridge.tileentity;

import com.simibubi.create.modules.contraptions.base.KineticTileEntity;
import net.malfact.createenergybridge.energy.SettableEnergyStorage;
import net.malfact.createenergybridge.init.ModTileEntityTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class DynamoTileEntity extends KineticTileEntity {

    private static final String ENERGY_TAG = "energy";

    public final SettableEnergyStorage energy = new SettableEnergyStorage(32);
    private final LazyOptional<EnergyStorage> energyCapabilityExternal = LazyOptional.of(() -> this.energy);

    private int lastEnergy = -1;
    private int generation = 0;

    public DynamoTileEntity() {
        super(ModTileEntityTypes.DYNAMO);
    }

    @Override
    public void tick() {
        super.tick();

        generation = (int)(speed/16F);

        if (speed > 0){
            energy.setEnergy(energy.getEnergyStored() + generation);

            markDirty();
        }
    }

    @Override
    public boolean addToGoggleTooltip(List<String> tooltip, boolean isPlayerSneaking) {
        super.addToGoggleTooltip(tooltip, isPlayerSneaking);

        tooltip.add("Stored FE: " + energy.getEnergyStored() + "/" + energy.getMaxEnergyStored());
        tooltip.add("FE/S: " + generation);
        return true;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (world != null && !world.isRemote)
            lastEnergy = energy.getEnergyStored();
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        this.energy.setEnergy(pkt.getNbtCompound().getInt(ENERGY_TAG));
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.energy.setEnergy(compound.getInt(ENERGY_TAG));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt(ENERGY_TAG, this.energy.getEnergyStored());
        return compound;
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        final CompoundNBT tag = new CompoundNBT();
        tag.putInt(ENERGY_TAG, this.energy.getEnergyStored());
        return new SUpdateTileEntityPacket(this.pos, 1, writeToClient(tag));
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return super.getUpdateTag();
    }

    @Override
    public void remove() {
        super.remove();
        energyCapabilityExternal.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY){
            return this.energyCapabilityExternal.cast();
        }
        return super.getCapability(cap, side);
    }

}
