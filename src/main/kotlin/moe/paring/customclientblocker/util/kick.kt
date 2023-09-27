package moe.paring.customclientblocker.util

import moe.paring.customclientblocker.plugin.CustomClientBlockerPlugin
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.entity.Player

fun Player.kickIfBlocked() {
    val pl = CustomClientBlockerPlugin.plugin
    pl.logger.info(clientBrandName)
    if (pl.blockedClients.contains(clientBrandName)) {
        val msg = pl.kickMessage.replace("%client%", clientBrandName!!)
        kick(runCatching {
            JSONComponentSerializer.json().deserialize(msg)
        }.getOrElse { LegacyComponentSerializer.legacyAmpersand().deserialize(msg) })
    }
}