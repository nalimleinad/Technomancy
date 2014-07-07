package democretes.blocks.machines;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.blocks.BlockBase;
import democretes.blocks.machines.tiles.TileBiomeMorpher;
import democretes.lib.Names;
import democretes.lib.Ref;
import democretes.lib.RenderIds;

public class BlockBiomeMorpher extends BlockBase {

	public BlockBiomeMorpher() {
		setBlockName(Ref.MOD_PREFIX + Names.biomeMorpher);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileBiomeMorpher();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (!world.isRemote) {
			if(player.isSneaking()){
				if (world.getBlockMetadata(x, y, z) != 2) {
					world.setBlockMetadataWithNotify(x, y, z, ((world.getBlockMetadata(x, y, z)) + 1), 3);
				}else{
					world.setBlockMetadataWithNotify(x, y, z, 0, 3);
				}
				String text = null;
				switch (world.getBlockMetadata(x, y, z)) {
				case 0:
					text = "Magical Forest"; break;
				case 1:
					text = "Eerie"; break;
				case 2:
					text = "Tainted Land";
				}
				if(text != null) {
					player.addChatComponentMessage(new ChatComponentText("Set to: " + text));
				}
			}else{
				String text = null;
				switch (world.getBlockMetadata(x, y, z)) {
				case 0:
					text = "Magical Forest"; break;
				case 1:
					text = "Eerie"; break;
				case 2:
					text = "Tainted Land";
				}
				if(text != null) {
					player.addChatComponentMessage(new ChatComponentText("Processing: " + text));
				}
			}
		}
		return true;
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
		return RenderIds.idBiomeMorpher;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IIcon) {
		blockIcon = IIcon.registerIcon(Ref.TEXTURE_PREFIX + Names.condenserBlock);
	}

}
