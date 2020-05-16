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

    @Test
    fun testStraightLine() {
        val valid = piece.straightLine("A2", "A6", board)
        val unvalid = piece.straightLine("A1", "C8", board)
        val blocked = piece.straightLine("A1", "A4", board)

        assertTrue(valid)
        assertFalse(unvalid)
        assertFalse(blocked)
    }

    @Test
    fun testDiagonalMove() {
        val valid = piece.diagonalMove("A2", "E6", board)
        val unvalid = piece.diagonalMove("A1", "B5", board)
        val blocked = piece.diagonalMove("C1", "E3", board)

        assertTrue(valid)
        assertFalse(unvalid)
        assertFalse(blocked)
    }
}