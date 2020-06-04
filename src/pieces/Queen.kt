package chess

class Queen(_team: Team) : Piece(_team) {
    override fun validMove(target: String, board: Board): Boolean {
        val original: String = position(board)

        val straightLine = straightLine(original, target, board)
        val diagonal = diagonalMove(original, target, board)
        return (straightLine or diagonal)
    }
}