package me.Xocky.News.core.news.config.custom.factory.item;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.message.Message;
import me.Xocky.News.core.utils.custom.item.BItem;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ItemFactory {
    public ItemFactory() {

    }
    public ItemStack manufacture(String name) {
        if (News.nm.getItemConfig().getYaml().contains(name)) {
            BItem item = (News.nm.getItemConfig().getYaml().contains(name +".amount") || News.nm.getItemConfig().getYaml().contains(name +".durability"))?(News.nm.getItemConfig().getYaml().contains(name +".amount")?new BItem(new ItemStack(Material.valueOf(News.nm.getItemConfig().getYaml().getString(name+".material")),News.nm.getItemConfig().getYaml().getInt(name+".amount"),(short)News.nm.getItemConfig().getYaml().getInt(name+".durability"))):new BItem(new ItemStack(Material.valueOf(News.nm.getItemConfig().getYaml().getString(name+".material")),1,(short)News.nm.getItemConfig().getYaml().getInt(name+".durability")))):new BItem(new ItemStack(Material.valueOf(News.nm.getItemConfig().getYaml().getString(name+".material"))));
            if (News.nm.getItemConfig().getYaml().getBoolean(name+".enchanted")) {item.applyEnchantment(Enchantment.DAMAGE_ALL,1);}
            if (News.nm.getItemConfig().getYaml().contains(name+".name")) {item.setDisplayName(new Message(News.nm.getItemConfig().getYaml().getString(name+".name")).translate());}
            if (News.nm.getItemConfig().getYaml().contains(name+".lore")) {item.setLore(Arrays.asList(new Message(StringUtils.join(News.nm.getItemConfig().getYaml().getStringList(name+".lore")," /= ")).translate().split(" /= ")));}
            if (News.nm.getItemConfig().getYaml().contains(name+".command")) {item.setNBTString("command",News.nm.getItemConfig().getYaml().getString(name+".command"));}
            if (News.nm.getItemConfig().getYaml().contains(name+".tag")) {item.setNBTString("tag",News.nm.getItemConfig().getYaml().getString(name+".tag"));}
            item.setNBTString("itemname",name);
            return item.build();
        }
        return null;
    }
}
