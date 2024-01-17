package org.super89.supermegamod.learningplugin;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.super89.supermegamod.learningplugin.Utils.ItemUtils;

import java.util.HashMap;

import static org.bukkit.Bukkit.getServer;

public class GUI implements Listener {
    public static HashMap getHashMap(){
        HashMap<Player, Boolean> playerSwitches = new HashMap<Player, Boolean>();
        return playerSwitches;

    }
    HashMap playerSwitches = getHashMap();
    private ArmorStand armorStand;


    EventS eventS = new EventS();
    ArmorStand as = eventS.armorStand;
    public static void openGUI(Player player){
        Inventory inv = Bukkit.createInventory(null, 54, "     Верстак");
        for (int i = 0; i < 18; i++) {
            inv.setItem(i, ItemUtils.create(Material.PURPLE_STAINED_GLASS_PANE, " "));
        }
        inv.setItem(25,ItemUtils.create(Material.NETHERITE_HELMET, "Получить незеритовый сет"));
        inv.setItem(53, ItemUtils.create(Material.BARRIER, ChatColor.RED +"Выход из Верстака"));
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        OfflinePlayer offlinePlayer = player;
        meta.setOwningPlayer(offlinePlayer);
        meta.setDisplayName(ChatColor.GOLD + "Питомец " +ChatColor.WHITE + player.getDisplayName());
        skull.setItemMeta(meta);
        if (player.hasPermission("pet.1")) {
            inv.setItem(18, skull);
        }

        player.openInventory(inv);





    }
    @EventHandler
    public void InventoryClick(InventoryClickEvent e){
        boolean b = false;
        Player p = (Player) e.getWhoClicked();
        Location loc = p.getLocation();
        if(e.getView().getTitle().equalsIgnoreCase("     Верстак" ) && e.getCurrentItem().getType() != null){
            e.setCancelled(true);
            if(e.getCurrentItem().getType() == Material.BARRIER){
                p.closeInventory();
            }
            else if(e.getCurrentItem().getType() == Material.NETHERITE_HELMET){
                p.getInventory().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
                p.getInventory().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
                p.getInventory().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
                p.getInventory().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
                p.sendMessage(ChatColor.GOLD + "Вы надели незеритовый сет");
                p.closeInventory();
            } else if (e.getCurrentItem().getType() == Material.PLAYER_HEAD) {

                if (!playerSwitches.containsKey(p)) {
                    playerSwitches.put(p, false);
                    p.setMaxHealth(22);
                }
                boolean b1 = (boolean) playerSwitches.get(p);

                if(!b1){
                    getServer().getPluginManager().registerEvents(eventS, PetPlugin.getPlugin(PetPlugin.class));
                    playerSwitches.put(p,true);
                    p.closeInventory();

                }
                if(b1) {
                    ArmorStand as = eventS.getArmorStand();
                    double x = 0;
                    double y = 10000;
                    double z = 0;



                    HandlerList.unregisterAll(eventS);
                    playerSwitches.put(p,false);
                    as.teleport(new Location(p.getWorld(), x,y,z));
                    p.closeInventory();
                }



            } else{
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void getClickedBlock(PlayerInteractEvent e){
        if(e.getClickedBlock() != null){
            Player p = (Player) e.getPlayer();
            Block block = e.getClickedBlock();

            if(block.getType().equals(Material.CRAFTING_TABLE)){

                e.setCancelled(true);
                openGUI(p);
            }
            else {
                e.setCancelled(false);
           }
        }
    }

}
