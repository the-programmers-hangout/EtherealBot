package me.aberrantfox.etherealbot.services.chess

sealed class MoveResult(val message: String) {
    object Success : MoveResult("Success")
    object PieceCantMoveLikeThat : MoveResult("That piece cannot move like that.")
    object PieceInTheWay : MoveResult("There is a piece in the way")
    object WouldResultInCheck : MoveResult("Moving there would result in check")
}