package tsuttsu305.AntiInvisibilityPotion;

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
            if (event.getItem() != null) {
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
                                case WOOD_DOOR:
                                case IRON_DOOR:
                                case BEACON:
                                case ANVIL:
                                case BED:
                                case BOAT:
                                case BREWING_STAND:
                                case BURNING_FURNACE:
                                case CAKE_BLOCK:
                                case DRAGON_EGG:
                                case ENCHANTMENT_TABLE:
                                case ENDER_CHEST:
                                case ITEM_FRAME:
                                case JUKEBOX:
                                case LOCKED_CHEST:
                                case MINECART:
                                case NOTE_BLOCK:
                                case POWERED_MINECART:
                                case WORKBENCH:
                                    return;
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
