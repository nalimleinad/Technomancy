package democretes.util;

import thaumcraft.api.aspects.IEssentiaTransport;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class WorldHelper {

	public static TileEntity getAdjacentTileEntity(TileEntity tile, int facing) {
		ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[facing];
		return tile!=null ? tile.getWorldObj().getTileEntity(tile.xCoord + dir.offsetX, tile.yCoord + dir.offsetY, tile.zCoord + dir.offsetZ)
				: null;
	}

	public static int insertFluidIntoAdjacentFluidHandler(TileEntity tile, ForgeDirection from, FluidStack stack, boolean b) {
		if(tile==null || stack==null)return 0;
		IFluidHandler fh = tile.getWorldObj().getTileEntity(tile.xCoord + from.offsetX, tile.yCoord + from.offsetX, tile.zCoord + from.offsetX)
				instanceof IFluidHandler ? (IFluidHandler)tile.getWorldObj().getTileEntity(tile.xCoord + from.offsetX,
				tile.yCoord + from.offsetX, tile.zCoord + from.offsetX) : null;
		return fh==null ? 0 : fh.fill(from.getOpposite(), stack, b);
	}

	public static boolean isFluidHandler(TileEntity tile, ForgeDirection dir) {
		return tile==null || dir==ForgeDirection.UNKNOWN ? false :tile.getWorldObj().getTileEntity(
			tile.xCoord + dir.offsetX, tile.yCoord + dir.offsetY, tile.zCoord + dir.offsetZ) instanceof IFluidHandler;
	}

}
