import chess.Board
import chess.Pawn
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PiecesTypesTest {

    private val matrix = Board.newGameBoard().matrix

    @Test
    fun testPawnValidMove() {
        val pawn = matrix[Pair('A', '2')]!!
        val pawn2 = matrix[Pair('H', '2')]!!

//        val attackWithoutEnemy = pawn.validMove(Pair('B', '3'), matrix)

        matrix.replace(Pair('B', '3'), Pawn("black"))
        matrix.replace(Pair('H', '3'), Pawn("black"))

//        val attackMove = pawn.validMove(Pair('B', '3'), matrix)
//        val validFirst = pawn.validMove(Pair('A', '4'), matrix)
//        val unvalidFirst = pawn.validMove(Pair('A', '5'), matrix)

        matrix.replace(Pair('A', '3'), Pawn("black"))

        val straightAttack = pawn2.validMove(Pair('H', '3'), matrix)

//        assertTrue(attackMove)
//        assertTrue(validFirst)
//        assertFalse(unvalidFirst)
//        assertFalse(attackWithoutEnemy)
        assertFalse(straightAttack)

    }

    @Test
    fun testPawnValidAttack() {
        val pawn = matrix[Pair('A', '2')]!!

        matrix.replace(Pair('B', '3'), Pawn("black"))

        val validAttackMove = pawn.validMove(Pair('B', '3'), matrix)
        val unvalidAttackMove = pawn.validMove(Pair('A', '3'), matrix)

//        assertTrue(validAttackMove)
//        assertFalse(unvalidAttackMove)
    }

}