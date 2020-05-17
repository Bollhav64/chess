import chess.Board
import chess.Pawn
import chess.Piece
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PieceSuperClassTest {

    private val board = Board.newGameBoard()
    private val piece = Piece("white")

    @Before
    fun setup() {
        board.reload()
    }

    @Test
    fun testGetType() {
        assertEquals("Piece", piece.type)
    }

    @Test
    fun testGetName() {
        val pawn = Pawn("white")
        assertEquals("white Pawn", pawn.name)
    }

    @Test
    fun testSameTeam() {
        val friendPiece = Piece("white")
        val enemyPiece = Piece("black")

        assertFalse(piece.sameTeam(enemyPiece))
        assertTrue(piece.sameTeam(friendPiece))
    }


    @Test
    fun testValidMove() {
        val valid = piece.validMove("A4", board)
        val unvalid = piece.validMove("B2", board)

        assertTrue(valid)
        assertFalse(unvalid)
    }
}