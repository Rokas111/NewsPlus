package me.Xocky.News.core.hook;

import me.Xocky.News.core.hook.hooks.PlaceHolderAPI;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HookManager {
    private Plugin pl;
    private List<Hook> hooks;
    public HookManager(Plugin pl) {
        this.pl = pl;
        this.hooks = new ArrayList<>();
    }
    public void initialize() {
        registerHooks();
    }
    private void registerHooks() {
        hooks.add(new PlaceHolderAPI());
    }
    public List<Hook> getHooks() {
        return this.hooks;
    }
    public Hook getHookByName(String name) {
        return !hooks.stream().filter(hook -> hook.getPluginName().equals(name)).collect(Collectors.toList()).isEmpty()
                ?hooks.stream().filter(hook -> hook.getPluginName().equals(name)).collect(Collectors.toList()).get(0)
                :null;
    }
}
