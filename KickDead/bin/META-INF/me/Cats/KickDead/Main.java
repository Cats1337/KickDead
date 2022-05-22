package me.Cats.KickDead;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
  public static boolean kickOnDeath = true;
  
  public void onEnable() {
    if (kickOnDeath)
      System.out.println("Death Kick is enabled"); 
    getServer().getPluginManager().registerEvents(this, (Plugin)this);
  }
  
  public void onDisable() {}
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (label.equalsIgnoreCase("kd")) {
      if (sender instanceof Player) {
        Player player = (Player)sender;
        if (player.hasPermission("kickdead.use")) {
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlayers will now be sent to the &8&lShadow Realm&b. &r(&0&aOn&r)"));
          kickOnDeath = true;
          return true;
        } 
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYour deck lacks the power to send someone to the &8&lShadow Realm&r&b."));
      } else {
        sender.sendMessage("Currently only works if ran by a player. I'm a bit lazy...");
        return true;
      } 
    } else if (label.equalsIgnoreCase("kdoff")) {
      if (sender instanceof Player) {
        Player player = (Player)sender;
        if (player.hasPermission("kickdead.use")) {
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlayers will be allowed on the &a&lMortal Plane&r&b. &r(&0&4Off&r)"));
          kickOnDeath = false;
          return true;
        } 
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYour deck lacks the power to allow people to walk this &a&lMortal Plane&r&b."));
      } else {
        sender.sendMessage("Currently only works if ran by a player. I'm a bit lazy...");
        return true;
      } 
    } 
    return false;
  }
  
  @EventHandler
  public void onDeath(EntityDeathEvent event) {
    if (kickOnDeath) {
      Player player = (Player)event.getEntity();
      getServer().dispatchCommand((CommandSender)getServer().getConsoleSender(), "tempban " + player.getName() + " 1m" + "§6§kCats§r§4§lYOU DIED§6§kCats§r");
    } 
  }
}
