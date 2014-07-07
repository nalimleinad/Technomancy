package democretes.blocks.machines;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import democretes.blocks.BlockBase;
import democretes.blocks.machines.tiles.TileBloodFabricator;
import democretes.lib.Names;
import democretes.lib.Ref;
import democretes.lib.RenderIds;

public class BlockBloodFabricator extends BlockBase {

	public BlockBloodFabricator() {
		setBlockName(Ref.MOD_PREFIX + Names.bloodFabricator);
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)    {
        int rotation = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, rotation, 2);
    }
	
	@Override
	public void registerBlockIcons(IIconRegister IIcon) {
		blockIcon = IIcon.registerIcon(Ref.TEXTURE_PREFIX + Names.bloodFabricator);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileBloodFabricator();
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return RenderIds.idBloodFabricator;
	}

}
