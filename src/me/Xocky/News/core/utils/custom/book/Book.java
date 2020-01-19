package me.Xocky.News.core.utils.custom.book;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.item.BItem;
import me.Xocky.News.core.utils.custom.message.Message;
import me.Xocky.News.core.utils.legacy.Version;
import me.Xocky.News.core.utils.nbt.Classes;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private ItemStack item;
    private Player p;
    public Book(Player p) {
        this.item = new ItemStack(Material.WRITTEN_BOOK);
        this.p = p;
    }
    public Book(String author, String title, List<String[]> pages) {
        this.item = new ItemStack(Material.WRITTEN_BOOK);
        setAuthor(author);
        setTitle(title);
        for (String[] page : pages) {
            addPage(page);
        }
    }
    public Book(ItemStack book) {
        this.item = book;
    }
    public void setAuthor(String author) {
        BookMeta meta = (BookMeta) item.getItemMeta();
        meta.setAuthor(author);
        item.setItemMeta(meta);
    }
    public void setTitle(String title) {
        BookMeta meta = (BookMeta) item.getItemMeta();
        meta.setTitle(title);
        item.setItemMeta(meta);
    }
    public List<String[]> getPages() {
        List<String[]> pages = new ArrayList<>();
        BookMeta meta = (BookMeta) item.getItemMeta();
        meta.getPages().forEach(page-> pages.add(page.split("\n")));
        return pages;
    }
    public void addPage(String... lines) {
        BookMeta meta = (BookMeta) item.getItemMeta();
        ComponentBuilder text = new ComponentBuilder("");
        for (String line : lines) {

            text.append(new Message(line).translateAllJSON(p).create());
            text.append("\n");
        }
        try {
            List<Object> pages = (List<Object>) Classes.getCBukkitClass("inventory.CraftMetaBook").getDeclaredField("pages").get(meta);
            Object page = Classes.getNETClass("IChatBaseComponent$ChatSerializer").getMethod("a", new Class[]{String.class}).invoke(Classes.getNETClass("IChatBaseComponent$ChatSerializer"), new Object[]{ComponentSerializer.toString(text.create())});
            pages.add(page);
        } catch (InvocationTargetException | NoSuchFieldException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        item.setItemMeta(meta);
    }
    public void openBook() {
        int slot = p.getInventory().getHeldItemSlot();
        ItemStack old = p.getInventory().getItem(slot);
        p.getInventory().setItem(slot, item);
        try {
            ByteBuf buf = Unpooled.buffer(256);
            buf.setByte(0, (byte)0);
            buf.writerIndex(1);
            Object handle = p.getClass().getMethod("getHandle",new Class[0]).invoke(p,new Object[0]);
            Object pc = handle.getClass().getField("playerConnection").get(handle);
            if (News.v.getVersionId() >= Version.MC1_14_R1.getVersionId()) {
                Object enumhand = Classes.getNETClass("EnumHand").getMethod("valueOf", new Class[]{String.class}).invoke(Classes.getNETClass("EnumHand"),new Object[]{"MAIN_HAND"});
                Object bookpacket = Classes.getNETClass("PacketPlayOutOpenBook").getConstructor(new Class[]{Classes.getNETClass("EnumHand")}).newInstance(new Object[]{enumhand});
                pc.getClass().getMethod("sendPacket", new Class[]{Classes.getNETClass("Packet")}).invoke(pc, new Object[]{bookpacket});
            } else if (News.v.getVersionId() >= Version.MC1_13_R1.getVersionId()) {
                Object serializer = Classes.getNETClass("PacketDataSerializer").getConstructor(new Class[]{ByteBuf.class}).newInstance(new Object[]{buf});
                Object key = Classes.getNETClass("MinecraftKey").getConstructor(new Class[]{String.class}).newInstance(new Object[]{"minecraft:book_open"});
                Object packet = Classes.getNETClass("PacketPlayOutCustomPayload").getConstructor(new Class[]{Classes.getNETClass("MinecraftKey"), Classes.getNETClass("PacketDataSerializer")}).newInstance(new Object[]{key, serializer});
                pc.getClass().getMethod("sendPacket", new Class[]{Classes.getNETClass("Packet")}).invoke(pc, new Object[]{packet});
            }else {
                Object serializer = Classes.getNETClass("PacketDataSerializer").getConstructor(new Class[]{ByteBuf.class}).newInstance(new Object[]{buf});
                Object packet = Classes.getNETClass("PacketPlayOutCustomPayload").getConstructor(new Class[]{String.class, Classes.getNETClass("PacketDataSerializer")}).newInstance(new Object[]{"MC|BOpen", serializer});
                pc.getClass().getMethod("sendPacket", new Class[]{Classes.getNETClass("Packet")}).invoke(pc, new Object[]{packet});
            }
        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        p.getInventory().setItem(slot, old);
    }
    public BItem getItem() {
        return new BItem(this.item);
    }
}
