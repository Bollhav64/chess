package chess

open class Piece(_team: String) {
    private val team = _team
    val type = javaClass.toString().split(".")[1]
    val name = "$team $type"

    var moved = false

    open fun validMove(target: String, board: Board): Boolean {
        val occupant = board[target]

        return (!sameTeam(occupant!!))
    }

    fun sameTeam(otherPiece: Piece): Boolean {
        return otherPiece.team == team
    }

    fun straightLine(original: String, target: String, board: Board): Boolean {
        val sameRow = original[0] == target[0]
        val sameColumn = original[1] == target[1]
        val keys = getStraightInterval(sameRow, sameColumn, original, target)

        return ((sameRow || sameColumn)
                and pathIsClear(keys,board))
    }

    private fun pathIsClear(keys: List<String>, board: Board): Boolean {
        for (key in keys) {
            if (board[key] != NullPiece) {
                return false
            }
        }
        return true
    }

    private fun getStraightInterval(
        sameRow: Boolean, sameColumn: Boolean,
        original: String, target: String
    ): List<String> {
        val keys = mutableListOf<String>()

        if (sameRow) {
            var key: String
            for (number in (original[1]..target[1])) {
                key = "${original[0]}$number"
                keys.add(key)
            }
        } else {
            var key: String
            for (letter in (original[0]..target[0])) {
                key = "$letter${original[1]}"
                keys.add(key)
            }
        }
        with(keys) {
            remove(target)
            remove(original)
        }
        return keys.toList()
    }

    fun diagonalMove(original: String, target: String): Boolean {
        val letter = target[0] - original[0]
        val number = target[1] - original[1]
        return (letter == number)
    }
}

object NullPiece : Piece("none")

