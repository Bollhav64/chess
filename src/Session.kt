package chess

import java.lang.IllegalArgumentException

class Session {

    val board: Board = Board.newGameBoard()
    var turn: Team = Team.White

    fun move(piece: Piece, target: String) {
        val originalPosition: String = board.filterValues {
            it == piece
        }.keys.first()

        if (piece.validMove(target, board)) {
            board[target] = piece
            board.emptySquare(originalPosition)
        } else {
            throw IllegalArgumentException("Unvalid Move Target")
        }
    }
}