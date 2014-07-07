package democretes.renderer.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import democretes.blocks.machines.tiles.TileTeslaCoil;
import democretes.lib.RenderIds;

public class BlockTeslaCoilRenderer implements ISimpleBlockRenderingHandler{
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileTeslaCoil(), 0, 0, 0, 0);
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int mId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return RenderIds.idTeslaCoil;
	}

}
