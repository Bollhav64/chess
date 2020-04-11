import chess.Board
import chess.Pawn
import chess.Piece
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertFails
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PieceSuperClassTest {

    private val board = Board.newGameBoard()
    private val matrix = board.matrix
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
        val piece = Piece("white")
        val valid = piece.validMove(Pair('A', '4'), matrix)
        val unvalid = piece.validMove(Pair('B', '2'), matrix)


        assertTrue(valid)
        assertFalse(unvalid)
    }
}