package chess

import kotlin.math.absoluteValue

class Knight(_team: Team) : Piece(_team) {
    override fun validMove(target: String, board: Board): Boolean {
        val original: String = position(board)

        val letter = (original[0]-target[0]).absoluteValue
        val number = (original[1]-target[1]).absoluteValue

        return (letter == 1 && number == 2)
    }
}