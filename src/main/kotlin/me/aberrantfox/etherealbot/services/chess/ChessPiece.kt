package me.aberrantfox.etherealbot.services.chess


interface MovingPiece {
    fun canMove(board: ChessBoard): Boolean
}

sealed class TeamColour(val name: String = "NoColour") {
    object Black : TeamColour("black")
    object White : TeamColour("white")
    object NoColour : TeamColour()
}

sealed class ChessPiece(val name: String, val colour: TeamColour = TeamColour.NoColour) : MovingPiece


class Pawn(colour: TeamColour) : ChessPiece("Pawn", colour) {
    override fun canMove(board: ChessBoard) = false
}

class Rook(colour: TeamColour) : ChessPiece("Rook", colour) {
    override fun canMove(board: ChessBoard) = false
}

class Knight(colour: TeamColour) : ChessPiece("Knight", colour) {
    override fun canMove(board: ChessBoard) = false
}

class Bishop(colour: TeamColour) : ChessPiece("Bishop", colour) {
    override fun canMove(board: ChessBoard) = false
}

class Queen(colour: TeamColour) : ChessPiece("Queen", colour) {
    override fun canMove(board: ChessBoard) = false
}

class King(colour: TeamColour) : ChessPiece("King", colour) {
    override fun canMove(board: ChessBoard) = false
}

class None(colour: TeamColour) : ChessPiece("None", colour) {
    override fun canMove(board: ChessBoard) = false
}