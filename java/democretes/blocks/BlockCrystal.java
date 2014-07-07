package democretes.blocks;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import democretes.lib.Names;
import democretes.lib.Ref;

public class BlockCrystal extends BlockBase {

	public BlockCrystal() {
		
		setBlockUnbreakable();
		setBlockName(Ref.MOD_PREFIX + Names.crystalBlock);
	}
	
	@Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if(player != null) {
			int meta = world.getBlockMetadata(x, y, z);
			if(meta == 0) {
				Random rand = new Random();
				int dim = rand.nextInt(DimensionManager.getIDs().length);
				if(DimensionManager.isDimensionRegistered(DimensionManager.getIDs()[dim])) {
					player.travelToDimension(DimensionManager.getIDs()[dim]);
				}
			}					
		}
	}
	
//	@Override
//	public void getSubBlocks(int id, CreativeTabs tab, List list) {
//		list.add(new ItemStack(id, 1, 0));
//		list.add(new ItemStack(id, 1, 1));
//		list.add(new ItemStack(id, 1, 2));
//	}


	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

}
