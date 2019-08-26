package me.Xocky.News.core.news.config.custom.factory.gui;

import me.Xocky.News.core.News;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.stream.Collectors;

public class GUIFactory {
    public GUIFactory() {

    }
    public GUI manufacture(String name) {
        if (News.NM.getGUIConfig().getYaml().contains(name)) {
            GUI g = new GUI(News.NM.getGUIConfig().getYaml().getString(name+".title"),News.NM.getGUIConfig().getYaml().getInt(name+".size"));
            for (String slot:News.NM.getGUIConfig().getYaml().getConfigurationSection(name+".slots").getKeys(false)) {
                g.setItem(Integer.parseInt(slot), (!News.NM.getGUIConfig().getYaml().getString(name+".slots."+slot).isEmpty())?News.NM.getItemFactory().manufacture(News.NM.getGUIConfig().getYaml().getString(name+".slots."+slot)): new ItemStack(Material.AIR));
            }
            if (News.NM.getGUIConfig().getYaml().contains(name + ".slottags") && !News.NM.getGUIConfig().getYaml().getConfigurationSection(name + ".slottags").getKeys(false).isEmpty()) {
                for (String slot : News.NM.getGUIConfig().getYaml().getConfigurationSection(name + ".slottags").getKeys(false)) {
                    g.setSlotTag(Integer.parseInt(slot), News.NM.getGUIConfig().getYaml().getString(name + ".slottags." + slot));
                }
            }
            return g;
        }
        return null;
    }
}
