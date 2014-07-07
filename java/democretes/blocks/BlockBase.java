package democretes.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import democretes.Technomancy;

public abstract class BlockBase extends BlockContainer {
	
	public BlockBase() {
		super(Material.iron);
		setCreativeTab(Technomancy.tabsTM);
		setHardness(2F);
	}
}