package chess

class Pawn(_team: String) : Piece(_team) {

    override fun validMove(target: Pair<Char, Char>, board: MutableMap<Pair<Char, Char>, Piece>): Boolean {
        val occupantPiece = board[target]!!
        val originalPosition: Pair<Char, Char> = board.filterValues {
            it == this
        }.keys.first()

        return (attackMove(originalPosition, target, occupantPiece)
                
                || (rowMove(originalPosition, target, board)

                && columnMove(originalPosition.second, target.second)
                ))
    }

    private fun attackMove(
        original: Pair<Char, Char>,
        target: Pair<Char, Char>,
        occupantPiece: Piece
    ): Boolean {

        if (target.second == original.second) {return false}

        return if (target.first == original.first+1 || target.first == original.first-1 &&
            (target.second == original.second+1)) {

            (occupantPiece.type != "NullPiece" && !sameTeam(occupantPiece))
        } else {
            false
        }
    }

    private fun rowMove(
        original: Pair<Char, Char>,
        target: Pair<Char, Char>,
        board: MutableMap<Pair<Char, Char>, Piece>
    ): Boolean {

        val targetOccupant = board[target]

        if (original.first == target.first) {
            if (super.validMove(target, board)) {
                return super.validMove(target, board)
                        && (targetOccupant == NullPiece)
            }
        }
        return false
    }

    private fun columnMove(original: Char, target: Char) = (target == original+1 || (target == original+2 && !moved))

}

class King(_team: String) : Piece(_team)

class Queen(_team: String) : Piece(_team)

class Knight(_team: String) : Piece(_team)

class Bishop(_team: String) : Piece(_team)

class Tower(_team: String) : Piece(_team)



