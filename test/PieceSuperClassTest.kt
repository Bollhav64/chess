import chess.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PieceSuperClassTest {

    private val session = Session()
    private val board = session.board
    private val piece = Piece(Team.White)

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
        val pawn = Pawn(Team.White)
        assertEquals("White Pawn", pawn.name)
    }

    @Test
    fun testSameTeam() {
        val friendPiece = Piece(Team.White)
        val enemyPiece = Piece(Team.Black)

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