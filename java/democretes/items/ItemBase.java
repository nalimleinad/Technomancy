package democretes.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.Technomancy;
import democretes.lib.Ref;

public class ItemBase extends Item {

    public ItemBase() {
        setMaxStackSize(1);
        setCreativeTab(Technomancy.tabsTM);
    }

}
