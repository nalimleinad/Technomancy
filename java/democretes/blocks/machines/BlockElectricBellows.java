package democretes.blocks.machines;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.blocks.BlockBase;
import democretes.blocks.machines.tiles.TileElectricBellows;
import democretes.compat.Thaumcraft;
import democretes.lib.Names;
import democretes.lib.Ref;
import democretes.lib.RenderIds;

public class BlockElectricBellows extends BlockBase{

	public BlockElectricBellows() {
		setBlockName(Ref.MOD_PREFIX + Names.electricBellows);
	}
	
	private byte facing;
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack){
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileElectricBellows) {
			((TileElectricBellows)tile).facing = this.facing;
		}
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta){
		this.facing = (byte)ForgeDirection.OPPOSITES[side];
		return side + meta;
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {	
		int offsetX = 0;
		int offsetZ = 0;
		switch(ForgeDirection.OPPOSITES[side]) {
		case 2:
			offsetZ -= 1;break;
		case 3:
			offsetZ += 1;break;
		case 4:
			offsetX -= 1;break;
		case 5:
			offsetX += 1;
		}
		if(Thaumcraft.TileAlchemyFurnace.isInstance(world.getTileEntity(x + offsetX, y, z + offsetZ)) ||
				Thaumcraft.TileArcaneFurnace.isInstance(world.getTileEntity(x + (offsetX*2), y, z + (offsetZ*2)))) {
			return true;
		}
		return false;
	}	

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileElectricBellows();
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
		return RenderIds.idElectricBellows;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IIcon) {
		blockIcon = IIcon.registerIcon(Ref.TEXTURE_PREFIX + Names.condenserBlock);
	}

}
