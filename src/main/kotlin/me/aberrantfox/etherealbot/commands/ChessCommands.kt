package me.aberrantfox.etherealbot.commands

import me.aberrantfox.etherealbot.services.chess.createStartingBoard
import me.aberrantfox.kjdautils.api.dsl.CommandSet
import me.aberrantfox.kjdautils.api.dsl.arg
import me.aberrantfox.kjdautils.api.dsl.commands
import me.aberrantfox.kjdautils.internal.command.arguments.DoubleArg
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.roundToInt

private const val imagePath = "image.png"

@CommandSet("testing")
fun chessCommands() = commands {
    command("board") {
        expect(arg(DoubleArg, true, 1.0))
        execute {
            val scale = it.args.component1() as Double
            val board = createStartingBoard(scale)

            it.channel.sendFile(board.getImage()).queue()
        }
    }
}
