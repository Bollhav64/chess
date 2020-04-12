package chess

open class Piece(_team: String) {
    private val team = _team
    val type = javaClass.toString().split(".")[1]
    val name = "$team $type"

    var moved = false

    open fun validMove(target: Pair<Char, Char>, board: MutableMap<Pair<Char, Char>, Piece>): Boolean {
        val occupant = board[target]

        return (!sameTeam(occupant!!))
    }

    fun sameTeam(otherPiece: Piece): Boolean {
        return otherPiece.team == team
    }

    fun straightLine(original: Pair<Char, Char>, target: Pair<Char, Char>): Boolean {
        return (original.first == target.first
                || original.second == target.second)
    }
}

object NullPiece : Piece("none")

