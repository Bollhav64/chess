package chess

class Pawn(_position: Square, _team: String) : Piece(_position, _team) {
    var coordinates = _position


//    override fun validMove(target: Square): Boolean {
//        if (target.number == coordinates.number + 1)
//        return true
//    }

}