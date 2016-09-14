package fr.scopegames.hub.listener.inventory;

import fr.scopegames.hub.mount.MountManager;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Click
implements Listener {
    private ItemStack target;
    private ItemStack itemStack;
    private ItemMeta itemMeta;

    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {
        this.target = null;
        this.target = event.getCurrentItem();
        try {
            if (this.target.getItemMeta().getDisplayName().equals("Boutique") && this.target.getType() == Material.GOLD_INGOT) {
                event.setCancelled(true);
                this.Boutique((Player)event.getWhoClicked());
            } else if (this.target.getItemMeta().getDisplayName().equals("Monture") && this.target.getType() == Material.SADDLE) {
                event.setCancelled(true);
                this.Monture((Player)event.getWhoClicked());
            } else if (this.target.getItemMeta().getDisplayName().equals("Monture de Creeper") && this.target.getType() == Material.SULPHUR) {
                event.setCancelled(true);
                MountManager.RideCreeper((Player)event.getWhoClicked());
            } else if (this.target.getItemMeta().getDisplayName().equals("Monture de Spider") && this.target.getType() == Material.SPIDER_EYE) {
                event.setCancelled(true);
                MountManager.RideSpider((Player)event.getWhoClicked());
            } else if (this.target.getItemMeta().getDisplayName().equals("Monture de Sheep") && this.target.getType() == Material.WOOL) {
                event.setCancelled(true);
                MountManager.RideSheep((Player)event.getWhoClicked());
            } else if (this.target.getItemMeta().getDisplayName().equals("Monture de Cow") && this.target.getType() == Material.LEATHER) {
                event.setCancelled(true);
                MountManager.RideCow((Player)event.getWhoClicked());
            } else if (this.target.getItemMeta().getDisplayName().equals("Monture de Chicken") && this.target.getType() == Material.FEATHER) {
                event.setCancelled(true);
                MountManager.RideChicken((Player)event.getWhoClicked());
            }
        }
        catch (NullPointerException exception) {
            // empty catch block
        }
    }

    private void Boutique(Player player) {
        Inventory inventory = Bukkit.createInventory((InventoryHolder)player, (int)9, (String)"Boutique");
        this.itemStack = new ItemStack(Material.SADDLE);
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setDisplayName("Monture");
        this.itemStack.setItemMeta(this.itemMeta);
        inventory.addItem(new ItemStack[]{this.itemStack});
        player.openInventory(inventory);
    }

    public void Monture(Player player) {
        Inventory inventory = Bukkit.createInventory((InventoryHolder)player, (int)9, (String)"Monture");
        this.itemStack = new ItemStack(Material.SULPHUR);
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setDisplayName("Monture de Creeper");
        this.itemStack.setItemMeta(this.itemMeta);
        inventory.addItem(new ItemStack[]{this.itemStack});
        this.itemStack = new ItemStack(Material.SPIDER_EYE);
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setDisplayName("Monture de Spider");
        this.itemStack.setItemMeta(this.itemMeta);
        inventory.addItem(new ItemStack[]{this.itemStack});
        this.itemStack = new ItemStack(Material.WOOL);
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setDisplayName("Monture de Sheep");
        this.itemStack.setItemMeta(this.itemMeta);
        inventory.addItem(new ItemStack[]{this.itemStack});
        this.itemStack = new ItemStack(Material.LEATHER);
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setDisplayName("Monture de Cow");
        this.itemStack.setItemMeta(this.itemMeta);
        inventory.addItem(new ItemStack[]{this.itemStack});
        this.itemStack = new ItemStack(Material.FEATHER);
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setDisplayName("Monture de Chicken");
        this.itemStack.setItemMeta(this.itemMeta);
        inventory.addItem(new ItemStack[]{this.itemStack});
        player.openInventory(inventory);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        try {
            if (event.getItem().getItemMeta().getDisplayName().equals("Boutique") && event.getItem().getType() == Material.GOLD_INGOT) {
                this.Boutique(event.getPlayer());
            }
        }
        catch (NullPointerException exception) {
            // empty catch block
        }
    }
}