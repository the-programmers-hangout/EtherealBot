package me.aberrantfox.etherealbot

import me.aberrantfox.etherealbot.configuration.BotConfiguration
import me.aberrantfox.etherealbot.configuration.loadConfig
import me.aberrantfox.kjdautils.api.startBot

fun main(args: Array<String>) {
    val config = loadConfig()

    if(config != null) {
        start(config)
    } else {
        println("Please fill in the configuration file.")
    }
}

fun start(config: BotConfiguration) = startBot(config.token) {
    registerInjectionObject(config)
    registerCommands("me.aberrantfox.etherealbot", config.prefix)
    registerListenersByPath("me.aberrantfox.listeners")
}
