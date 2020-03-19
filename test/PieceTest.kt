import chess.Piece
import chess.Square
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class PieceTest {
    private val originalSquare = Square(Pair('A', 1))
    private val enemySquare = Square(Pair('B', 2))
    private val friendSquare = Square(Pair('C', 3))

    private val piece = Piece(originalSquare, "white")
    private val friendPiece = Piece(friendSquare, "white")
    private val enemyPiece = Piece(enemySquare, "black")

    @Before
    fun setUp() {
        originalSquare.occupant = piece
        friendSquare.occupant = friendPiece
        enemySquare.occupant = enemyPiece
    }

    @Test
    fun getTeam() {
        assertEquals("white", piece.team)
    }

    @Test
    fun indetifyEnemy() {
        assertFalse(piece.sameTeam(enemyPiece))
    }

    @Test
    fun identifyFriend() {
        assertTrue(piece.sameTeam(friendPiece))
    }

    @Test
    fun moveToValidSquare() {
        val targetSquare = Square(Pair('A', 2))
        piece.move(targetSquare)
        assertEquals(piece.position, targetSquare)
    }

    @Test
    fun moveToOccupiedSquare() {
        piece.position = originalSquare
        piece.move(friendSquare)
        assertEquals(originalSquare, piece.position)
    }

    @Test
    fun moveMakesOriginalSquareEmpty() {

    }

    @Test
    fun killOponent() {
        piece
    }

    @Test
    fun validMove() {
    }

}