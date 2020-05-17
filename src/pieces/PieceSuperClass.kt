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

    protected fun straightLine(original: String, target: String, board: Board): Boolean {
        val sameRow = original[0] == target[0]
        val sameColumn = original[1] == target[1]
        val keys = getStraightInterval(sameRow, original, target)

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
        sameRow: Boolean,
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

    protected fun diagonalMove(original: String, target: String, board: Board): Boolean {
        val letters = target[0] - original[0]
        val numbers = target[1] - original[1]
        val diagonal = letters == numbers

        if (diagonal) {

            val squares = when {
                (original[0] < target[0]) && (original[1] < target[1]) -> upRight(original, target)

                (original[0] > target[0]) && (original[1] < target[1]) -> upLeft(original, target)

                (original[0] < target[0]) && (original[1] > target[1]) -> downRight(original, target)

                (original[0] > target[0]) && (original[1] > target[1]) -> downLeft(original, target)

                else -> listOf()
            }
            return pathIsClear(squares, board)
        }

        return false
    }

    private fun upRight(original: String, target: String): List<String> {
        val keys = mutableListOf<String>()
        for ((index, _) in (original[0]..target[0]).withIndex()) {
            val key = "${original[0]+index}${original[1]+index}"
            keys.add(key)
        }
        with(keys) {
            removeFirst()
            removeLast()
        }
        return keys.toList()
    }

    private fun upLeft(original: String, target: String): List<String> {
        val keys = mutableListOf<String>()
        for ((index, _) in (original[0]..target[0]).withIndex()) {
            val key = "${original[0]+index}${original[1]-index}"
            keys.add(key)
        }
        with(keys) {
            removeFirst()
            removeLast()
        }
        return keys.toList()
    }

    private fun downRight(original: String, target: String): List<String> {
        val keys = mutableListOf<String>()
        for ((index, _) in (original[0]..target[0]).withIndex()) {
            val key = "${original[0]-index}${original[1]+index}"
            keys.add(key)
        }
        with(keys) {
            removeFirst()
            removeLast()
        }
        return keys.toList()
    }

    private fun downLeft(original: String, target: String): List<String> {
        val keys = mutableListOf<String>()
        for ((index, _) in (original[0]..target[0]).withIndex()) {
            val key = "${original[0]-index}${original[1]-index}"
            keys.add(key)
        }
        with(keys) {
            removeFirst()
            removeLast()
        }
        return keys.toList()
    }

}

object NullPiece : Piece("none")
