package democretes.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.IScribeTools;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.Technomancy;
import democretes.lib.Names;
import democretes.lib.Ref;

public class ItemPen extends ItemBase implements IScribeTools {

	public ItemPen() {
		
		this.maxStackSize = 1;
		this.canRepair = true;
		setMaxDamage(3000);
	}
	
	
	public IIcon penIIcon;
	
	@Override
    public void registerIcons(IIconRegister IIcon) {
        this.penIIcon = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "penItem");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
    return this.penIIcon;
    }
    
	public String getUnlocalizedName(ItemStack stack) {
		return Ref.MOD_PREFIX + Names.pen;
	}
    
	public void addInformation(ItemStack itemstack, EntityPlayer player, List info, boolean useExtraInformation) {
		info.add("It's the 21st century,");
		info.add("why are we using quills and ink?");
	}

}
