package democretes.blocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileTechnomancy extends TileEntity {
	
	
	  public void readFromNBT(NBTTagCompound nbttagcompound)
	  {
	    super.readFromNBT(nbttagcompound);
	    readCustomNBT(nbttagcompound);
	  }
	  
	  public void readCustomNBT(NBTTagCompound nbttagcompound) {}
	  
	  public void writeToNBT(NBTTagCompound nbttagcompound)
	  {
	    super.writeToNBT(nbttagcompound);
	    writeCustomNBT(nbttagcompound);
	  }
	  
	  public void writeCustomNBT(NBTTagCompound nbttagcompound) {}
	  
	  public Packet getDescriptionPacket() {
	    NBTTagCompound nbttagcompound = new NBTTagCompound();
	    writeCustomNBT(nbttagcompound);
	    return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, -999, nbttagcompound);
	  }
	  
	  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		  if(pkt!=null && pkt.func_148857_g()!=null)
			  readCustomNBT(pkt.func_148857_g());
	  }
	  
	  public void onNeighborBlockChange() {}
	  
	  public void onNeighborTileChange(int tileX, int tileY, int tileZ) {}

}
