import chess.Pawn
import chess.Piece
import chess.Square
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class SquareTest {
    private val square = Square(Pair('A', 1))
    private val testPiece = Piece(square, "white")

    @Before
    fun setUp() {
    }

    @Test
    fun setOccupant() {
        square.occupant = testPiece
        assertEquals(testPiece, square.occupant)
    }

    @Test
    fun getLetter() {
        assertEquals('A', square.letter)
    }

    @Test
    fun getNumber() {
        assertEquals(1, square.number)
    }
}