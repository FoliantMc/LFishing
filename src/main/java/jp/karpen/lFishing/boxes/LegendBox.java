package jp.karpen.lFishing.boxes;

import jp.karpen.lFishing.LFishing;
import jp.karpen.lFishing.models.BoxType;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import java.util.*;

public final class LegendBox extends AbstractBox {
    public LegendBox(LFishing plugin) {
        super(plugin, BoxType.LEGEND);
    }

    protected List<ItemStack> generateRandomItems() {
        List<ItemStack> items = new ArrayList<>();
        Random random = new Random();
        int var = random.nextInt(0, 5);

        switch (var) {
            case 0:
                items.add(new ItemStack(Material.DIAMOND, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.GOLD_BLOCK, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.IRON_ORE, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.GOLD_BLOCK, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.IRON_ORE, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.DIAMOND_ORE, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.GOLDEN_APPLE, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 1:
                items.add(new ItemStack(Material.DEEPSLATE_DIAMOND_ORE, random.nextInt(1, 6)));
                items.add(new ItemStack(Material.EMERALD_BLOCK, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.LAPIS_BLOCK, random.nextInt(1, 7)));
                items.add(new ItemStack(Material.IRON_BLOCK, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.LAPIS_BLOCK, random.nextInt(1, 7)));
                items.add(new ItemStack(Material.IRON_BLOCK, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.DIAMOND, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 2:
                items.add(new ItemStack(Material.DIAMOND, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.DIAMOND_ORE, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.LAPIS_BLOCK, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.DIAMOND_ORE, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.LAPIS_BLOCK, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.EMERALD_BLOCK, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 3:
                items.add(new ItemStack(Material.GOLD_BLOCK, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.DIAMOND, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.DEEPSLATE_DIAMOND_ORE, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.DEEPSLATE_GOLD_ORE, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.DEEPSLATE_GOLD_ORE, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.DEEPSLATE_GOLD_ORE, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 4:
                items.add(new ItemStack(Material.ENDER_EYE, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.IRON_BLOCK, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.ENDER_EYE, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.IRON_BLOCK, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.END_ROD, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.DEEPSLATE_DIAMOND_ORE, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.DIAMOND, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
        }
        return items;
    }
}

