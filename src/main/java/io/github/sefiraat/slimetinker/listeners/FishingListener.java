package io.github.sefiraat.slimetinker.listeners;

import io.github.sefiraat.slimetinker.events.FishingEvents;
import io.github.sefiraat.slimetinker.events.friend.EventChannels;
import io.github.sefiraat.slimetinker.events.friend.EventFriend;
import io.github.sefiraat.slimetinker.events.friend.TraitEventType;
import io.github.sefiraat.slimetinker.modifiers.Modifications;
import io.github.sefiraat.slimetinker.utils.*;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.sefiraat.slimetinker.events.friend.EventChannels.settlePotionEffects;

public class FishingListener implements Listener {
    public static final Map<Location, EventFriend> EVENT_FRIEND_MAP = new HashMap<>();

    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled = true)
    public void onFishCaught(PlayerFishEvent event) {

        Player player = event.getPlayer();
        ItemStack inHand = player.getInventory().getItemInMainHand();
        if (Slimefun.getIntegrations().isEventFaked(event) ||
            event.isCancelled()
        ) {
            return;
        }

        if (ItemUtils.isTinkersBroken(inHand)) {
            event.setCancelled(true);
            event.getPlayer()
                .sendMessage(ThemeUtils.WARNING + "Your tool is broken, you should really repair it!");
        } else {
            Experience.addExp(inHand, 1, event.getPlayer(), true);
        }

        EventFriend friend = new EventFriend(player, TraitEventType.FISH);
        friend.setRod(inHand);
        if (friend.isActionTaken()) {
            settlePotionEffects(friend);
        }
    }
}
