package org.super89.supermegamod.learningplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.super89.supermegamod.learningplugin.Utils.ItemUtils;

public final class PetPlugin extends JavaPlugin {
    EventS eventS = new EventS();
    GUI gui = new GUI();

    @Override
    public void onEnable() {
        NamespacedKey key = new NamespacedKey(this, "nice_pet");

        ItemStack i = ItemUtils.CreatePlayerHead("Super89");
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Открыть яйцо");
        i.setItemMeta(meta);
        ItemStack item = ItemUtils.create(Material.SPONGE, "Великая губка");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
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
