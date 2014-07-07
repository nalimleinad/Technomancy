package democretes.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.lib.Names;
import democretes.lib.Ref;

public class ItemProcessedOre extends ItemBase {
	
	String[] processors = {"Thaumcraft", "Botania", "Blood Magic", "Ars Magica", "Witchery", "Totemic" };
	String name;
	
	public ItemProcessedOre() {
		
		setMaxStackSize(64);
		setHasSubtypes(true);
	}

	public IIcon[] itemIcon = new IIcon[5];

	@Override
	public void registerIcons(IIconRegister IIcon) {
		itemIcon[0] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "ore0");
		itemIcon[1] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "ore1");
		itemIcon[2] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "ore2");
		itemIcon[3] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "ore3");
		itemIcon[4] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "ore4");
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < itemIcon.length; i++) {
			ItemStack stack  = new ItemStack(id, 1, i);
			list.add(stack);
		}
	}

	@Override	
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par) {
		return itemIcon[par];
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return Ref.MOD_PREFIX + name;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if(!(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))) {
			list.add(EnumChatFormatting.BLUE.toString() + EnumChatFormatting.ITALIC + 
					StatCollector.translateToLocal("info.techno:purity") + ": " + (stack.getItemDamage() + 1));
			list.add(EnumChatFormatting.WHITE.toString() + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("info.techno:shift"));
		}else{
			list.add(StatCollector.translateToLocal(this.getUnlocalizedName()));
			list.remove("item.null");
			list.add(EnumChatFormatting.BLUE.toString() + EnumChatFormatting.ITALIC +
					StatCollector.translateToLocal("info.techno:purity") + ": " + (stack.getItemDamage() + 1));
			list.add(EnumChatFormatting.WHITE.toString() + EnumChatFormatting.ITALIC + 
					StatCollector.translateToLocal("info.techno:process") + ":");
			for(int i = 0; i < processors.length; i++) {
				if(stack.stackTagCompound != null) {
					if(stack.stackTagCompound.getBoolean(processors[i])) {
						list.add(processors[i]);
					}
				}
			}
		}
	}

	public static class ItemProcessedIron extends ItemProcessedOre {	
		
		public ItemProcessedIron() {
			
			setMaxStackSize(64);
			setHasSubtypes(true);
			this.name = Names.pureIron;
		}
	
		@Override
		@SideOnly(Side.CLIENT)
		public int getColorFromItemStack(ItemStack stack, int par2)	  {
			return 14211288;
		}
	}
	
	public static class ItemProcessedGold extends ItemProcessedOre {	

		public ItemProcessedGold() {
			
			setMaxStackSize(64);
			setHasSubtypes(true);
			this.name = Names.pureGold;
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public int getColorFromItemStack(ItemStack stack, int par2)	  {
			return 14605824;
		}
    
	}
	
	public static class ItemProcessedCopper extends ItemProcessedOre {	

		public ItemProcessedCopper() {
			
			setMaxStackSize(64);
			setHasSubtypes(true);
			this.name = Names.pureCopper;
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public int getColorFromItemStack(ItemStack stack, int par2)	  {
			return 16758834;
		}
	}
	
	public static class ItemProcessedTin extends ItemProcessedOre {	

		public ItemProcessedTin() {
			
			setMaxStackSize(64);
			setHasSubtypes(true);
			this.name = Names.pureTin;
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public int getColorFromItemStack(ItemStack stack, int par2)	  {
			return 12243942;
		}
	}
	
	public static class ItemProcessedSilver extends ItemProcessedOre {	

		public ItemProcessedSilver() {
			
			setMaxStackSize(64);
			setHasSubtypes(true);
			this.name = Names.pureSilver;
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public int getColorFromItemStack(ItemStack stack, int par2)	  {
			return 13163770;
		}    
	}
	
	public static class ItemProcessedLead extends ItemProcessedOre {

		public ItemProcessedLead() {
			
			setMaxStackSize(64);
			setHasSubtypes(true);
			this.name = Names.pureLead;
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public int getColorFromItemStack(ItemStack stack, int par2)	  {
			return 8163006;
		}		
	}
	
	public static class ItemProcessedNickel extends ItemProcessedOre {	

		public ItemProcessedNickel() {
			
			setMaxStackSize(64);
			setHasSubtypes(true);
			this.name = Names.pureNickel;
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public int getColorFromItemStack(ItemStack stack, int par2)	  {
			return 16053453;
		}
	}

}
