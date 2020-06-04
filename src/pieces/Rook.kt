package chess

class Rook(_team: Team) : Piece(_team) {

    override fun validMove(target: String, board: Board): Boolean {
        val original: String = position(board)

        return straightLine(original, target, board)
    }
}