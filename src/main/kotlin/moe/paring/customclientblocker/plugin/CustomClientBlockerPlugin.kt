package moe.paring.customclientblocker.plugin

import io.github.monun.tap.util.updateFromGitHub
import moe.paring.customclientblocker.command.commands
import moe.paring.customclientblocker.listener.JoinHandler
import moe.paring.customclientblocker.util.kickIfBlocked
import org.bukkit.plugin.java.JavaPlugin

class CustomClientBlockerPlugin : JavaPlugin() {
    companion object {
        lateinit var plugin: CustomClientBlockerPlugin
    }

    var kickMessage = ""
    val blockedClients = mutableListOf<String>()

    override fun onEnable() {
        plugin = this

        updateFromGitHub("pikokr", "CustomClientBlocker", "CustomClientBlocker.jar") {
            getOrNull()?.let {
                logger.info("Update available! Download at https://github.com$it")
            }
        }

        saveDefaultConfig()
        kickMessage = config.getString("kickMessage") ?: ""

        server.pluginManager.registerEvents(JoinHandler(), this)

        commands()

        reloadBlacklist()
    }

    fun reloadBlacklist() {
        blockedClients.clear()
        blockedClients.addAll(config.getStringList("blacklist"))
    }

    fun save() {
        config.set("blacklist", blockedClients)
        saveConfig()
        server.onlinePlayers.forEach { it.kickIfBlocked() }
    }
}
