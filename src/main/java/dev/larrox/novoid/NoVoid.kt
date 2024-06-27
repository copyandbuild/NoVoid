package dev.larrox.novoid

import dev.larrox.novoid.commands.ReloadCommand
import dev.larrox.novoid.events.EventPlayerMove
import org.bukkit.plugin.java.JavaPlugin

class NoVoid : JavaPlugin() {
    private lateinit var eventPlayerMove: EventPlayerMove

    override fun onEnable() {
        saveDefaultConfig()
        eventPlayerMove = EventPlayerMove(this)
        server.pluginManager.registerEvents(eventPlayerMove, this)
        getCommand("reloadnovoidconfig")?.setExecutor(ReloadCommand(this, eventPlayerMove))
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
