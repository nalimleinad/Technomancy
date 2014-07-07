package democretes.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;

public class EnergyHelper {

	public static boolean isHandler(TileEntity tile, ForgeDirection dir) {
		return tile instanceof IEnergyHandler ? ((IEnergyHandler) tile).canConnectEnergy(dir) : false;
	}

}
