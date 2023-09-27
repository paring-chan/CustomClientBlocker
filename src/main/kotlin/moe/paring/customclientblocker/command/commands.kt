package moe.paring.customclientblocker.command

import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import moe.paring.customclientblocker.plugin.CustomClientBlockerPlugin
import moe.paring.customclientblocker.util.kickIfBlocked
import net.kyori.adventure.text.Component

fun CustomClientBlockerPlugin.commands() {
    kommand {
        register("clientblocker", "ccb") {
            requires { isConsole || hasPermission("clientblocker.command") }

            "players" {
                requires { isConsole || hasPermission("clientblocker.command.players") }
                executes { listPlayers(sender) }
            }

            "list" {
                requires { isConsole || hasPermission("clientblocker.command.players.list") }
                executes { showBlacklist(sender) }
            }

            "block" {
                requires {
                    isConsole || hasPermission("clientblocker.command.players.add") || hasPermission("clientblocker.command.players.modify")
                }

                then("name" to string()) {
                    executes { context ->
                        val name: String by context

                        blockedClients.add(name)
                        save()
                        server.onlinePlayers.forEach { it.kickIfBlocked() }
                        broadcast(Component.text("added $name to client blacklist"))
                    }
                }
            }

            "unblock" {
                requires {
                    isConsole || hasPermission("clientblocker.command.players.remove") || hasPermission("clientblocker.command.players.modify")
                }

                then("name" to string()) {
                    executes { context ->
                        val name: String by context

                        blockedClients.remove(name)
                        save()
                        server.onlinePlayers.forEach { it.kickIfBlocked() }
                        broadcast(Component.text("removed $name from client blacklist"))
                    }
                }
            }

            "reload" {
                requires { isConsole || hasPermission("commandblocker.command.reload") }

                executes {
                    reloadBlacklist()
                    broadcast(Component.text("Reloaded client blacklist!"))
                }
            }
        }
    }
}
