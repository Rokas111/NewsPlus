package me.Xocky.News.core.utils.pages;

import me.Xocky.News.core.utils.custom.gui.GUI;
import me.Xocky.News.core.utils.pages.interfaces.IGUIPage;
import org.bukkit.entity.Player;

public abstract class GUIPage implements IGUIPage {
    private GUI g;
    private Player p;
    private boolean cancel;
    public GUIPage(GUI g, Player p, boolean cancel) {
        this.g = g;
        this.p = p;
        this.cancel = cancel;
    }
    public GUI getGUI() {
        return this.g;
    }
    public Player getPlayer() {
        return this.p;
    }
    public boolean cancelClick() {
        return this.cancel;
    }
}
