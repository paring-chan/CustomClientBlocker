package moe.paring.customclientblocker.command

import moe.paring.customclientblocker.plugin.CustomClientBlockerPlugin
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.CommandSender

fun showBlacklist(sender: CommandSender) {
    val pl = CustomClientBlockerPlugin.plugin

    var component = Component.text()
        .appendNewline()
        .append(Component.text("[Blocked Clients]").color(NamedTextColor.GOLD))

    pl.blockedClients.forEach { client ->
        component = component.appendNewline()
            .append(Component.text("- "))
            .append(Component.text(client).color(NamedTextColor.GRAY)
                .clickEvent(ClickEvent.suggestCommand("/ccb unblock $client"))
                .hoverEvent(HoverEvent.showText(Component.text("Click to unblock"))))
    }

    if (pl.blockedClients.isEmpty())
        component = component.appendNewline()
            .append(Component.text("- Empty -").color(NamedTextColor.GRAY))

    sender.sendMessage(component)
}
