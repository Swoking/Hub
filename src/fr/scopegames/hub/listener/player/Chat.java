package fr.scopegames.hub.listener.player;

import fr.scopegames.scopegamesapi.player.ScopePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat
implements Listener {
    @EventHandler
    public void PlayerChatEvent(AsyncPlayerChatEvent event) {
        ScopePlayer scopePlayer = new ScopePlayer(event.getPlayer());
        Bukkit.broadcastMessage((String)(scopePlayer.getPrefix() + " " + scopePlayer.getName() + "\u00a7r > " + event.getMessage()));
        event.setCancelled(true);
    }
}