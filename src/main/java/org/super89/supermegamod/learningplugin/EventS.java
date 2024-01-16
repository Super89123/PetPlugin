package org.super89.supermegamod.learningplugin;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class EventS implements Listener {
    GUI gui = new GUI();

    public ArmorStand armorStand;
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){


        if(event.getPlayer().hasPermission("pet.self")) {

            Player player = event.getPlayer();
            String s = player.getDisplayName();


            if (armorStand == null) {
                World world = player.getWorld();
                Location loc = player.getLocation();
                loc.setY(loc.getY() - 1);
                armorStand = SpawnMob.SpawnArmorStand(event.getPlayer().getLocation(), "20", event.getPlayer(), s, event.getPlayer());
                armorStand.setGravity(false);


            }

            Location loc = player.getLocation();
            double angle = Math.toRadians(loc.getYaw() + 360);  // Поворачиваем на 360 градусов, чтобы армор-стенд смотрел на игрока
            double radius = 1.5;  // Радиус вращения

            double x = loc.getX() + radius * Math.sin(angle);
            double y = loc.getY() + 1;  // Высота армор-стенда относительно игрока
            double z = loc.getZ() + radius * Math.cos(angle);

            armorStand.teleport(new Location(loc.getWorld(), x, y, z, loc.getYaw(), 0));
            armorStand.setRotation(loc.getYaw(), 0);

        }



    }
    public void onLeaveEvent(PlayerQuitEvent e){
        armorStand.remove();

    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }
}
