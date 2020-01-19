package me.Xocky.News.core.news.config.custom.factory.gui;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.gui.GUI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GUIFactory {
    public GUIFactory() {

    }
    public GUI manufacture(String name) {
        if (News.nm.getGUIConfig().getYaml().contains(name)) {
            GUI g = new GUI(News.nm.getGUIConfig().getYaml().getString(name+".title"),News.nm.getGUIConfig().getYaml().getInt(name+".size"));
            for (String slot:News.nm.getGUIConfig().getYaml().getConfigurationSection(name+".slots").getKeys(false)) {
                g.setItem(Integer.parseInt(slot), (!News.nm.getGUIConfig().getYaml().getString(name+".slots."+slot).isEmpty())?News.nm.getItemFactory().manufacture(News.nm.getGUIConfig().getYaml().getString(name+".slots."+slot)): new ItemStack(Material.AIR));
            }
            if (News.nm.getGUIConfig().getYaml().contains(name + ".slottags") && !News.nm.getGUIConfig().getYaml().getConfigurationSection(name + ".slottags").getKeys(false).isEmpty()) {
                for (String slot : News.nm.getGUIConfig().getYaml().getConfigurationSection(name + ".slottags").getKeys(false)) {
                    g.setSlotTag(Integer.parseInt(slot), News.nm.getGUIConfig().getYaml().getString(name + ".slottags." + slot));
                }
            }
            if (News.nm.getGUIConfig().getYaml().contains(name + ".commandtags") && !News.nm.getGUIConfig().getYaml().getConfigurationSection(name + ".commandtags").getKeys(false).isEmpty()) {
                for (String slot : News.nm.getGUIConfig().getYaml().getConfigurationSection(name + ".commandtags").getKeys(false)) {
                    g.setCommandTag(Integer.parseInt(slot), News.nm.getGUIConfig().getYaml().getString(name + ".commandtags." + slot));
                }
            }
            return g;
        }
        return null;
    }
}
