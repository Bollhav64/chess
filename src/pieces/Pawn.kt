package chess

class Pawn(_team: Team) : Piece(_team) {

    override fun validMove(target: String, board: Board): Boolean {
        val occupantPiece = board[target]!!
        val originalPosition: String = position(board)

        return (attackMove(originalPosition, target, occupantPiece)

                || (rowMove(originalPosition, target, board)

                && columnMove(originalPosition[1], target[1])
                ))
    }

    private fun attackMove(
        original: String,
        target: String,
        occupantPiece: Piece
    ): Boolean {

        if (target[1] == original[1]) {
            return false
        }

        return if (target[0] == original[0]+1 || target[0] == original[0]-1 &&
            (target[1] == original[1]+1)
        ) {

            (occupantPiece.type != "NullPiece" && !sameTeam(occupantPiece))
        } else {
            false
        }
    }

    private fun rowMove(
        original: String,
        target: String,
        board: Board
    ): Boolean {

        val targetOccupant = board[target]

        if (original[0] == target[0]) {
            if (super.validMove(target, board)) {
                return super.validMove(target, board)
                        && (targetOccupant == NullPiece)
            }
        }
        return false
    }

    private fun columnMove(original: Char, target: Char) = (target == original+1 || (target == original+2 && !moved))
}