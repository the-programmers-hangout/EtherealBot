package me.aberrantfox.etherealbot

import me.aberrantfox.etherealbot.configuration.BotConfiguration
import me.aberrantfox.etherealbot.configuration.loadConfig
import me.aberrantfox.etherealbot.persistence.Database
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
    println("PROFILE STRING : " + config.dbString())
    val database = Database(config)
    registerInjectionObject(config, database)
    registerCommands("me.aberrantfox.etherealbot", ":{")
    registerListenersByPath("me.aberrantfox.listeners")
}