package me.Xocky.News.core.utils.custom.item;

import com.google.common.collect.Lists;
import me.Xocky.News.core.utils.custom.message.Message;
import me.Xocky.News.core.utils.nbt.MainNBTTag;
import me.Xocky.News.core.utils.nbt.NBTStack;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BItem {
    private ItemStack item;
    public BItem(Material m) {
        this.item = new ItemStack(m);
    }
    public BItem(Material m, int amount) {
        this.item = new ItemStack(m,amount);
    }
    public BItem(Material m, int amount, short durability) {
        this.item = new ItemStack(m,amount,durability);
    }
    public BItem(ItemStack item) {
        this.item = item;
    }
    public Map<Enchantment, Integer> getEnchantments() {
        return item.getEnchantments();
    }
    public BItem applyEnchantment(Enchantment e, Integer level) {
        item.addUnsafeEnchantment(e,level);
        return this;
    }

    public BItem setEnchantments(Map<Enchantment,Integer> ench) {
        for (Enchantment e : ench.keySet()) {
            if (!getEnchantments().containsKey(e)) {
                applyEnchantment(e,ench.get(e));
            }
        }
        return this;
    }
    public String getDisplayName() {
        ItemMeta meta = item.getItemMeta();
        return meta.getDisplayName();
    }
    public BItem setDisplayName(String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(new Message((name)).translate());
        item.setItemMeta(meta);
        return this;
    }
    public List<String> getLore() {
        ItemMeta meta = item.getItemMeta();
        return meta.getLore();
    }
    public BItem setLore(List<String> name) {
        ItemMeta meta = item.getItemMeta();
        List<String> list = new ArrayList<>();
        for (String key : name) {
            list.add(new Message((key)).translate());
        }
        meta.setLore(list);
        item.setItemMeta(meta);
        return this;
    }
    public String getSignature() {
        return getNBTString("signature");
    }
    public BItem setSignature(String signature) {
        setNBTString("signature",signature);
        return this;
    }
    public String getNBTString(String nbtstring) {
        NBTStack itemnbt = new NBTStack(item);
        MainNBTTag tag = itemnbt.getTag();
        return tag.hasTag(nbtstring)?tag.getString(nbtstring).replaceAll("\"",""):null;
    }
    public BItem setNBTString(String nbtstring,String nbtvalue) {
        NBTStack itemnbt = new NBTStack(item);
        MainNBTTag tag = itemnbt.getTag();
        tag.setString(nbtstring,nbtvalue);
        itemnbt.setTag(tag);
        item = itemnbt.build();
        return this;
    }
    public boolean hasNBTString(String nbtstring) {
        NBTStack itemnbt = new NBTStack(item);
        return itemnbt.getTag().hasTag(nbtstring);
    }
    public BItem setDurability(short dura) {
        item.setDurability(dura);
        return this;
    }
    public Material getMaterial() {
        return item.getType();
    }
    public int getDurability() {
        return item.getDurability();
    }
    public int getAmount() {
        return item.getAmount();
    }
    public boolean hasMeta() {
        return item.hasItemMeta();
    }
    public boolean hasDisplayName() {
        return item.getItemMeta().hasDisplayName();
    }
    public boolean hasLore() {
        return item.getItemMeta().hasLore();
    }
    public boolean isEnchanted() {
        return item.getItemMeta().hasEnchants();
    }
    public ItemStack build() {
        return this.item;
    }
    public BItem addLineLore(String line) {
        List<String> lore = hasMeta()&&hasLore()?getLore():new ArrayList<>();
        lore.add(line);
        setLore(lore);
        return this;
    }
    public BItem addLineLore(String[] lines) {
        List<String> lore = hasMeta()&&hasLore()?getLore():new ArrayList<>();
        lore.addAll(Lists.newArrayList(lines));
        setLore(lore);
        return this;
    }
}
