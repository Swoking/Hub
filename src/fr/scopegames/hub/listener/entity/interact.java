package fr.scopegames.hub.listener.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;

public class interact
implements Listener {
    @EventHandler
    public void onPlayerInteract(EntityInteractEvent event) {
        event.setCancelled(true);
    }
}