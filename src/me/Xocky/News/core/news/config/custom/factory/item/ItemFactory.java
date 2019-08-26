package me.Xocky.News.core.news.config.custom.factory.item;

import me.Xocky.News.core.News;
import me.Xocky.News.core.news.config.custom.factory.text.Message;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ItemFactory {
    public ItemFactory() {

    }
    public ItemStack manufacture(String name) {
        if (News.NM.getItemConfig().getYaml().contains(name)) {
            BItem item = (News.NM.getItemConfig().getYaml().contains(name +".amount") || News.NM.getItemConfig().getYaml().contains(name +".durability"))?(News.NM.getItemConfig().getYaml().contains(name +".amount")?new BItem(new ItemStack(Material.valueOf(News.NM.getItemConfig().getYaml().getString(name+".material")),News.NM.getItemConfig().getYaml().getInt(name+".amount"),(short)News.NM.getItemConfig().getYaml().getInt(name+".durability"))):new BItem(new ItemStack(Material.valueOf(News.NM.getItemConfig().getYaml().getString(name+".material")),1,(short)News.NM.getItemConfig().getYaml().getInt(name+".durability")))):new BItem(new ItemStack(Material.valueOf(News.NM.getItemConfig().getYaml().getString(name+".material"))));
            if (News.NM.getItemConfig().getYaml().getBoolean(name+".enchanted")) {item.applyEnchantment(Enchantment.DAMAGE_ALL,1);}
            if (News.NM.getItemConfig().getYaml().contains(name+".name")) {item.setDisplayName(new Message(News.NM.getItemConfig().getYaml().getString(name+".name")).translate());}
            if (News.NM.getItemConfig().getYaml().contains(name+".lore")) {item.setLore(Arrays.asList(new Message(StringUtils.join(News.NM.getItemConfig().getYaml().getStringList(name+".lore")," /= ")).translate().split(" /= ")));}
            if (News.NM.getItemConfig().getYaml().contains(name+".command")) {item.setNBTString("command",News.NM.getItemConfig().getYaml().getString(name+".command"));}
            if (News.NM.getItemConfig().getYaml().contains(name+".tag")) {item.setNBTString("tag",News.NM.getItemConfig().getYaml().getString(name+".tag"));}
            item.setNBTString("itemname",name);
            return item.build();
        }
        return null;
    }
}
