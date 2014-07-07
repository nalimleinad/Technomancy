package democretes.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import democretes.Technomancy;
import democretes.lib.Names;
import democretes.lib.Ref;

public class BlockCosmeticOpaque extends Block {

	public BlockCosmeticOpaque() {
		super(Material.glass);
		setStepSound(Block.soundTypeGlass);
		setHardness(.25F);
		setBlockName(Ref.MOD_PREFIX + Names.cosmeticOpaque);
		this.setCreativeTab(Technomancy.tabsTM);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister IIcon) {
		blockIcon = IIcon.registerIcon(Ref.TEXTURE_PREFIX + Names.cosmeticOpaque);
	}
	
	public int quantityDropped(Random par1Random) {
	       return 1;
	}
	
    public int getRenderBlockPass() {
		return 1;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	public boolean renderAsNormalBlock() {
		return false;
	}

}
