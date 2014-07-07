package democretes.blocks.dynamos;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import democretes.blocks.BlockBase;
import democretes.blocks.dynamos.tiles.TileNodeDynamo;
import democretes.lib.RenderIds;

public class BlockNodeDynamo extends BlockBase {
	
	public BlockNodeDynamo() {
		
		setBlockName("techno:nodeDynamo");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileNodeDynamo();
	}

	@Override
	public boolean isOpaqueCube(){
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return RenderIds.idNodeDynamo;
	}

}
