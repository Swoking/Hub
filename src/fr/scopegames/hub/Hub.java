package fr.scopegames.hub;

import fr.scopegames.hub.customentities.CustomEntityType;
import fr.scopegames.hub.listener.ListenerManager;
import fr.dinnerwolph.scopegamesapi.utils.UpdateUtils;
import java.io.IOException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Hub
extends JavaPlugin {
    public void onEnable() {
        new ListenerManager(this);
        try {
            new UpdateUtils((Plugin)this);
        }
        catch (IOException e) {
            // empty catch block
        }
        CustomEntityType.registerEntities();
    }

    public void onDisable() {
        CustomEntityType.unregisterEntities();
    }
}