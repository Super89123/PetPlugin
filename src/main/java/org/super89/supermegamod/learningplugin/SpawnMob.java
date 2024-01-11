package org.super89.supermegamod.learningplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import  org.bukkit.World;

public class SpawnMob implements CommandExecutor {

    public static ArmorStand SpawnMobs;

    public SpawnMob(LearningPlugin learningPlugin) {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(command.getName().equalsIgnoreCase("player_spawn") && commandSender.hasPermission("omg.poco")){
            if(commandSender.hasPermission("omg.poco")){
                Player target = commandSender.getServer().getPlayer(strings[0]);
                Player player = (Player) commandSender;
                Location location = player.getLocation();

                SpawnArmorStand(location, strings[1], target, strings[0], player);
                return true;
            }
            else {
                commandSender.sendMessage("У вас нет прав!");

            }
            return true;
        }




        return false;
    }
    public static ArmorStand SpawnArmorStand(Location l, @NotNull String heath, Player tar, String name, Player player){
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        OfflinePlayer player1 = tar;
        meta.setOwningPlayer(player1);
        item.setItemMeta(meta);


        World world = l.getWorld();
        ArmorStand as = (ArmorStand) world.spawnEntity(l, EntityType.ARMOR_STAND);
        as.setCustomName("§7[Питомец] " + "§f"+ name);
        as.setCustomNameVisible(true);
        as.setHelmet(item);
        as.setArms(true);
        as.setVisible(false);
        as.setGravity(false);


        as.setSmall(true);
        as.setBasePlate(false);



        return as;
    }
}
