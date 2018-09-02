package me.aberrantfox.etherealbot.persistence

import com.mongodb.MongoClientURI
import me.aberrantfox.etherealbot.configuration.BotConfiguration
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.*
import java.util.*


data class Profile(@BsonId val  _id: String,
                   val description: String? = null,
                   val birthday: Date? = null)


class Database (val config: BotConfiguration) {
    private val client = KMongo.createClient(MongoClientURI(config.dbString()))
    private val database = client.getDatabase("EtherealBot")
    private val collection = database.getCollection("Profiles", Profile::class.java)

    fun createProfile(_id: String) {
        collection.insertOne(Profile(_id))
    }

    fun getProfile(_id: String) = collection.find(Profile::_id eq _id, Profile::class.java).first()
}
