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

private const val defaultPath = "/"
private const val defaultName = "config.json"

private val gson = GsonBuilder().setPrettyPrinting().create()

fun loadConfig(path: String = defaultPath, filename: String = defaultName): BotConfiguration? {
    val parent = File(path)
    parent.mkdirs()

    val file = File("$path${File.separator}$filename")

    return if(file.exists()) {
        gson.fromJson(file.readText(), BotConfiguration::class.java)
    } else {
        file.writeText(gson.toJson(BotConfiguration()))
        null
    }
}

fun BotConfiguration.save(path: String = defaultPath, name: String = defaultName) =
        File("$path${File.separator}$name").writeText(gson.toJson(this))
