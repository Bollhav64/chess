package chess

open class Piece(_position: Square, _team: String) {

    val team = _team
    var position = _position
    var moved = false

    open fun move(target: Square) {

        if (validMove(target)) {
            val originalPosition = position
            position = target
            target.occupant = this
            moved = true
            originalPosition.nullPiece()
        }
    }

    open fun validMove(target: Square): Boolean {
        return (!sameTeam(target.occupant))
    }

    fun sameTeam(otherPiece: Piece): Boolean {
        return otherPiece.team == team
    }

}

