package chess

class Session {

    val board: Board = Board.newGameBoard()
    var turn: Team = Team.White

    fun move(originalPosition: String, target: String) {
        val piece = board[originalPosition]!!

        if (myTurn(piece)) {
            if (piece.validMove(target, board)) {
                board[target] = piece
                board.emptySquare(originalPosition)

                if (piece.type == "Pawn") transformPawnIfBackline(piece.team, target)
                itterateTurn()
            } else {
                throw UnvalidMoveTargetException("Piece ${piece.name} cannot move there")
            }
        } else {
            throw UnvalidMoveOriginException("please move $turn piece")
        }
    }

    private fun itterateTurn() {
        turn =
            if (turn == Team.White) Team.Black
            else Team.Black
    }

    private fun myTurn(piece: Piece): Boolean {
        return (piece.team == turn)

    }

    private fun transformPawnIfBackline(team: Team, square: String) {
        val row = square[1]
        val whiteTransform = (team == Team.White && row == '8')
        val blackTransform = (team == Team.Black && row == '1')

        if (blackTransform || whiteTransform) board[square] = Queen(team)
    }

    class UnvalidMoveTargetException(message: String): Exception(message)
    class UnvalidMoveOriginException(message: String): Exception(message)


}