package tsuttsu305.Main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;

public class Event implements Listener {


	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayeruse(PlayerInteractEvent event) {
		if ((event instanceof PlayerInteractEvent))
		{


			PlayerInteractEvent aaaa = event;
			if (aaaa.useItemInHand() != null) {
				Player player = aaaa.getPlayer();
				ItemStack it = player.getItemInHand();
				Material abc = it.getType();

				if (abc == Material.POTION)
				{
					Potion po = Potion.fromItemStack(it);

					PotionEffectType poet = po.getType().getEffectType();

					if (poet == PotionEffectType.INVISIBILITY){
						if ((player.hasPermission("invisibility.on")) || (player.isOp())) {
							return;
						}

						if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
							Material bl = event.getClickedBlock().getType();
							if (bl == Material.CHEST){
								event.setCancelled(true);

								player.sendMessage(ChatColor.RED + "You may not open a container with an invisibility potion!");
								player.updateInventory();
								return;
							}else if (bl == Material.DISPENSER){
								event.setCancelled(true);

								player.sendMessage(ChatColor.RED + "You may not open a dispenser with an invisibility potion!");
								player.updateInventory();
								return;
							}else if (bl == Material.STONE_BUTTON || bl == Material.WOOD_BUTTON || bl == Material.LEVER){
								event.setCancelled(true);

								player.sendMessage(ChatColor.RED + "You cannot use button and lever. You have invisibility potion!");
								player.updateInventory();
								return;

							}else if (bl == Material.FURNACE) {
								event.setCancelled(true);

								player.sendMessage(ChatColor.RED + "You may not open a furnace with an invisibility potion!");
								player.updateInventory();
								return;

							}else{
							event.setCancelled(true);

							player.sendMessage(ChatColor.RED + "You don't have Permission to use this Potion!");
							player.updateInventory();
							return;
							}

						}else if ( event.getAction() == Action.RIGHT_CLICK_AIR){
							event.setCancelled(true);

							player.sendMessage(ChatColor.RED + "You don't have Permission to use this Potion!");
							player.updateInventory();
							return;
					}

				}

				return;
			}
		}
	}
}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onDispense(BlockDispenseEvent event){
		if (event instanceof BlockDispenseEvent){
			BlockDispenseEvent BDE = event;
			ItemStack item = BDE.getItem();
			Material mat = item.getType();

			if (mat == Material.POTION)
			{
				Potion po = Potion.fromItemStack(item);

				PotionEffectType poet = po.getType().getEffectType();

				if (poet == PotionEffectType.INVISIBILITY){
					BDE.setCancelled(true);
					return;



				}
			}

		}


	}

}












