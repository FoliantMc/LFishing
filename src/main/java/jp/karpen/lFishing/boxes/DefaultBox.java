package jp.karpen.lFishing.boxes;

import jp.karpen.lFishing.LFishing;
import jp.karpen.lFishing.models.BoxType;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class DefaultBox extends AbstractBox {

    public DefaultBox (LFishing plugin) {
        super(plugin, BoxType.DEFAULT);
    }

    protected List<ItemStack> generateRandomItems() {
        List<ItemStack> items = new ArrayList<>();
        Random random = ThreadLocalRandom.current();
        int var = random.nextInt(0, 10);

        switch (var) {
            case 0:
                items.add(new ItemStack(Material.OAK_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.OAK_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.COBBLESTONE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.COBBLESTONE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.OAK_LOG, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.BREAD, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 1:
                items.add(new ItemStack(Material.ACACIA_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.ACACIA_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.DEEPSLATE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.DEEPSLATE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.ACACIA_LOG, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.COOKED_BEEF, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 2:
                items.add(new ItemStack(Material.CHERRY_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.CHERRY_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.ANDESITE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.ANDESITE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.CHERRY_LOG, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.COOKED_RABBIT, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 3:
                items.add(new ItemStack(Material.JUNGLE_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.JUNGLE_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.GRANITE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.GRANITE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.JUNGLE_LOG, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.COOKED_CHICKEN, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 4:
                items.add(new ItemStack(Material.BIRCH_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.BIRCH_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.STONE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.STONE, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.BIRCH_LOG, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.COOKED_COD, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 5:
                items.add(new ItemStack(Material.SPRUCE_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.SPRUCE_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.STICK, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.STICK, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.SPRUCE_LOG, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.BREAD, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 6:
                items.add(new ItemStack(Material.DARK_OAK_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.DARK_OAK_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.DARK_OAK_LEAVES, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.DARK_OAK_LEAVES, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.DARK_OAK_LOG, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.COOKED_RABBIT, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 7:
                items.add(new ItemStack(Material.PALE_OAK_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.PALE_OAK_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.PALE_MOSS_BLOCK, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.PALE_MOSS_BLOCK, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.PALE_OAK_LOG, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.PALE_MOSS_CARPET, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 8:
                items.add(new ItemStack(Material.MANGROVE_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.MANGROVE_PLANKS, random.nextInt(1, 5)));
                items.add(new ItemStack(Material.MOSS_BLOCK, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.MOSS_BLOCK, random.nextInt(1, 9)));
                items.add(new ItemStack(Material.MANGROVE_LOG, random.nextInt(1, 8)));
                items.add(new ItemStack(Material.MOSS_CARPET, random.nextInt(1, 10)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                break;
            case 9:
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.COBWEB, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.STICK, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.STICK, random.nextInt(1, 3)));
                items.add(new ItemStack(Material.STICK, random.nextInt(1, 3)));
        }
        return items;
    }
}

