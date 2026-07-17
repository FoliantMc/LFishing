package jp.karpen.lFishing.boxes;

import jp.karpen.lFishing.LFishing;
import jp.karpen.lFishing.models.BoxType;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public final class EpicBox extends AbstractBox {
    public EpicBox(LFishing plugin) {
        super(plugin, BoxType.EPIC);
    }

    protected List<ItemStack> generateRandomItems() {
        List<ItemStack> items = new ArrayList<>();
        Random random = new Random();
        int var = random.nextInt(0, 5);

        switch (var) {
            case 0:
                items.add(new ItemStack(Material.DIAMOND_HORSE_ARMOR, 1));
                items.add(new ItemStack(Material.GOLD_NUGGET, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.IRON_NUGGET, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.GOLDEN_APPLE, random.nextInt(1, 2)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 1:
                items.add(new ItemStack(Material.BLAZE_ROD, random.nextInt(1, 2)));
                items.add(new ItemStack(Material.EMERALD, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.LAPIS_LAZULI, random.nextInt(1, 4)));
                items.add(new ItemStack(Material.IRON_BLOCK, random.nextInt(1, 2)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 2:
                items.add(new ItemStack(Material.GOLD_NUGGET, random.nextInt(1, 2)));
                items.add(new ItemStack(Material.REDSTONE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.LAPIS_BLOCK, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.EMERALD_ORE, random.nextInt(1, 2)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 3:
                items.add(new ItemStack(Material.GOLD_BLOCK, random.nextInt(1, 2)));
                items.add(new ItemStack(Material.DIAMOND, random.nextInt(1, 2)));
                items.add(new ItemStack(Material.BLAZE_ROD, random.nextInt(1, 4)));
                items.add(new ItemStack(Material.GOLD_NUGGET, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 4:
                items.add(new ItemStack(Material.ENDER_EYE, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.IRON_BLOCK, random.nextInt(1, 2)));
                items.add(new ItemStack(Material.END_ROD, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.IRON_NUGGET, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
        }
        return items;
    }
}

