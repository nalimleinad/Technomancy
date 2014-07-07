package democretes.blocks.machines;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.wand.IWandHUD;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.Technomancy;
import democretes.blocks.BlockBase;
import democretes.blocks.machines.tiles.TileBMProcessor;
import democretes.blocks.machines.tiles.TileBOProcessor;
import democretes.blocks.machines.tiles.TileProcessorBase;
import democretes.blocks.machines.tiles.TileTCProcessor;
import democretes.compat.Botania;
import democretes.compat.Thaumcraft;
import democretes.lib.Names;
import democretes.lib.Ref;

public class BlockProcessor extends BlockBase {

	String name;
	
	public BlockProcessor() {}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(player != null && tile != null) {
			if(tile instanceof TileTCProcessor) {		
				player.openGui(Technomancy.instance, 0, world, x, y, z);
			}
			if(tile instanceof TileBMProcessor) {
				player.openGui(Technomancy.instance, 1, world, x, y, z);
			}
			if(tile instanceof TileBOProcessor) {
				player.openGui(Technomancy.instance, 2, world, x, y, z);
			}
		}		
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile != null) {
			if(tile instanceof TileBMProcessor && entity instanceof EntityPlayer) {
				((TileBMProcessor)tile).owner = ((EntityPlayer)entity).getDisplayName();
			}
		}
	}
	
	@Override
	public String getUnlocalizedName() {
		return "tile." + Ref.MOD_PREFIX + Names.processor + name;
		
	}
	
	public IIcon[] IIcons = new IIcon[4];	
	
	@Override
	public void registerBlockIcons(IIconRegister IIcon) {
		IIcons[0] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + Names.processor + name + "Side");
		IIcons[1] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + Names.processor + name + "Active");
		IIcons[2] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + Names.processor + name + "Inactive");
		IIcons[3] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + Names.processor + name + "Top");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == 1) {
			return IIcons[3];
		}
		if(side > 1) {
			return IIcons[2];
		}
		return IIcons[1];
	}
	
	@Override
	public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side) {
		int meta = access.getBlockMetadata(x, y, z);
		TileEntity tile = access.getTileEntity(x, y, z);
		if(side == 1) {
			return IIcons[3];
		}
		if(side > 1) {
			if(((TileProcessorBase)tile).isActive()) {
				return IIcons[1];
			}
			return IIcons[2];
		}		
		return IIcons[1];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random r)	  {
		TileEntity te = world.getTileEntity(x, y, z);
	    if ((te != null) && ((TileProcessorBase)te).isActive())	    {
	    	float f = x + 0.5F;
    		float f1 = y + 0.2F + r.nextFloat() * 5.0F / 16.0F;
    		float f2 = z + 0.5F;
    		float f3 = 0.52F;
    		float f4 = r.nextFloat() * 0.5F - 0.25F;
	    	if(te instanceof TileTCProcessor || te instanceof TileBMProcessor) {	      
	    		world.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
	    		world.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
	        
	    		world.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
	    		world.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
	        
	    		world.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
	    		world.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
	        
	    		world.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
	    		world.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
	    	}
	    	if(te instanceof TileBOProcessor) {
	    		f += 0.1F;
	    		f1 += 0.1F;
	    		f2 += 0.1F;
		    	Botania.sparkle(world, (double)f - f3, (double)f1, f2 + f4, r);
		    	Botania.sparkle(world, (double)f + f3, (double)f1, f2 + f4, r);
		    	Botania.sparkle(world, (double)f + f4, (double)f1, f2 - f3, r);
		    	Botania.sparkle(world, (double)f + f4, (double)f1, f2 + f3, r);
		    }
	    }
	    
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		if(world.getTileEntity(x, y, z) instanceof TileProcessorBase) {
			if(((TileProcessorBase)world.getTileEntity(x, y, z)).isActive()) {
				return 12;
			}
		}
		return super.getLightValue(world, x, y, z);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)	  {
	    Thaumcraft.dropItems(world, x, y, z);
	    super.breakBlock(world, x, y, z, block, meta);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}
	
	public static class BlockTCProcessor extends BlockProcessor {

		public BlockTCProcessor() {
			this.name = "TC";
		}
		
		@Override
		public TileEntity createNewTileEntity(World world, int meta) {
			return new TileTCProcessor();
		}
		
	}
	
	public static class BlockBMProcessor extends BlockProcessor {
		
		public BlockBMProcessor() {
			this.name = "BM";
		}
		
		@Override
		public TileEntity createNewTileEntity(World world, int meta) {
			return new TileBMProcessor();
		}
	}
	
	public static class BlockBOProcessor extends BlockProcessor implements IWandHUD {
		
		public BlockBOProcessor() {
			this.name = "BO";
		}
		
		@Override
		public TileEntity createNewTileEntity(World world, int meta) {
			return new TileBOProcessor();
		}

		@Override
		public void renderHUD(Minecraft minecraft, ScaledResolution res, World world, int x, int y, int z) {
			if(world.getTileEntity(x, y, z) instanceof TileBOProcessor) {
				((TileBOProcessor)world.getTileEntity(x, y, z)).renderHUD(minecraft, res);
			}
		}
	}

	

}
