package fr.scopegames.hub.listener;

import fr.scopegames.hub.Hub;
import fr.scopegames.hub.listener.entity.interact;
import fr.scopegames.hub.listener.inventory.Click;
import fr.scopegames.hub.listener.player.Chat;
import fr.scopegames.hub.listener.player.Dammage;
import fr.scopegames.hub.listener.player.Food;
import fr.scopegames.hub.listener.player.Join;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerManager {
    public ListenerManager(Hub plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents((Listener)new Join(), (Plugin)plugin);
        pluginManager.registerEvents((Listener)new Chat(), (Plugin)plugin);
        pluginManager.registerEvents((Listener)new Dammage(), (Plugin)plugin);
        pluginManager.registerEvents((Listener)new Food(), (Plugin)plugin);
        pluginManager.registerEvents((Listener)new Click(), (Plugin)plugin);
        pluginManager.registerEvents((Listener)new interact(), (Plugin)plugin);
    }
}