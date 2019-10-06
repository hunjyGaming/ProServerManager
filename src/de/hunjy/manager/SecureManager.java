package de.hunjy.manager;
/*
    Create by RiedCrafter on 06.10.2019
    @author: RiedCrafter
    @date: 06.10.2019
    @time: 18:13
    @projekt: ProServerManager
*/

import de.hunjy.PSM;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecureManager implements Listener {

    private static int authKey;
    BukkitTask task;
    private static List<Player> checked = new ArrayList<>();
    private static List<Player> checking = new ArrayList<>();

    public void loadCheckInventory(Player player) {
        int randomSlot = new Random().nextInt(8);
        Inventory inventory = Bukkit.createInventory(null, 9, "§8× §bCheck§7-§bInventar §8×");
        for(int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, getFill());
        }
        inventory.setItem(randomSlot, getRight());
        player.openInventory(inventory);
        if(!checking.contains(player)) {
            checking.add(player);
            task = Bukkit.getScheduler().runTaskLater(PSM.getInstance(), () -> player.kickPlayer(PSM.Prefix + "§cDu hast die Zeit überschritten. §7(§e20s§7)"), 20*20);
        }
    }

    @EventHandler
    public void on(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(event.getBlock().getType() == Material.COMMAND) {
            if((boolean)PSM.getInstance().getMainConfig().get("secureSystem")) {
                if (!checked.contains(player)) {
                    event.setCancelled(true);
                    loadCheckInventory(player);
                }
            }
        }
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory().getName().equals("§8× §bCheck§7-§bInventar §8×")) {
            if (event.getCurrentItem().getType() == Material.INK_SACK) {
                player.closeInventory();
                authKey = new Random().nextInt(9999);
                player.sendMessage(PSM.Prefix + "§7Bitte autentifiziere dich indem du den AuthKey in den Chat eintippst§8.");
                player.sendMessage(PSM.Prefix + "§7AuthKey§8: §e" + authKey);
            } else {
                player.kickPlayer(PSM.Prefix + "§cDu hast dein Parameter falsch angegeben.");
            }
        }
    }

    @EventHandler
    public void on(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(checking.contains(player)) {
            checking.remove(player);
        }
        if(checked.contains(player)) {
            checked.remove(player);
        }
    }

    @EventHandler
    public void on(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if(checking.contains(player)) {
            event.setCancelled(true);
            if(event.getMessage().equals(authKey + "")) {
                player.sendMessage(PSM.Prefix + "§7Du wurdest erfolgreich §aautentifiziert§7.");
                checking.remove(player);
                checked.add(player);
                task.cancel();
            } else {
                player.sendMessage(PSM.Prefix + "§cDies ist nicht der AuthKey.");
            }
        }
    }

    private static ItemStack getRight() {
        ItemStack fill = new ItemStack(Material.INK_SACK, 1, (short) 10);
        ItemMeta meta = fill.getItemMeta();
        meta.setDisplayName("§aKlicke hier zum Bestätigen");
        fill.setItemMeta(meta);
        return fill;
    }

    private static ItemStack getFill() {
        ItemStack fill = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta meta = fill.getItemMeta();
        meta.setDisplayName("§a");
        fill.setItemMeta(meta);
        return fill;
    }

}
