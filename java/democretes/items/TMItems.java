package democretes.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.wands.WandRod;
import cpw.mods.fml.common.registry.GameRegistry;
import democretes.lib.Ids;
import democretes.lib.Names;

public class TMItems {

	// Item Instances
    public static Item essentiaCannon;
    public static Item itemMaterial;
    public static Item itemPen;
    public static Item itemWandCores;
    public static Item itemFusionFocus;
    public static Item processedIron;
    public static Item processedGold;
    public static Item processedCopper;
    public static Item processedTin;
    public static Item processedSilver;
    public static Item processedLead;
    public static Item processedNickel;
    
    public static WandRod WAND_ROD_ELECTRIC;
    
    public static void initThaumcraft() { 	

        // Item Initializations
        //essentiaCannon = new ItemEssentiaCannon(ItemIds.idESSENTIA_CANNON);
        itemMaterial = new ItemMaterial();
        itemPen = new ItemPen();
        itemWandCores = new ItemWandCores();
        itemFusionFocus = new ItemFusionFocus();        
        processedIron = new ItemProcessedOre.ItemProcessedIron();
        processedGold = new ItemProcessedOre.ItemProcessedGold();
        processedCopper = new ItemProcessedOre.ItemProcessedCopper();
        processedTin = new ItemProcessedOre.ItemProcessedTin();
        processedSilver = new ItemProcessedOre.ItemProcessedSilver();
        processedLead = new ItemProcessedOre.ItemProcessedLead();
        processedNickel = new ItemProcessedOre.ItemProcessedNickel();
        
        //Registry
        //GameRegistry.registerItem(essentiaCannon, LibNames.ESSENTIA_CANNON_NAME);
        GameRegistry.registerItem(itemMaterial, Names.itemMaterial);
        GameRegistry.registerItem(itemPen, Names.pen);
        GameRegistry.registerItem(itemFusionFocus, Names.fusionFocus);
        GameRegistry.registerItem(processedIron, Names.pureIron);
        
        
        GameRegistry.registerItem(itemWandCores, Names.wandCores);
        WAND_ROD_ELECTRIC = new WandRod("electric", 25, new ItemStack(itemWandCores, 1, 0), 9, new ElectricWandUpdate(), new ResourceLocation("technom", "textures/models/electricWand.png"));
    }
    
    //Instances
    public static Item itemBM;
    
    public static void initBloodMagic() {

    	//Initializations
        itemBM = new ItemBMMaterial();        
        
        //Registry
        GameRegistry.registerItem(itemBM, Names.itemBM);
    }
    
    //Instances
    public static Item itemBO;
    
    public static void initBotania() {
    	//Initializations
    	itemBO = new ItemBOMaterial();
    	
    	//Registry
    	GameRegistry.registerItem(itemBO, Names.itemBO);
    }

    

}
