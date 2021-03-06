package democretes.tiles.dynamos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IManaReceiver;
import democretes.blocks.base.TMBlocks;
import democretes.compat.Botania;
import democretes.tiles.base.EnergyStorage;
import democretes.tiles.base.TileDynamoBase;

public class TileFlowerDynamo extends TileDynamoBase implements IManaReceiver {

	public int mana = 0;
	public int maxMana = 100000;
	
	public TileFlowerDynamo() {
		super(new EnergyStorage(maxEnergy_default, maxExtract_default, maxReceive_default));
	}
	
	@Override
	public int extractFuel(int ener) {
		float ratio = ((float) ener) / 80F;
		int val = (int) (20 * ratio);
		float fuel = (float) val / (float) Math.min(mana, val);mana -= Math.min(mana, val);
		return (int) (((float)10*20)*fuel);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		for(int x = -4; x < 5; x++) {
			for(int z = -4; z < 5; z++) {
				TileEntity tile = worldObj.getBlockTileEntity(xCoord + x, yCoord, zCoord + z);
				if(tile instanceof IManaPool) {
					IManaPool pool = (IManaPool)tile;
					if(pool.getCurrentMana() >= 100 && mana <= maxMana - 100) {
						pool.recieveMana(-100);
						this.mana += 100;
					}
				}
			}
		}	
	}
	
	@Override
	public void readCustomNBT(NBTTagCompound comp) {
		super.readCustomNBT(comp);
		mana = comp.getInteger("Mana");
	}
	
	@Override
	public void writeCustomNBT(NBTTagCompound comp) {
		super.writeCustomNBT(comp);
		comp.setInteger("Mana", this.mana);
	}

	@Override
	public boolean isFull() {
		if(mana < maxMana) {
			return true;
		}
		return false;
	}

	@Override
	public void recieveMana(int mana) {
		mana += mana;		
	}

	@Override
	public boolean canRecieveManaFromBursts() {
		return true;
	}

	@Override
	public int getCurrentMana() {
		return mana;
	}

	public void renderHUD(Minecraft mc, ScaledResolution res) {
		int color = 0x660000FF;
		try {
			Botania.drawHUD.invoke(null, color, mana, maxMana, TMBlocks.flowerDynamo.getLocalizedName(), res);
		}catch (Exception e){e.printStackTrace();}
	}

}
