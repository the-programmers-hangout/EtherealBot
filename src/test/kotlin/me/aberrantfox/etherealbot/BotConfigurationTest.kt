package me.aberrantfox.etherealbot

import me.aberrantfox.etherealbot.configuration.BotConfiguration
import me.aberrantfox.etherealbot.configuration.loadConfig
import me.aberrantfox.etherealbot.configuration.save
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.File

private const val testPath = "abc/testpath/"
private const val testName = "config.json"

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BotConfigurationTest {

    @Test
    fun generatesDefaultConfiguration() {
        val config = loadConfig(testPath, testName)

        //does not exist initially
        Assertions.assertNull(config)

        val defaultConfig = loadConfig(testPath)

        //Should have loaded a config
        Assertions.assertNotNull(defaultConfig)
    }

    @Test
    fun configMaintainsValues() {
        //generate the folder structure
        loadConfig(testPath, testName)

        val token = "test-token"
        val config = BotConfiguration(token)

        //save out our changes
        config.save(testPath, testName)

        val loadedConfig = loadConfig(testPath, testName)
        Assertions.assertNotNull(loadedConfig)
        Assertions.assertEquals(loadedConfig!!.token, token)
    }

    @AfterAll
    fun cleanup() {
        val file = File("abc/")
        file.deleteRecursively()
    }
}
