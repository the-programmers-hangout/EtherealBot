package me.aberrantfox.etherealbot.services.chess


import me.aberrantfox.etherealbot.arguments.numberLookup
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import me.aberrantfox.etherealbot.services.chess.TeamColour.*
import java.awt.Font
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.roundToInt

private const val imagePath = "board.png"

typealias State = ArrayList<ArrayList<ChessPiece>>

data class Point(val x: Int, val y: Int)

data class ChessBoard(val graphics: Graphics2D, val image: BufferedImage, val state: State, val size: Int) {
    fun getImageFile(): File {
        this.draw()
        val file = File(imagePath)
        ImageIO.write(image, "png", file)
        return file
    }

    fun performMove(from: Point, to: Point, colour: TeamColour): MoveResult {
        val fromElement = state[from.y][from.x]
        val toElement = state[to.y][to.x]

        if(toElement.colour == colour) {
            return MoveResult.PieceInTheWay
        }

        val canMove = fromElement.canMove(this, from, to)

        when(canMove) {
            is MoveResult.Success -> {
                state[from.y][from.x] = None()
                state[to.y][to.x] = fromElement
            }
            else -> println(canMove.message)
        }

        return canMove
    }

    private fun draw() {
        graphics.clearRect(0, 0, size, size)
        drawBackground(graphics, size)
        state.forEachIndexed { y, row ->
            row.forEachIndexed { x, piece ->
                drawToCell(graphics, piece, y, x, size)
            }
        }
    }
}

fun createStartingBoard(scale: Double = 1.0): ChessBoard {
    val pair = createEmptyBoard(scale)
    return ChessBoard(pair.first, pair.second, createInitialState(), (800 * scale).toInt())
}

private fun drawToCell(graphics: Graphics2D, piece: ChessPiece, y: Int, x: Int, size: Int) {
    if(piece is None) return

    val image = ImageIO.read(ChessBoard::class.java.getResourceAsStream("/sprites/${piece.colour.name}/${piece.name}.png"))
    val tileSize = size / 8
    val iconSize = Math.floor((size / 8) * 0.9).toInt()
    val margin = ((tileSize - iconSize) / 2).toInt()

    graphics.drawImage(image, (x * tileSize) + margin , (y * tileSize) + margin, iconSize, iconSize, null)
}

private fun createInitialState() = arrayListOf(
        arrayListOf(Rook(Black), Knight(Black), Bishop(Black), Queen(Black), King(Black), Bishop(Black), Knight(Black), Rook(Black)),
        createRowOf(Pawn(Black)),
        createRowOf(None(NoColour)),
        createRowOf(None(NoColour)),
        createRowOf(None(NoColour)),
        createRowOf(None(NoColour)),
        createRowOf(Pawn(White)),
        arrayListOf(Rook(White), Knight(White), Bishop(White), Queen(White), King(White), Bishop(White), Knight(White), Rook(White))
)

private fun createRowOf(piece: ChessPiece): ArrayList<ChessPiece> {
    val list = ArrayList<ChessPiece>()
    repeat(8) { list.add(piece) }

    return list
}

private fun createEmptyBoard(scale: Double): Pair<Graphics2D, BufferedImage> {
    val size = (800 * scale).roundToInt()

    val image = BufferedImage(size, size, BufferedImage.TYPE_INT_RGB)
    val graphics = image.createGraphics()

    drawBackground(graphics, size)

    return Pair(graphics, image)
}

private fun drawBackground(graphics: Graphics2D, size: Int) {
    val tileDimension = size / 8

    graphics.color = Color.white

    (0..7).forEach { y ->
        (0..7).forEach { x ->
            drawTile(graphics, tileDimension * x, tileDimension * y, tileDimension)
            graphics.color = if (graphics.color == Color.white) Color.darkGray else Color.white
        }
        graphics.color = if (graphics.color == Color.white) Color.darkGray else Color.white
    }

    graphics.font = Font("TimesRoman", Font.PLAIN, 20)
    graphics.color = Color.black


    (8 downTo 1).forEach {
        val locationalCoordinate = numberLookup[it]!!
        val bound = ((locationalCoordinate + 1) * tileDimension)
        val y = if(bound == 0) 30 else bound - 70

        graphics.drawString("$it", 3, y )
    }

    ('a'..'h').forEachIndexed { i, c ->
        graphics.drawString("$c", (i * tileDimension) + 2 , size - 3)
    }
}

private fun drawTile(graphics2D: Graphics2D, x: Int, y: Int, size: Int) = graphics2D.apply {
    fillRect(x, y, size, size)
}