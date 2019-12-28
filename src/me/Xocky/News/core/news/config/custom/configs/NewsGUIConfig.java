package me.Xocky.News.core.news.config.custom.configs;

import me.Xocky.News.core.News;
import me.Xocky.News.core.news.config.custom.configs.defaults.GUIs;
import me.Xocky.News.core.utils.config.Config;
import me.Xocky.News.core.utils.config.Section;
import me.Xocky.News.core.utils.custom.gui.GUI;
import org.bukkit.Material;

import java.io.IOException;
import java.util.HashMap;

public class NewsGUIConfig extends Config {
    private HashMap<GUI,String> defaults;
    public NewsGUIConfig() {
        super("Guis",new Section(new Section(News.PLUGIN_FOLDER),"Custom"));
        this.defaults = new HashMap<>();
    }
    public void addGUIDefault(GUI gui, String name) {
        defaults.put(gui,name);
    }
    public void addGUIDefaults(GUIs[] guis) {
        for (GUIs gui: guis) {
            addGUIDefault(gui.getGUI(),gui.toString().toLowerCase());
        }
    }
    public void checkSetup() {
        if (!setup()) {
            setupKeys();
        }
    }
    public void addDefault(GUI g, String name) {
        getYaml().set(name+".title",g.getTitle());
        getYaml().set(name+".size",g.getSize());
        for (Integer slot : g.getItems().keySet()) {
            getYaml().set(name+".slots."+slot,(g.getItems().get(slot).build()==null||g.getItems().get(slot).build().getType()== Material.AIR)?"":g.getItems().get(slot).getNBTString("itemname"));
        }
        if (!g.getSlotTags().isEmpty()) {
            for (Integer slot : g.getSlotTags().keySet()) {
                getYaml().set(name+".slottags."+slot,g.getSlotTags().get(slot));
            }
        }
        try {
            getYaml().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setupKeys() {
        defaults.keySet().forEach(gui -> addDefault(gui,defaults.get(gui)));
    }
}
