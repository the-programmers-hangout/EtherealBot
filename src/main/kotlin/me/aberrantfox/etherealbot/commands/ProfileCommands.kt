package me.aberrantfox.etherealbot.commands

import me.aberrantfox.etherealbot.persistence.Database
import me.aberrantfox.kjdautils.api.dsl.CommandSet
import me.aberrantfox.kjdautils.api.dsl.commands

@CommandSet("profiles")
fun profiles(database: Database) = commands {
    command("profile") {
        execute {
            database.createProfile(it.author.id)
        }
    }

    command("getprofile") {
        execute {
            it.respond(database.getProfile(it.author.id)._id)
        }
    }
}
