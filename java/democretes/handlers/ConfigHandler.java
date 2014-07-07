package democretes.handlers;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import democretes.lib.Ids;
import democretes.lib.Names;

public class ConfigHandler {
	
	public static boolean fancy = true;
	public static int[] blacklist = {};
	public static boolean bonus = true;

    public static void init(File file) {
        Configuration config = new Configuration(file);

        config.load();

        //Recipe Whatnots
        Property smelting = config.get("Recipes", "Add/Increase Smelting bonus to dusts/ore", bonus);
        bonus = smelting.getBoolean(true);
        
        //Render effects
        Property coilfx = config.get("Renderers", "CoilFX", fancy);
        fancy = coilfx.getBoolean(true);
        
        //Reconstructor stuff
        Property reconstructor = config.get("Machines", "Blacklisted materials for use in the Essentia Reconstructor", blacklist);
        blacklist = reconstructor.getIntList();

        config.save();

    }

}
