package democretes.blocks.dynamos;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.blocks.BlockBase;
import democretes.blocks.dynamos.tiles.TileDynamoBase;
import democretes.blocks.dynamos.tiles.TileEssentiaDynamo;
import democretes.lib.RenderIds;

public class BlockEssentiaDynamo extends BlockBase {

	public BlockEssentiaDynamo() {
		
		setBlockName("techno:essentiaDynamo");
	}

	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEssentiaDynamo();
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IIcon) {
		blockIcon = IIcon.registerIcon("technom:models/essentiaDynamo");
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float vecX, float vecY, float vecZ) {
		if (!world.isRemote) {
			if (player.getHeldItem() != null) {
				TileEntity entity = world.getTileEntity(x, y, z);
				if (entity instanceof TileEssentiaDynamo) {
					// if (player.getHeldItem().getItem() instanceof
					// IToolWrench) {
					// ((TileEssentiaDynamo)entity).rotateBlock();
					// }
				}
			} else {
				return true;
			}

		}
		return false;
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		TileDynamoBase tile = (TileDynamoBase) world.getTileEntity(x, y, z);
		if (tile != null) {
			tile.rotateBlock();
		}
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
	}

	public int getRenderType() {
		return RenderIds.idEssentiaDynamo;
	}
}
