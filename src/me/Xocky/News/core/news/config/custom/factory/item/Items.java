package me.Xocky.News.core.news.config.custom.factory.item;

import me.Xocky.News.core.News;
import me.Xocky.News.legacy.SinceVersion;
import me.Xocky.News.legacy.Version;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.Arrays;
import java.util.List;

public enum Items {
    NONE_ITEM(" ","",null,new SinceVersion(Version.MC1_7_R4,"STAINED_GLASS_PANE" +"."+7), new SinceVersion(Version.MC1_13_R1,"LEGACY_STAINED_GLASS_PANE" +"." +7)),
    NEXT_PAGE_ITEM("&aNext Page","nextpage", Arrays.asList("&7Click to view the next page"),new SinceVersion(Version.MC1_7_R4,"ARROW" +"." +0)),
    PREVIOUS_PAGE_ITEM("&aPrevious Page","backpage",Arrays.asList("&7Click to view the previous page"),new SinceVersion(Version.MC1_7_R4,"ARROW" +"." +0)),
    EMPTY_SLOT("&cEmpty Slot","",null,new SinceVersion(Version.MC1_7_R4,"IRON_FENCE" +"." +0), new SinceVersion(Version.MC1_13_R1,"LEGACY_IRON_FENCE" +"." +0)),
    NEWS_UPDATE("&aUpdate Release","",Arrays.asList("&7Click to view the latest update"),new SinceVersion(Version.MC1_7_R4,"PAPER" +"." +0)),
    NEWS_BLOG("&aBlog 1","",Arrays.asList("&7Click to view the latest blog"),new SinceVersion(Version.MC1_7_R4,"PAPER" +"." +0));
    private BItem item;
    private Items(String displayname, String signature, List<String> lore, SinceVersion... sinces) {
        BItem item =null;
        for (int i = 0; i < sinces.length;i++) {
            SinceVersion vern = sinces[i];
            if (vern.getVersion().getVersionId() <= News.V.getVersionId() && (sinces.length == i +1  || sinces[i + 1].getVersion().getVersionId() > News.V.getVersionId())) {
                item = Short.parseShort(vern.getOutput().split("\\.")[1]) != 0?new BItem(Material.valueOf(vern.getOutput().split("\\.")[0]),1,Short.parseShort(vern.getOutput().split("\\.")[1])):new BItem(Material.valueOf(vern.getOutput().split("\\.")[0]));
            }
        }
        item.setDisplayName(displayname);
        if (lore != null && !lore.isEmpty()) {item.setLore(lore);}
        if (!signature.isEmpty()) {item.setSignature(signature);}
        this.item = item;
    }
    public BItem getItem() {
        return this.item;
    }
}
