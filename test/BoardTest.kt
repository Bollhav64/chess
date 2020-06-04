import chess.*

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class BoardTest {

    private val board = Board.newGameBoard()

    @Before
    fun setup() {
        board.reload()
    }

    @Test
    fun testGenerateMatrix() {
        assertEquals(64, board.size)
    }

    @Test
    fun testNewGameBoard() {
        assertEquals("class chess.Board", board.javaClass.toString())
    }

    @Test
    fun testNumberOfPlacedPieces() {
        val placedPieces = board.filter {
            it.value != NullPiece
        }
        assertEquals(32, placedPieces.size)
    }

    @Test
    fun testPawnPlacement() {
        val blackPawns = board.filterValues {
            it.name == "Black Pawn"
        }

        val whitePawns = board.filter {
            it.value.name == "White Pawn"
        }

        val expectedPositions = arrayOf(
            "A2", "A7",
            "B2", "B7",
            "C2", "C7",
            "D2", "D7",
            "E2", "E7",
            "F2", "F7",
            "G2", "G7",
            "H2", "H7"
        )

        assertEquals(8, whitePawns.size)
        assertEquals(8, blackPawns.size)

        for (pawn in whitePawns) {
            assertTrue((pawn.key in expectedPositions))
        }
    }

    @Test
    fun testRoyaltyPlacement() {
        val queens = board.filterValues { it.name.contains("Queen") }
        val kings = board.filterValues { it.name.contains("King") }

        assertTrue(kings["E8"]?.name == "Black King")
        assertTrue(kings["E1"]?.name == "White King")

        assertTrue(queens["D8"]?.name == "Black Queen")
        assertTrue(queens["D1"]?.name == "White Queen")
    }

    @Test
    fun testNumberOfRoyalty() {
        val queens = board.filterValues { it.name.contains("Queen") }
        val kings = board.filterValues { it.name.contains("King") }

        assertEquals(2, kings.size)
        assertEquals(2, queens.size)
    }

    @Test
    fun testNumberOfRooks() {
        val rooks = board.filterValues { it.name.contains("Rook") }

        assertEquals(4, rooks.size)
    }

    @Test
    fun testRookPlacement() {
        val rooks = board.filterValues { it.name.contains("Rook") }

        assertTrue(rooks["A8"]?.name == "Black Rook")
        assertTrue(rooks["H8"]?.name == "Black Rook")

        assertTrue(rooks["A1"]?.name == "White Rook")
        assertTrue(rooks["H1"]?.name == "White Rook")
    }

    @Test
    fun testNumberOfKnights() {
        val knights = board.filterValues { it.name.contains("Knight") }

        assertEquals(4, knights.size)
    }

    @Test
    fun testKnightsPlacement() {
        val knights = board.filterValues { it.name.contains("Knight") }

        assertTrue(knights["B8"]?.name == "Black Knight")
        assertTrue(knights["G8"]?.name == "Black Knight")

        assertTrue(knights["B1"]?.name == "White Knight")
        assertTrue(knights["G1"]?.name == "White Knight")
    }

    @Test
    fun testNumberOfBishops() {
        val bishops = board.filterValues { it.name.contains("Bishop") }

        assertEquals(4, bishops.size)
    }

    @Test
    fun testBishopsPlacement() {
        val knights = board.filterValues { it.name.contains("Bishop") }

        assertTrue(knights["C8"]?.name == "Black Bishop")
        assertTrue(knights["F8"]?.name == "Black Bishop")

        assertTrue(knights["C1"]?.name == "White Bishop")
        assertTrue(knights["F1"]?.name == "White Bishop")
    }

    @Test
    fun testMove() {
        val piece = board["A2"]

        board.move(piece!!, "A4")
        assertEquals(piece, board["A4"])
        assertEquals(NullPiece, board["A2"])
    }

    @Test
    fun testUnvalidMoveException() {
        val piece = board["A2"]!!
        
        assertFailsWith<IllegalArgumentException> {
            board.move(piece, "A1")
        }
    }
}

