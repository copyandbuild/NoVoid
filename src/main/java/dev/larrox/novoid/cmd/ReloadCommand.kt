package dev.larrox.novoid.commands

import dev.larrox.novoid.NoVoid
import dev.larrox.novoid.events.EventPlayerMove
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ReloadCommand(private val plugin: NoVoid, private val eventPlayerMove: EventPlayerMove) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player && sender.hasPermission("novoid.reload")) {
            plugin.reloadConfig()
            eventPlayerMove.loadConfig()
            sender.sendMessage("§aConfig neu geladen")
            return true
        } else if (sender !is Player) {
            plugin.reloadConfig()
            eventPlayerMove.loadConfig()
            sender.sendMessage("Config wurde neu geladen")
            return true
        } else {
            sender.sendMessage("§cDu hast keine Berechtigung für diesen Befehl.")
            return true
        }
    }
}
