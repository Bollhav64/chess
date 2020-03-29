import chess.Board
import chess.NullPiece
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class BoardTest {

    private lateinit var board: Board

    @Before
    fun setup() {
        board = Board.newGameBoard()
    }

    @After
    fun teardown() {
        board = Board()
    }

    @Test
    fun testGenerateMatrix() {
        assertEquals(64, board.matrix.size)
    }

    @Test
    fun testNewGameBoard() {
        assertEquals("class chess.Board", board.javaClass.toString())
    }

    @Test
    fun testNumberOfPlacedPieces() {
        val placedPieces = board.matrix.filter {
            it.value != NullPiece
        }
        assertEquals(32, placedPieces.size)
    }

    @Test
    fun testPawnPlacement() {
        val blackPawns = board.matrix.filterValues {
            it.name == "black Pawn"
        }

        val whitePawns = board.matrix.filter {
            it.value.name == "white Pawn"
        }

        val expectedPositions = arrayOf(
            Pair('A', '2'), Pair('A', '7'),
            Pair('B', '2'), Pair('B', '7'),
            Pair('C', '2'), Pair('C', '7'),
            Pair('D', '2'), Pair('D', '7'),
            Pair('E', '2'), Pair('E', '7'),
            Pair('F', '2'), Pair('F', '7'),
            Pair('G', '2'), Pair('G', '7'),
            Pair('H', '2'), Pair('H', '7')
        )

        assertEquals(8, whitePawns.size)
        assertEquals(8, blackPawns.size)

        for (pawn in whitePawns) {
            assertTrue((pawn.key in expectedPositions))
        }
    }

    @Test
    fun testRoyaltyPlacement() {
        val queens = board.matrix.filterValues { it.name.contains("Queen") }
        val kings = board.matrix.filterValues { it.name.contains("King") }

        assertTrue(kings[Pair('E', '8')]?.name == "black King")
        assertTrue(kings[Pair('E', '1')]?.name == "white King")

        assertTrue(queens[Pair('D', '8')]?.name == "black Queen")
        assertTrue(queens[Pair('D', '1')]?.name == "white Queen")
    }

    @Test
    fun testNumberOfRoyalty() {
        val queens = board.matrix.filterValues { it.name.contains("Queen") }
        val kings = board.matrix.filterValues { it.name.contains("King") }

        assertEquals(2, kings.size)
        assertEquals(2, queens.size)
    }

    @Test
    fun testNumberOfTowers() {
        val towers = board.matrix.filterValues { it.name.contains("Tower") }

        assertEquals(4, towers.size)

    }

    @Test
    fun testTowerPlacement() {
        val towers = board.matrix.filterValues { it.name.contains("Tower") }

        assertTrue(towers[Pair('A', '8')]?.name == "black Tower")
        assertTrue(towers[Pair('H', '8')]?.name == "black Tower")

        assertTrue(towers[Pair('A', '1')]?.name == "white Tower")
        assertTrue(towers[Pair('H', '1')]?.name == "white Tower")
    }

    @Test
    fun testNumberOfKnights() {
        val knights = board.matrix.filterValues { it.name.contains("Knight") }

        assertEquals(4, knights.size)
    }

    @Test
    fun testKnightsPlacement() {
        val knights = board.matrix.filterValues { it.name.contains("Knight") }

        assertTrue(knights[Pair('B', '8')]?.name == "black Knight")
        assertTrue(knights[Pair('G', '8')]?.name == "black Knight")

        assertTrue(knights[Pair('B', '1')]?.name == "white Knight")
        assertTrue(knights[Pair('G', '1')]?.name == "white Knight")
    }

    @Test
    fun testNumberOfBishops() {
        val bishops = board.matrix.filterValues { it.name.contains("Bishop") }

        assertEquals(4, bishops.size)
    }

    @Test
    fun testBishopsPlacement() {
        val knights = board.matrix.filterValues { it.name.contains("Bishop") }

        assertTrue(knights[Pair('C', '8')]?.name == "black Bishop")
        assertTrue(knights[Pair('F', '8')]?.name == "black Bishop")

        assertTrue(knights[Pair('C', '1')]?.name == "white Bishop")
        assertTrue(knights[Pair('F', '1')]?.name == "white Bishop")
    }

    @Test
    fun testMove() {
        val piece = board.matrix[Pair('A', '2')]

        board.move(piece!!, Pair('A', '4'))
        assertEquals(piece, board.matrix[Pair('A', '4')])
        board.move(piece, Pair('A', '2'))

    }
}

