import chess.Board
import chess.NullPiece
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertTrue

class BoardTest {

    val board = Board.newGameBoard()

    @Test
    fun testNumberOfPlacedPieces() {
        val placedPieces = board.matrix.filter {
            it.value != NullPiece
        }
        assertEquals(32, placedPieces.size)
    }

    @Test
    fun testPawnPlacement() {
        val blackPawns = board.matrix.filter {
            it.value.name == "black Pawn"
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
        
        assertEquals(8 , whitePawns.size)
        assertEquals(8 , blackPawns.size)

        for ( pawn in whitePawns ) {
            assertTrue( (pawn.key in expectedPositions) )
        }
    }

}
