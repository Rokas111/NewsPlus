package me.Xocky.News.core.news.config.custom.configs.defaults;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.item.BItem;
import me.Xocky.News.core.utils.legacy.SinceVersion;
import me.Xocky.News.core.utils.legacy.Version;
import org.bukkit.Material;

public enum Items {
    NONE_ITEM(new SinceVersion(Version.MC1_7_R4,"STAINED_GLASS_PANE.1.7. "), new SinceVersion(Version.MC1_13_R1,"LEGACY_STAINED_GLASS_PANE.1.7. " )),
    AIR_ITEM(new BItem(Material.valueOf("AIR"))),
    NEXT_PAGE_ITEM(new BItem(Material.valueOf("ARROW")).setDisplayName("&aNext Page").addLineLore("&7Click to view the next page").setSignature("nextpage")),
    PREVIOUS_PAGE_ITEM(new BItem(Material.valueOf("ARROW")).setDisplayName("&aPrevious Page").addLineLore("&7Click to view the previous page").setSignature("backpage")),
    EMPTY_SLOT(new SinceVersion(Version.MC1_7_R4,"IRON_FENCE.1.0.&cEmpty Slot"), new SinceVersion(Version.MC1_13_R1,"LEGACY_IRON_FENCE.1.0.&cEmpty Slot")),
    NEWS_UPDATE(new BItem(Material.valueOf("PAPER")).setDisplayName("&aUpdate Release").addLineLore("&7Click to view the latest update")),
    NEWS_BLOG(new BItem(Material.valueOf("PAPER")).setDisplayName("&aBlog 1").addLineLore("&7Click to view the latest blog"));
    private BItem item;
    private Items(SinceVersion... sinces) {
        BItem item =null;
        for (int i = 0; i < sinces.length;i++) {
            SinceVersion vern = sinces[i];
            if (vern.getVersion().getVersionId() <= News.v.getVersionId() && (sinces.length == i +1  || sinces[i + 1].getVersion().getVersionId() > News.v.getVersionId())) {
                item = new BItem(Material.valueOf(vern.getOutput().split("\\.")[0]),Integer.parseInt(vern.getOutput().split("\\.")[1]),Short.parseShort(vern.getOutput().split("\\.")[2]));
                if (vern.getOutput().split("\\.").length > 3) {item.setDisplayName(vern.getOutput().split("\\.")[3]);}
                if (vern.getOutput().split("\\.").length > 4) {item.addLineLore(vern.getOutput().split("\\.")[4]);}
                if (vern.getOutput().split("\\.").length > 5) {item.setSignature(vern.getOutput().split("\\.")[5]);}
            }
        }
        this.item = item;
    }
    private Items(BItem item) {
        this.item = item;
    }
    public BItem getItem() {
        return this.item;
    }
}
