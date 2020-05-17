import chess.*

import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PiecesTypesTest {

    private val board = Board.newGameBoard()

    @Before
    fun setup() {
        board.reload()
    }

    @Test
    fun testPawnValidMove() {
        val pawn = board["A2"]!!

        val validFirst = pawn.validMove("A4", board)
        val unvalidFirst = pawn.validMove("A5", board)
        val validMove = pawn.validMove("A3", board)
        val teleportation = pawn.validMove("E5", board)

        assertTrue(validMove)
        assertTrue(validFirst)
        assertFalse(unvalidFirst)
        assertFalse(teleportation)
    }

    @Test
    fun testPawnValidAttack() {

        val pawn = board["A2"]!!

        board.replace("B3", Pawn("black"))
        board.replace("A3", Pawn("black"))

        val validAttackMove = pawn.validMove("B3", board)
        val unvalidAttackMove = pawn.validMove("A3", board)
        val straightAttack = pawn.validMove("A3", board)

        assertTrue(validAttackMove)
        assertFalse(unvalidAttackMove)
        assertFalse(straightAttack)
    }

    @Test
    fun testRookValidMove() {
        var rook = board["A1"]!!

        val diagonal = rook.validMove("E5", board)
        val blocked = rook.validMove("A5", board)

        board.replace("A3", Rook("white"))
        rook = board["A3"]!!
        val vertical = rook.validMove("A6", board)
        val horizontal = rook.validMove("H3", board)

        assertFalse(diagonal)
        assertFalse(blocked)

        assertTrue(vertical)
        assertTrue(horizontal)
    }
}