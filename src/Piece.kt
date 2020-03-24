package chess

open class Piece(_team: String) {

    val team = _team
    private var moved = false

    fun sameTeam(otherPiece: Piece): Boolean {
        return otherPiece.team == team
    }

}

object NullPiece: Piece("none")

