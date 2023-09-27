package moe.paring.customclientblocker.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

fun listPlayers(sender: CommandSender) {
    val server = Bukkit.getServer()

    var component = Component.text()
        .appendNewline()
        .append(Component.text("[Online Players]").color(NamedTextColor.GOLD))

    server.onlinePlayers.forEach { p ->
        component = component.appendNewline()
            .append(Component.text("- "))
            .append(Component.text(p.name).color(NamedTextColor.GRAY))
            .append(Component.text(" - "))
            .append(Component.text(p.clientBrandName ?: "Unknown").color(NamedTextColor.LIGHT_PURPLE).let { c ->
                p.clientBrandName?.let { name ->
                    c.clickEvent(
                        ClickEvent.suggestCommand("/ccb block $name")
                    )
                } ?: c
            }.hoverEvent(HoverEvent.showText(Component.text("Click to block"))))
    }

    sender.sendMessage(component)
}
