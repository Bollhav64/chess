package chess

import kotlin.math.absoluteValue

class King(_team: String) : Piece(_team) {
    override fun validMove(target: String, board: Board): Boolean {
        val original: String = board.filterValues {
            it == this
        }.keys.first()

        val letter = (original[0] - target[0]).absoluteValue
        val number = (original[1] - target[1]).absoluteValue

        if (letter > 1 || number > 1) return false

        return super.validMove(target, board)
    }
}

class Knight(_team: String) : Piece(_team) {
    override fun validMove(target: String, board: Board): Boolean {
        val original: String = board.filterValues {
            it == this
        }.keys.first()

        val letter = (original[0] - target[0]).absoluteValue
        val number = (original[1] - target[1]).absoluteValue
        
        return (letter == 1 && number == 2)
    }
}

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



