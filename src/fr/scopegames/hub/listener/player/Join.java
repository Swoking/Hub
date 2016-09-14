package fr.dinnerwolph.hub.listener.player;

import fr.dinnerwolph.scopegamesapi.player.ScopePlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class Join
implements Listener {
    private ItemStack itemStack;
    private ItemMeta itemMeta;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ScopePlayer scopePlayer = new ScopePlayer(event.getPlayer());
        if (scopePlayer.hasPerm("join.message.broadcast")) {
            event.setJoinMessage(scopePlayer.getPrefix() + " " + scopePlayer.getName() + " \u00e0 rejoins le hub !");
        } else {
            event.setJoinMessage(null);
        }
        this.initInventory((Inventory)scopePlayer.getInventory());
    }

    private void initInventory(Inventory inventory) {
        this.itemStack = new ItemStack(Material.GOLD_INGOT);
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setDisplayName("Boutique");
        this.itemStack.setItemMeta(this.itemMeta);
        inventory.setItem(8, this.itemStack);
    }
}