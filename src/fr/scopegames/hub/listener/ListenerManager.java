package fr.dinnerwolph.hub.listener;

import fr.dinnerwolph.hub.Hub;
import fr.dinnerwolph.hub.listener.entity.interact;
import fr.dinnerwolph.hub.listener.inventory.Click;
import fr.dinnerwolph.hub.listener.player.Chat;
import fr.dinnerwolph.hub.listener.player.Dammage;
import fr.dinnerwolph.hub.listener.player.Food;
import fr.dinnerwolph.hub.listener.player.Join;
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