package chess

class Bishop(_team: String) : Piece(_team) {
    override fun validMove(target: String, board: Board): Boolean {
        val original: String = position(board)

        return diagonalMove(original, target, board)
    }
}