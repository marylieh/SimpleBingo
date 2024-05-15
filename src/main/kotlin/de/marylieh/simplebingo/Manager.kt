package de.marylieh.simplebingo

import de.marylieh.simplebingo.commands.*
import de.marylieh.simplebingo.config.ConfigManager
import de.marylieh.simplebingo.game.TimerManager
import net.axay.kspigot.commands.register
import net.axay.kspigot.main.KSpigot
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration

class InternalMainClass : KSpigot() {

    companion object {
        lateinit var INSTANCE: InternalMainClass; private set
    }

    override fun load() {
        ConfigManager.Config()
        INSTANCE = this
    }

    override fun startup() {
        registerCommands()
        this.saveDefaultConfig()
        ConfigManager.save()
        TimerManager.runTask()
    }

    override fun shutdown() {
        ConfigManager.save()
    }

    private fun registerCommands() {
        PassiveCommand().passiveCommand.register()
        TopCommand().topCommand.register()
        ReloadConfigCommand().reloadConfigCommand.register()
        BpCommand().bpCommand.register()
        SettingsCommand().settingsCommand.register()
        TimerCommand().timerCommand.register()
    }

    val prefix =
        Component.text("BINGO ", NamedTextColor.WHITE)
        .decoration(TextDecoration.BOLD, true)
        .append(Component.text("|", NamedTextColor.DARK_GRAY))
        .append(Component.text(" ").color(NamedTextColor.GRAY))

    val insufficientPermissions =
        prefix.append(Component.text("Dieser Befehl erfordert ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)
            .append(Component.text("erhöhte Berechtigungen").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, true))
            .append(Component.text(".", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false)))

}

val Manager by lazy { InternalMainClass.INSTANCE }
