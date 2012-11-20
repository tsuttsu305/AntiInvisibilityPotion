package tsuttsu305.Main;

import java.util.ArrayList;
import java.util.List;
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

    private final transient List<Material> allowedBlocks;
    
    public Event() {
        allowedBlocks = new ArrayList<Material>();
        allowedBlocks.add(Material.CHEST);
        allowedBlocks.add(Material.DISPENSER);
        allowedBlocks.add(Material.STONE_BUTTON);
        allowedBlocks.add(Material.WOOD_BUTTON);
        allowedBlocks.add(Material.LEVER);
        allowedBlocks.add(Material.FURNACE);
        allowedBlocks.add(Material.WOOD_DOOR);
        allowedBlocks.add(Material.IRON_DOOR);
        allowedBlocks.add(Material.BEACON);
        allowedBlocks.add(Material.ANVIL);
        allowedBlocks.add(Material.BED);
        allowedBlocks.add(Material.BOAT);
        allowedBlocks.add(Material.BREWING_STAND);
        allowedBlocks.add(Material.BURNING_FURNACE);
        allowedBlocks.add(Material.CAKE_BLOCK);
        allowedBlocks.add(Material.DRAGON_EGG);
        allowedBlocks.add(Material.ENCHANTMENT_TABLE);
        allowedBlocks.add(Material.ENDER_CHEST);
        allowedBlocks.add(Material.ITEM_FRAME);
        allowedBlocks.add(Material.JUKEBOX);
        allowedBlocks.add(Material.LOCKED_CHEST);
        allowedBlocks.add(Material.MINECART);
        allowedBlocks.add(Material.NOTE_BLOCK);
        allowedBlocks.add(Material.POWERED_MINECART);
        allowedBlocks.add(Material.WORKBENCH);
    }

    @SuppressWarnings({ "deprecation" })
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onPlayeruse(final PlayerInteractEvent event) {
        if (event.getItem() != null) {
            final Player player = event.getPlayer();
            final ItemStack playerInHand = player.getItemInHand();
            final Material playerInMate = playerInHand.getType();

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

                // Deny if...
                // The potion is an invisibility potion
                // and the player has no permission, and is also not op
                // and the action is a right click on air
                // or any block which is not included in the allowed list.
                if (poEType == PotionEffectType.INVISIBILITY
                        && !player.hasPermission("invisibility.on")
                        && !player.isOp()
                        && ((event.getAction() == Action.RIGHT_CLICK_BLOCK
                        && !allowedBlocks.contains(event.getClickedBlock().getType()))
                        || event.getAction() == Action.RIGHT_CLICK_AIR)) {
                    player.sendMessage(ChatColor.RED + Main.msg);
                    event.setCancelled(true);
                    player.updateInventory();
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onDispense(final BlockDispenseEvent event){
        final BlockDispenseEvent BDE = event;
        final ItemStack item = BDE.getItem();

        if (item.getType() == Material.POTION)
        {
            final Potion po = Potion.fromItemStack(item);

            final PotionEffectType poet = po.getType().getEffectType();

            if (poet == PotionEffectType.INVISIBILITY){
                BDE.setCancelled(true);
            }
        }
    }
}
