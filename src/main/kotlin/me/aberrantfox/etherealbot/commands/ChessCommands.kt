package me.aberrantfox.etherealbot.commands

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
        expect(IntegerArg, IntegerArg, IntegerArg, IntegerArg)
        execute {
            val x1 = it.args.component1() as Int
            val y1 = it.args.component2() as Int
            val x2 = it.args.component3() as Int
            val y2 = it.args.component4() as Int

            sBoard.performMove(Point(x1,y1), Point(x2, y2), TeamColour.White)
            it.channel.sendFile(sBoard.getImageFile()).queue()
        }
    }
}
