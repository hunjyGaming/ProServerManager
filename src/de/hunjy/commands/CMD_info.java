package de.hunjy.commands;
/*
    Create by RiedCrafter on 02.10.2019
    @author: RiedCrafter
    @date: 02.10.2019
    @time: 23:47
    @projekt: ProServerManager
*/

import de.hunjy.PSM;
import de.hunjy.utils.commands.PSMCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class CMD_info implements PSMCommand, Listener {
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Zeigt Informationen über das System";
    }

    @Override
    public void execute(Player player, String[] args) {
        if (player.hasPermission("psm.admin")) {
            if (args.length == 1) {
                loadInfoGUI(player);
                player.sendMessage(PSM.Prefix + "§7Du siehst nun informationen über das Projekt.");
            } else {
                player.sendMessage(PSM.Prefix + "§cDieser Command existiert nicht!");
            }
        }
    }

    private void loadInfoGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null,9*3,"§8» §bInfo§7-§bGUI §8«");
        inventory.setItem(0, getFill());
        inventory.setItem(1, getFill());
        inventory.setItem(2, getFill());
        inventory.setItem(3, getFill());
        inventory.setItem(4, getFill());
        inventory.setItem(5, getFill());
        inventory.setItem(6, getFill());
        inventory.setItem(7, getFill());
        inventory.setItem(8, getFill());
        inventory.setItem(9, getFill());
        inventory.setItem(10, getFill());
        inventory.setItem(11, getHunjy());
        inventory.setItem(12, getFill());
        inventory.setItem(13, getHistory());
        inventory.setItem(14, getFill());
        inventory.setItem(15, getRiedCrafter());
        inventory.setItem(16, getFill());
        inventory.setItem(17, getFill());
        inventory.setItem(18, getFill());
        inventory.setItem(19, getFill());
        inventory.setItem(20, getFill());
        inventory.setItem(21, getFill());
        inventory.setItem(22, getFill());
        inventory.setItem(23, getFill());
        inventory.setItem(24, getFill());
        inventory.setItem(25, getFill());
        inventory.setItem(26, getFill());
        player.openInventory(inventory);
    }

    private static ItemStack getHunjy() {

        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setDisplayName("§bDeveloper §8× §bhunjy §7(§cFlorian§7)");
        itemMeta.setOwner("hunjy");
        itemMeta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Entwickler des §3ProServerManager's");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;

    }

    private static ItemStack getRiedCrafter() {

        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setDisplayName("§bDeveloper §8× §bRiedCrafter §7(§cMelvin§7)");
        itemMeta.setOwner("RiedCrafter");
        itemMeta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Entwickler des §3ProServerManager's");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;

    }

    private static ItemStack getHistory() {

        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setDisplayName("§3ProServerManager");
        itemMeta.setOwner("MHF_Question");
        itemMeta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Das §3PSM §7war eine Idee von hunjy und RiedCrafter, welche sie");
        lore.add("§7am Anfang des §eOktober 2019 §7hatten und so schnell wie möglich");
        lore.add("§7umsetzen wollten§8.");
        lore.add("§7Dabei war ihre Intension hinter dem damals genannten §8'§cProjekt X§8'");
        lore.add("§7die Ermöglichung eines eigenen Server's für viele Leute§8, §7indem sie");
        lore.add("§7dieses System kostenfrei zum Download mit API zur verfügung stellen§8,");
        lore.add("§7sodass man seine eigenen Module dazu programmieren kann oder kosten-");
        lore.add("§7pflichtig ihre Module zu kaufen§8.");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;

    }

    private static ItemStack getFill() {
        ItemStack fill = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta meta = fill.getItemMeta();
        meta.setDisplayName("§a");
        fill.setItemMeta(meta);
        return fill;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        if(event.getInventory().getName().equals("§8» §bInfo§7-§bGUI §8«")) {
            event.setResult(Event.Result.DENY);
            event.setCancelled(true);
        }
    }

}
