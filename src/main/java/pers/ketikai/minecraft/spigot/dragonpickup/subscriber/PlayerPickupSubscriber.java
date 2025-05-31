/*
 *    dragon-pickup
 *    Copyright (C) 2025  ketikai
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package pers.ketikai.minecraft.spigot.dragonpickup.subscriber;

import eos.moe.dragoncore.network.PacketSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.ketikai.minecraft.spigot.dragonpickup.api.configuration.ConfigurationHolder;
import pers.ketikai.minecraft.spigot.dragonpickup.configuration.DragonPickupConfiguration;
import team.idealstate.sugar.next.context.annotation.component.Subscriber;
import team.idealstate.sugar.validate.annotation.NotNull;

@Subscriber
public class PlayerPickupSubscriber implements Listener, ConfigurationHolder {

    private DragonPickupConfiguration configuration;

    @EventHandler(priority = EventPriority.LOWEST)
    public void on(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        ItemStack original = event.getItem().getItemStack();
        ItemStack itemStack = new ItemStack(original.getType());
        itemStack.setAmount(original.getAmount());
        ItemMeta originalMeta = original.getItemMeta();
        String displayName;
        if (originalMeta != null && (displayName = originalMeta.getDisplayName()) != null) {
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(displayName);
            itemStack.setItemMeta(meta);
        }
        PacketSender.putClientSlotItem(player, configuration.getSlot(), itemStack);
        PacketSender.sendRunFunction(player, "Gui/" + configuration.getHud(), configuration.getFunction(), false);
    }

    @Override
    public void setConfiguration(@NotNull DragonPickupConfiguration configuration) {
        this.configuration = configuration;
    }
}
