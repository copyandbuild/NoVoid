package dev.larrox.novoid.events

import dev.larrox.novoid.NoVoid
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class EventPlayerMove(private val plugin: NoVoid) : Listener {
    var worldname: String = "world"
    var voidY: Int = 64
    var voidlocation: Location? = null
    var teleportCommand: String = "spawn"

    init {
        loadConfig()
    }

    fun loadConfig() {
        plugin.reloadConfig()
        val config = plugin.config
        worldname = config.getString("void.worldname", "world") ?: "world"
        voidY = config.getInt("void.voidY", 64)
        teleportCommand = config.getString("void.teleportCommand", "spawn") ?: "spawn"

        val world = Bukkit.getWorld(worldname)
        if (world != null) {
            val x = 0.0
            val z = 0.0
            val yaw = 0.0f
            val pitch = 0.0f
            voidlocation = Location(world, x, voidY.toDouble(), z, yaw, pitch)
        } else {
            Bukkit.getLogger().warning("World $worldname not found!")
        }
    }

    fun updateWorldName(newWorldName: String) {
        worldname = newWorldName
        loadConfig()
    }

    fun updateVoidY(newVoidY: Int) {
        voidY = newVoidY
        loadConfig()
    }

    fun updateTeleportCommand(newCommand: String) {
        teleportCommand = newCommand
        loadConfig()
    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        val world = player.world
        val location = player.location

        if (world.name == worldname && location.y <= voidY) {
            player.performCommand(teleportCommand)
        }
    }
}
