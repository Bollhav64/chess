package chess

class Rook(_team: String) : Piece(_team) {

    override fun validMove(target: String, board: Board): Boolean {
        val original: String = position(board)

        return straightLine(original, target, board)
    }
}