package me.Xocky.News.core.hook;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Hook {
    private String pluginname;
    private boolean isEnabled;
    public Hook(String pluginname) {
        this.pluginname = pluginname;
        this.isEnabled = checkEnabled();
        announce();
    }
    private boolean checkEnabled() {
        return Bukkit.getPluginManager().getPlugin(pluginname) != null;
    }
    public boolean isEnabled() {
        return this.isEnabled;
    }
    public String getPluginName() {
        return this.pluginname;
    }
    private void announce() {
        if (isEnabled) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"Enabled support for "+pluginname);
            return;
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Disabled support for "+pluginname);
    }
}
