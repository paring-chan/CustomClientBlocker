package moe.paring.customclientblocker.listener

import moe.paring.customclientblocker.plugin.CustomClientBlockerPlugin
import moe.paring.customclientblocker.util.kickIfBlocked
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import java.util.function.Consumer

class JoinHandler : Listener {
    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val server = Bukkit.getServer()
        val pl = CustomClientBlockerPlugin.plugin

        server.scheduler.runTaskTimer(pl, Consumer {
            if (e.player.clientBrandName != null) {
                it.cancel()
                e.player.kickIfBlocked()
            }
        }, 10, 10)
    }
}
