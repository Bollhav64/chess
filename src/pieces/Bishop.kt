package chess

class Bishop(_team: Team) : Piece(_team) {
    override fun validMove(target: String, board: Board): Boolean {
        val original: String = position(board)

        return diagonalMove(original, target, board)
    }
}