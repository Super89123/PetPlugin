package org.super89.supermegamod.learningplugin;

import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.naming.NamingEnumeration;

public final class LearningPlugin extends JavaPlugin {
    EventS eventS = new EventS();
    GUI gui = new GUI();

    @Override
    public void onEnable() {
        getServer().getPluginCommand("player_spawn").setExecutor(new SpawnMob(this));

        getServer().getPluginManager().registerEvents(gui, this);

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
