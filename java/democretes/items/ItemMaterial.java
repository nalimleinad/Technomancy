package democretes.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.IAspectSource;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.blocks.machines.tiles.TileTeslaCoil;
import democretes.compat.Thaumcraft;
import democretes.lib.Names;
import democretes.lib.Ref;

public class ItemMaterial extends ItemBase {

	public ItemMaterial() {
		
		setMaxStackSize(64);
		setHasSubtypes(true);
	}


	public IIcon[] itemIcon = new IIcon[5];

	@Override
	public void registerIcons(IIconRegister IIcon) {
		itemIcon[0] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "neutronizedMetal");
		itemIcon[1] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "enchantedCoil");
		itemIcon[2] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "neutronizedGear");
		itemIcon[3] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "penCore");
		itemIcon[4] = IIcon.registerIcon(Ref.TEXTURE_PREFIX + "coilCoupler");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par) {
		return this.itemIcon[par];
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return Ref.MOD_PREFIX + Names.itemMaterial + "." + stack.getItemDamage();
	}
	
	public void getSubItems(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < itemIcon.length; i++) {
			ItemStack stack  = new ItemStack(id, 1, i);
			list.add(stack);
		}
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(stack.stackTagCompound==null && stack.getItemDamage() == 4){
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setBoolean("ent", false);
			stack.stackTagCompound.setInteger("x", 0);
			stack.stackTagCompound.setInteger("y", 0);
			stack.stackTagCompound.setInteger("z", 0);
			stack.stackTagCompound.setInteger("dimId", 0);
		}

		if(!world.isRemote) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if(tile != null && stack.getItemDamage() == 4) {			
				if(tile instanceof IAspectSource && !Thaumcraft.TileMirrorEssentia.isInstance(tile) && !(tile instanceof TileTeslaCoil)) {
					if(stack.stackTagCompound.getBoolean("ent")) {
						if(tile.getWorldObj().provider.dimensionId == stack.stackTagCompound.getInteger("dimId")){
							if(!areCoordsEqual(stack.stackTagCompound, x, y, z)) {
								int[] i = retrievePos(stack.stackTagCompound);
								((TileTeslaCoil)world.getTileEntity(i[0], i[1], i[2])).sources.add(new ChunkCoordinates(x, y, z));
								player.addChatComponentMessage(new ChatComponentText("Linked"));
								stack.stackTagCompound.setBoolean("ent", false);
								return true;
							}
						}else{
							player.addChatComponentMessage(new ChatComponentText("Cannot create an interdimensional link"));
						}
					}else{
						player.addChatComponentMessage(new ChatComponentText("No valid wireless destination available"));
					}
				}else if(tile instanceof TileTeslaCoil && (!stack.stackTagCompound.getBoolean("ent") || player.isSneaking())) {
					if(player.isSneaking()) {
						((TileTeslaCoil)tile).sources.clear();
						player.addChatComponentMessage(new ChatComponentText("Links Cleared"));
						return false;
					}else{
						player.addChatComponentMessage(new ChatComponentText("Begin Linking"));					
						stack.stackTagCompound.setBoolean("ent", true);
						stack.stackTagCompound.setInteger("x", x);
						stack.stackTagCompound.setInteger("y", y);
						stack.stackTagCompound.setInteger("z", z);
						stack.stackTagCompound.setInteger("dimId", tile.getWorldObj().provider.dimensionId);
						return true;
					}

				}else{
					player.addChatComponentMessage(new ChatComponentText("Not a valid source"));
				}
			}
		}
		return false;
	}

	private boolean areCoordsEqual(NBTTagCompound comp, int x, int y, int z) {
		int nx = comp.getInteger("x");int ny = comp.getInteger("y");int nz = comp.getInteger("z");

		return nx==x && ny==y && nz==z;
	}

	private int[] retrievePos(NBTTagCompound comp) {
		return new int[]{comp.getInteger("x"),comp.getInteger("y"),comp.getInteger("z")};
	}

}
