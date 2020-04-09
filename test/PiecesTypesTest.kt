import chess.Board
import chess.Pawn
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PiecesTypesTest {

    lateinit var board: Board

    @Before
    fun setup() {
        board = Board.newGameBoard()
    }

    @After
    fun tearDown() {
        board.reload()
    }

    @Test
    fun testPawnValidMove() {
        val matrix = board.matrix

        val pawn = matrix[Pair('A', '2')]!!

        val validFirst = pawn.validMove(Pair('A', '4'), matrix)
        val unvalidFirst = pawn.validMove(Pair('A', '5'), matrix)
        val validMove = pawn.validMove(Pair('A', '3'), matrix)
        val teleportation = pawn.validMove(Pair('E', '5'), matrix)

        assertTrue(validMove)
        assertTrue(validFirst)
        assertFalse(unvalidFirst)
        assertFalse(teleportation)
    }

    @Test
    fun testPawnValidAttack() {
        val matrix = board.matrix

        val pawn = matrix[Pair('A', '2')]!!

        matrix.replace(Pair('B', '3'), Pawn("black"))
        matrix.replace(Pair('A', '3'), Pawn("black"))

        val validAttackMove = pawn.validMove(Pair('B', '3'), matrix)
        val unvalidAttackMove = pawn.validMove(Pair('A', '3'), matrix)
        val straightAttack = pawn.validMove(Pair('A', '3'), matrix)

        assertTrue(validAttackMove)
        assertFalse(unvalidAttackMove)
        assertFalse(straightAttack)
    }
}