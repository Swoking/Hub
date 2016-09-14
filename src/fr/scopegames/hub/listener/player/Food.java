package fr.scopegames.hub.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Food
implements Listener {
    @EventHandler
    public void Food(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}