package chess

fun main() {
    val originalSquare = Square(Pair('A', 1))
    val enemySquare = Square(Pair('B', 2))
    val friendSquare = Square(Pair('C', 3))

    val piece = Piece(originalSquare, "white")
    val friendPiece = Piece(friendSquare, "white")
    val enemyPiece = Piece(enemySquare, "black")

    originalSquare.occupant = piece
    friendSquare.occupant = friendPiece
    enemySquare.occupant = enemyPiece

}