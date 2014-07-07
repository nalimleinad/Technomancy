package democretes.blocks.dynamos;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.wand.IWandHUD;
import democretes.blocks.BlockBase;
import democretes.blocks.dynamos.tiles.TileDynamoBase;
import democretes.blocks.dynamos.tiles.TileFlowerDynamo;
import democretes.lib.Names;
import democretes.lib.Ref;
import democretes.lib.RenderIds;

public class BlockFlowerDynamo extends BlockBase implements IWandHUD {

	public BlockFlowerDynamo() {
		
		setBlockName(Ref.MOD_PREFIX + Names.flowerDynamo);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float vecX, float vecY, float vecZ) {
		if (!world.isRemote) {
			if (player.getHeldItem() != null) { 
				TileEntity entity = world.getTileEntity(x, y, z);
				if (entity instanceof TileFlowerDynamo) {
//					if (player.getHeldItem().getItem() instanceof IToolWrench) {
//						((TileFlowerDynamo)entity).rotateBlock();
//					}		
				}
			}else{
				return true;
			}			
		}
		return false;	   
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		TileDynamoBase tile = (TileDynamoBase)world.getTileEntity(x, y, z);
		if (tile != null) {
			tile.rotateBlock();
		}
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
	}   

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileFlowerDynamo();
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
		((TileFlowerDynamo)world.getTileEntity(x, y, z)).renderHUD(mc, res);
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
		return RenderIds.idFlowerDynamo;
	}

}
