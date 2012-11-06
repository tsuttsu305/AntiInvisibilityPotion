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


	@SuppressWarnings({ "deprecation" })
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayeruse(PlayerInteractEvent event) {
		if ((event instanceof PlayerInteractEvent))
		{
			//PlayerInteractEvent aaaa = event;
			if (event.useItemInHand() != null) {
				Player player = event.getPlayer();
				ItemStack playerInHand = player.getItemInHand();
				Material playerInMate = playerInHand.getType();

				if (playerInMate == Material.POTION)
				{
					Potion po;
					//NoEffects Potion Avoid errors
					try {
						po = Potion.fromItemStack(playerInHand);
					} catch (Exception e) {
						// TODO: handle exception
						return;
					}

					PotionEffectType poEType;
					//NoEffects Potion Avoid errors
					try {
						poEType = po.getType().getEffectType();
					} catch (Exception e) {
						// TODO: handle exception
						return;
					}

					if (poEType == PotionEffectType.INVISIBILITY){
						if ((player.hasPermission("invisibility.on")) || (player.isOp())) {
							return;
						}

						//メッセージはconfigから呼び出し。
						if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
							switch (event.getClickedBlock().getType()){
								case CHEST:
								case DISPENSER:
								case STONE_BUTTON:
								case WOOD_BUTTON:
								case LEVER:
								case FURNACE:
									player.sendMessage(ChatColor.RED + Main.err_msg);
									break;

								default:
									player.sendMessage(ChatColor.RED + Main.msg);
									break;
							}
						}
						else if ( event.getAction() == Action.RIGHT_CLICK_AIR){
							player.sendMessage(ChatColor.RED + Main.msg);
						}

						event.setCancelled(true);
						player.updateInventory();
					}
				}
				return;
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











