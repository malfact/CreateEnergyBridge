package net.malfact.createenergybridge.tileentity.renderers;

import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.CreateClient;
import com.simibubi.create.foundation.utility.SuperByteBuffer;
import com.simibubi.create.modules.contraptions.base.KineticTileEntity;
import com.simibubi.create.modules.contraptions.base.KineticTileEntityRenderer;

public class DynamoRenderer extends KineticTileEntityRenderer {

    @Override
    protected SuperByteBuffer getRotatedModel(KineticTileEntity tileEntity){
        return CreateClient.bufferCache.renderPartial(AllBlockPartials.SHAFTLESS_COGWHEEL, tileEntity.getBlockState());
    }
}
