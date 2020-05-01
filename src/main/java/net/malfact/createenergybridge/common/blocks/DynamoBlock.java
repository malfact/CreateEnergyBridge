package net.malfact.createenergybridge.common.blocks;

import com.simibubi.create.modules.contraptions.base.DirectionalKineticBlock;
import net.malfact.createenergybridge.common.tiles.DynamoTile;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import com.simibubi.create.foundation.block.ITE;

public class DynamoBlock extends DirectionalKineticBlock implements ITE<DynamoTile>{

    public DynamoBlock() {
        super(Properties.from(Blocks.ANDESITE));
    }

    @Override
    public TileEntity createTileEntity(BlockState blockState, IBlockReader iBlockReader) {
        return new DynamoTile();
    }

    @Override
    public boolean hasIntegratedCogwheel(IWorldReader world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.get(FACING).getAxis();
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
    public Class<DynamoTile> getTileEntityClass(){
        return DynamoTile.class;
    }
}
