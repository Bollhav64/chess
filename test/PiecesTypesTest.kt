import chess.*

import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PiecesTypesTest {

    private val session = Session()
    private val board = session.board

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

        board.replace("B3", Pawn(Team.Black))
        board.replace("A3", Pawn(Team.Black))

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

        board["A3"] = Rook(Team.White)
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

        board["C5"] = Bishop(Team.White)
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

        board["C5"] = Queen(Team.White)
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

    @Test
    fun testKnightValidMove() {
        board["E2"] = NullPiece
        board["C2"] = NullPiece
        board["D4"] = Knight(Team.White)

        val knight = board["D4"] as Knight

        val upright = knight.validMove("E6", board)
        val upleft = knight.validMove("C6", board)
        val downright = knight.validMove("E2", board)
        val downleft = knight.validMove("C2", board)

        val straight = knight.validMove("D6", board)
        val diagonal = knight.validMove("F6", board)
        val teleport = knight.validMove("H2", board)

        assertTrue(upleft)
        assertTrue(upright)
        assertTrue(downleft)
        assertTrue(downright)

        assertFalse(straight)
        assertFalse(diagonal)
        assertFalse(teleport)
    }

    @Test
    fun testKingValidMove() {
        var king = board["E1"] as King

        val blocked = king.validMove("D1", board)

        board["E4"] = King(Team.White)
        king = board["E4"] as King

        val straight = king.validMove("E5", board)
        val diagonal = king.validMove("D3", board)

        val longStep = king.validMove("E6", board)

        assertTrue(straight)
        assertTrue(diagonal)

        assertFalse(blocked)
        assertFalse(longStep)
    }
}