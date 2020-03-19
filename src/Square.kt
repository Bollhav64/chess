package chess

class Square(_coordinates: Pair<Char, Int>) {
    var occupant = Piece(this, "none")
    val letter = _coordinates.first
    val number = _coordinates.second

    fun nullPiece() {
        occupant = Piece(Square(Pair('N', 0)), "none")
    }

}
