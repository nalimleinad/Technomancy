package democretes.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.lib.Names;
import democretes.lib.Ref;

public class ItemBMMaterial extends ItemBase {

	public ItemBMMaterial() {
		
		setMaxStackSize(64);
		setHasSubtypes(true);
	}

	
	public IIcon[] itemIcon = new IIcon[2];
	
	@Override
	public void registerIcons(IIconRegister icon) {
		itemIcon[0] = icon.registerIcon(Ref.TEXTURE_PREFIX + "sacrificialIngot");
		itemIcon[1] = icon.registerIcon(Ref.TEXTURE_PREFIX + "bloodCoil");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par) {
		return this.itemIcon[par];
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		return Ref.MOD_PREFIX + Names.itemBM + "." + stack.getItemDamage();
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < itemIcon.length; i++) {
			ItemStack stack  = new ItemStack(this, 1, i);
			list.add(stack);
		}
	}
}
