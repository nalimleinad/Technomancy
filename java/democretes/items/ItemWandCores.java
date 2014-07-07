package democretes.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.compat.Thaumcraft;
import democretes.lib.Names;
import democretes.lib.Ref;

public class ItemWandCores extends ItemBase{

	public ItemWandCores() {
		
		this.setHasSubtypes(true);
	}

	public IIcon[] coresIIcon = new IIcon[1];

	@Override
	public void registerIcons(IIconRegister IIcon) {
		coresIIcon[0] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "electricWandCore");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int IIcon) {
		return this.coresIIcon[IIcon];
	}

	public String getUnlocalizedName(ItemStack stack) {
		return Ref.MOD_PREFIX + Names.wandCores + "." + stack.getItemDamage();
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item id, CreativeTabs tab, List list) {
		try{
			for (int i = 0; i < coresIIcon.length; i++) {
				ItemStack stack  = new ItemStack(id, 1, i);
				list.add(stack);
			}
			ItemStack electric = new ItemStack(Thaumcraft.itemWandCasting, 1, 72);
			Thaumcraft.setCap.invoke(electric.getItem(), electric, (WandCap)WandCap.caps.get("thaumium"));
			Thaumcraft.setRod.invoke(electric.getItem(), electric, (WandRod)WandRod.rods.get("electric"));
			list.add(electric);
		}catch(Exception e){}
	}
}
