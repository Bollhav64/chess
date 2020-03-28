import chess.Pawn
import chess.Piece
import org.junit.Assert.assertEquals
import org.junit.Test

class PieceTest {

    @Test
    fun testGetType() {
        val piece = Piece("white")
        assertEquals("Piece", piece.type)
    }

    @Test
    fun testGetName() {
        val pawn = Pawn("white")
        assertEquals("white Pawn", pawn.name)
    }
}