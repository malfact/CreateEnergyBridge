package net.malfact.createenergybridge.blocks;

import com.simibubi.create.foundation.block.ITE;
import com.simibubi.create.modules.contraptions.base.DirectionalAxisKineticBlock;
import com.simibubi.create.modules.contraptions.base.DirectionalKineticBlock;
import net.malfact.createenergybridge.init.ModTileEntityTypes;
import net.malfact.createenergybridge.tileentity.DynamoTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class DynamoBlock extends DirectionalKineticBlock implements ITE<DynamoTileEntity>{

    public DynamoBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity createTileEntity(BlockState blockState, IBlockReader iBlockReader) {
        return ModTileEntityTypes.DYNAMO.create();
    }

    @Override
    public boolean hasIntegratedCogwheel(IWorldReader world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    protected boolean hasStaticPart() {
        return true;
    }

    @Override
    public Class<DynamoTileEntity> getTileEntityClass(){
        return DynamoTileEntity.class;
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.get(FACING).getAxis();
    }
}
