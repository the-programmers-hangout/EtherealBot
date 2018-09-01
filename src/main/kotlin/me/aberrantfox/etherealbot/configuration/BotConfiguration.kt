package me.aberrantfox.etherealbot.configuration

import com.google.gson.GsonBuilder
import java.io.File


data class BotConfiguration(val token: String = "insert-token-here",
                            val prefix: String = ":{",
                            val db: DatabaseCredentials = DatabaseCredentials()) {
    fun dbString() = db.run { "mongodb://$user:$password@$host:$port/$database" }
}

data class DatabaseCredentials(val user: String = "insert-user-here",
                               val password: String = "insert-password-here",
                               val host: String = "insert-host-here",
                               val port: String = "27017",
                               val database: String = "insert-database-here")

private const val path = "config.json"
private val file = File(path)
private val gson = GsonBuilder().setPrettyPrinting().create()

fun loadConfig(): BotConfiguration? =
        if(file.exists()) {
            gson.fromJson(file.readText(), BotConfiguration::class.java)
        } else {
            file.writeText(gson.toJson(BotConfiguration()))
            null
        }

fun save(configuration: BotConfiguration) = file.writeText(gson.toJson(configuration))