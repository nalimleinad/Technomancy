package democretes.blocks.dynamos;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.blocks.BlockBase;
import democretes.blocks.dynamos.tiles.TileBloodDynamo;
import democretes.blocks.dynamos.tiles.TileDynamoBase;
import democretes.compat.BloodMagic;
import democretes.lib.RenderIds;

public class BlockBloodDynamo extends BlockBase {

	public BlockBloodDynamo() {
		setBlockName("techno:bloodDynamo");
	}

	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileBloodDynamo();
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IIcon) {
		blockIcon = Blocks.stone.getIcon(0, 0);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float vecX, float vecY, float vecZ) {
		if (!world.isRemote) {
			if (player.getHeldItem() != null) {
				TileEntity entity = world.getTileEntity(x, y, z);
				if (entity instanceof TileBloodDynamo) {
					/*if (player.getHeldItem().getItem() instanceof IToolWrench) {
						((TileBloodDynamo) entity).rotateBlock();
					} else*/ if (player.getHeldItem().getItem() == BloodMagic.divinationSigil) {
						player.addChatComponentMessage(new ChatComponentText("Energy: "
								+ ((TileBloodDynamo) entity).getEnergyStored(null)+ "/"+ ((TileBloodDynamo) entity).getMaxEnergyStored(null)));
						player.addChatComponentMessage(new ChatComponentText("Blood: " + ((TileBloodDynamo) entity).tank.getFluidAmount() + "/"
								+ ((TileBloodDynamo) entity).tank.getInfo().capacity));
					}else if(player.getHeldItem().getItem() == BloodMagic.bucketLife){
						if(((TileBloodDynamo) entity).tank.getCapacity() - ((TileBloodDynamo) entity).tank.getFluidAmount() >= 1000){
							player.inventory.mainInventory[player.inventory.currentItem] = new ItemStack(Items.bucket, 1
									);
							((TileBloodDynamo) entity).tank.fill(new FluidStack(BloodMagic.lifeEssenceFluid, 1000), true);
						}
					}
				}
			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		TileDynamoBase tile = (TileDynamoBase) world.getTileEntity(x, y, z);
		if (tile != null) {
			tile.rotateBlock();
		}
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return RenderIds.idBloodDynamo;
	}
}