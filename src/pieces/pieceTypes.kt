package chess

class King(_team: String) : Piece(_team)

class Knight(_team: String) : Piece(_team)

class Queen(_team: String) : Piece(_team) {
    override fun validMove(target: String, board: Board): Boolean {
        val original: String = board.filterValues {
            it == this
        }.keys.first()
        val straightLine = straightLine(original, target, board)
        val diagonal = diagonalMove(original, target, board)
        return (straightLine or diagonal)
    }
}

class Bishop(_team: String) : Piece(_team) {
    override fun validMove(target: String, board: Board): Boolean {
        val original: String = board.filterValues {
            it == this
        }.keys.first()

        return diagonalMove(original, target, board)
    }
}

class Rook(_team: String) : Piece(_team) {

    override fun validMove(target: String, board: Board): Boolean {
        val original: String = board.filterValues {
            it == this
        }.keys.first()

        return straightLine(original, target, board)
    }
}



