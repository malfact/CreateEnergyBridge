package net.malfact.createenergybridge;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CreateEnergyBridge.MOD_ID)
public class CreateEnergyBridge {

    public static final String MOD_ID = "createenergybridge";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public CreateEnergyBridge(){
        LOGGER.debug("Initializing CreateEnergyBridge");
    }
}
