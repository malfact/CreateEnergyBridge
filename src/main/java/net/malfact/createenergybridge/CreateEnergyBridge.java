package net.malfact.createenergybridge;

import com.simibubi.create.config.AllConfigs;
import net.malfact.createenergybridge.tileentity.DynamoTileEntity;
import net.malfact.createenergybridge.tileentity.renderers.DynamoRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CreateEnergyBridge.MOD_ID)
public class CreateEnergyBridge {

    public static final String MOD_ID = "createenergybridge";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public CreateEnergyBridge(){
        LOGGER.debug("Initializing CreateEnergyBridge");

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);

        //AllConfigs.SERVER.kinetics.stressValues.impacts.put();
    }

    public void onClientSetup(FMLClientSetupEvent event){
        CreateEnergyBridge.LOGGER.debug("ATTEMPTING TO REGISTER DYNAMO RENDERER");
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
                ClientRegistry.bindTileEntitySpecialRenderer(DynamoTileEntity.class, new DynamoRenderer());
        });

    }
}
