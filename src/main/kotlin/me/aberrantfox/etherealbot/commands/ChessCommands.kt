package me.aberrantfox.etherealbot.commands

import me.aberrantfox.etherealbot.arguments.ChessCoordinate
import me.aberrantfox.etherealbot.services.chess.Point
import me.aberrantfox.etherealbot.services.chess.TeamColour
import me.aberrantfox.etherealbot.services.chess.createStartingBoard
import me.aberrantfox.kjdautils.api.dsl.CommandSet
import me.aberrantfox.kjdautils.api.dsl.arg
import me.aberrantfox.kjdautils.api.dsl.commands
import me.aberrantfox.kjdautils.internal.command.arguments.DoubleArg
import me.aberrantfox.kjdautils.internal.command.arguments.IntegerArg
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.roundToInt

private const val imagePath = "image.png"

private var sBoard = createStartingBoard()

@CommandSet("testing")
fun chessCommands() = commands {
    command("board") {
        expect(arg(DoubleArg, true, 1.0))
        execute {
            val scale = it.args.component1() as Double
            val board = createStartingBoard(scale)

            it.channel.sendFile(board.getImageFile()).queue()
        }
    }

    command("sboard") {
        expect(ChessCoordinate, ChessCoordinate)
        execute {
            val from = it.args.component1() as Point
            val to = it.args.component2() as Point

            sBoard.performMove(from, to, TeamColour.White)
            it.channel.sendFile(sBoard.getImageFile()).queue()
        }
    }
}
