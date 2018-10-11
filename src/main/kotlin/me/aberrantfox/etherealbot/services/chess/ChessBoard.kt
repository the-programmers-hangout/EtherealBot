package me.aberrantfox.etherealbot.services.chess


import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import me.aberrantfox.etherealbot.services.chess.ChessPiece.*
import me.aberrantfox.etherealbot.services.chess.TeamColour.*
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.roundToInt

private const val imagePath = "board.png"

typealias State = ArrayList<ArrayList<ChessPiece>>

data class ChessBoard(val graphics: Graphics2D, val image: BufferedImage, val state: State, val size: Int) {
    fun getImage(): File {
        val file = File(imagePath)
        ImageIO.write(image, "png", file)

        return file
    }
}

fun createStartingBoard(scale: Double = 1.0): ChessBoard {
    val pair = createEmptyBoard(scale)
    val state = initialState

    val board = ChessBoard(pair.first, pair.second, state, (800 * scale).toInt())
    board.draw()

    return board
}

private fun ChessBoard.draw() {
    state.forEachIndexed { y, row ->
        row.forEachIndexed { x, piece ->
            drawToCell(graphics, piece, y, x, size)
        }
    }
}

private fun drawToCell(graphics: Graphics2D, piece: ChessPiece, y: Int, x: Int, size: Int) {
    if(piece is None) return

    val pawn = ImageIO.read(ChessBoard::class.java.getResourceAsStream("/sprites/${piece.colour.name}/${piece.name}.png"))
    val tileSize = size / 8
    graphics.drawImage(pawn, x * tileSize, y * tileSize, tileSize, tileSize, null)
}

private val initialState = arrayListOf(
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

    val tileDimension = size / 8

    graphics.color = Color.white

    (0..7).forEach { y ->
        (0..7).forEach { x ->
            drawTile(graphics, tileDimension * x, tileDimension * y, tileDimension)
            graphics.color = if (graphics.color == Color.white) Color.black else Color.white
        }
        graphics.color = if (graphics.color == Color.white) Color.black else Color.white
    }

    return Pair(graphics, image)
}

private fun drawTile(graphics2D: Graphics2D, x: Int, y: Int, size: Int) = graphics2D.apply {
    fillRect(x, y, size, size)
}