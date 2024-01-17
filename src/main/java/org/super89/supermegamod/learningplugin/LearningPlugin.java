package org.super89.supermegamod.learningplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Item;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.super89.supermegamod.learningplugin.Utils.ItemUtils;

import javax.naming.NamingEnumeration;

public final class LearningPlugin extends JavaPlugin {
    EventS eventS = new EventS();
    GUI gui = new GUI();

    @Override
    public void onEnable() {

        ItemStack i = ItemUtils.CreatePlayerHead("Super89");
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Открыть яйцо");
        i.setItemMeta(meta);
        ShapedRecipe recipe = new ShapedRecipe(i);
        recipe.shape("DOD","GSG","DOD");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('S',Material.SPONGE);
        recipe.setIngredient('O', Material.OBSIDIAN);
        Bukkit.addRecipe(recipe);


        getServer().getPluginCommand("player_spawn").setExecutor(new SpawnMob(this));

        getServer().getPluginManager().registerEvents(gui, this);

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
