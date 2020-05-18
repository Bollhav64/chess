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
        val pawn = board["A2"] as Pawn

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

        val pawn = board["A2"] as Pawn

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
        var rook = board["A1"] as Rook

        val blocked = rook.validMove("A5", board)

        board["A3"] = Rook("white")
        rook = board["A3"] as Rook

        val diagonal = rook.validMove("E5", board)
        val vertical = rook.validMove("A6", board)
        val horizontal = rook.validMove("H3", board)

        assertFalse(diagonal)
        assertFalse(blocked)

        assertTrue(vertical)
        assertTrue(horizontal)
    }

    @Test
    fun testBishopValidMove() {
        var bishop = board["C1"] as Bishop

        val blocked = bishop.validMove("E3", board)

        board["C5"] = Bishop("white")
        bishop = board["C5"] as Bishop

        val straight = bishop.validMove("C6", board)
        val upRight = bishop.validMove("E7", board)
        val downRight = bishop.validMove("E3", board)
        val upLeft = bishop.validMove("A7", board)
        val downLeft = bishop.validMove("A3", board)

        assertFalse(straight)
        assertFalse(blocked)

        assertTrue(upRight)
        assertTrue(upLeft)
        assertTrue(downLeft)
        assertTrue(downRight)
    }

    @Test
    fun testQueenValidMove() {
        var queen = board["D1"] as Queen

        val blockedDiagonal = queen.validMove("F3", board)
        val blockedStraight = queen.validMove("D3", board)

        board["C5"] = Queen("white")
        queen = board["C5"] as Queen

        val straight = queen.validMove("C6", board)
        val upRight = queen.validMove("E7", board)
        val downRight = queen.validMove("E3", board)
        val upLeft = queen.validMove("A7", board)
        val downLeft = queen.validMove("A3", board)

        assertFalse(blockedDiagonal)
        assertFalse(blockedStraight)

        assertTrue(straight)
        assertTrue(upRight)
        assertTrue(upLeft)
        assertTrue(downLeft)
        assertTrue(downRight)
    }
 }