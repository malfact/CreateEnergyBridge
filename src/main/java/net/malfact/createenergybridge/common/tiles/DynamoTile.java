package net.malfact.createenergybridge.common.tiles;

import com.simibubi.create.modules.contraptions.base.KineticTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class DynamoTile extends KineticTileEntity {

    public DynamoTile() {
        super(null);
        //super(CEBTileEntities.Dynamo.type); //ToDO Add type
    }

    @Override
    public void tick() {
        super.tick();
    }
}
