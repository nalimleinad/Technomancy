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

public class ItemBOMaterial extends ItemBase {

	public ItemBOMaterial() {
		
		setMaxStackSize(64);
		setHasSubtypes(true);
	}

	
	public IIcon[] itemIcon = new IIcon[2];
	
	@Override
	public void registerIcons(IIconRegister IIcon) {
		itemIcon[0] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "manaCoil");
		itemIcon[1] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "manaGear");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par) {
		return this.itemIcon[par];
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		return Ref.MOD_PREFIX + Names.itemBO + "." + stack.getItemDamage();
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < itemIcon.length; i++) {
			ItemStack stack  = new ItemStack(id, 1, i);
			list.add(stack);
		}
	}
}
