package democretes.lib;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.items.TMItems;

public class CreativeTabTM extends CreativeTabs {

    public CreativeTabTM(int id, String tabLabel) {
        super(id, tabLabel);
    }

    @SideOnly(Side.CLIENT)
    public String getTabLabel() {
        return "Technomancy";
    }

	@Override
	public Item getTabIconItem() {
		if(TMItems.itemMaterial!=null){
	    	  return TMItems.itemMaterial;
	      }
	      return Item.getItemFromBlock(Blocks.stone);
	}
}
