package chess

class Board {

        val matrix: MutableMap<Pair<Char, Char>, Piece> =
            emptyMap<Pair<Char, Char>, Piece>().toMutableMap()

    init {
        populateMatrix()
    }

    private fun populateMatrix() {
        for (row in 'A'..'H') {
            for (column in '1'..'8') {
                val coordinate = Pair(row, column)
                matrix[coordinate] = NullPiece
            }
        }
        placePiecesForStart()
    }

    fun placePiecesForStart() {
        placePawns()
        placeRooks()
        placeKnights()
        placeBishops()
        placeRoyalty()
    }

    private fun placeRooks() {
        matrix[Pair('A', '1')] = Rook("white")
        matrix[Pair('H', '1')] = Rook("white")

        matrix[Pair('A', '8')] = Rook("black")
        matrix[Pair('H', '8')] = Rook("black")
    }

    private fun placeKnights() {
        matrix[Pair('B', '1')] = Knight("white")
        matrix[Pair('G', '1')] = Knight("white")

        matrix[Pair('B', '8')] = Knight("black")
        matrix[Pair('G', '8')] = Knight("black")
    }

    private fun placeBishops() {
        matrix[Pair('C', '1')] = Bishop("white")
        matrix[Pair('F', '1')] = Bishop("white")

        matrix[Pair('C', '8')] = Bishop("black")
        matrix[Pair('F', '8')] = Bishop("black")
    }

    private fun placeRoyalty() {
        matrix[Pair('E', '1')] = King("white")
        matrix[Pair('E', '8')] = King("black")

        matrix[Pair('D', '1')] = Queen("white")
        matrix[Pair('D', '8')] = Queen("black")
    }

    private fun placePawns() {
        val blackPawnRow = getFullRow('7')
        val whitePawnRow = getFullRow('2')

        whitePawnRow.forEach { key -> matrix[key] = Pawn("white") }
        blackPawnRow.forEach { key -> matrix[key] = Pawn("black") }
    }

    private fun getFullRow(row: Char): Set<Pair<Char, Char>> {
        return matrix.filter { (key, _) ->
            key.second == row
        }.keys
    }

    fun move(piece: Piece, target: Pair<Char, Char>) {
        val originalPosition: Pair<Char, Char> = matrix.filterValues {
            it == piece
        }.keys.first()

        if (piece.validMove(target, matrix)) {
            matrix[target] = piece
            matrix[originalPosition] = NullPiece
        }
    }

    fun reload() {
        matrix.clear()
        populateMatrix()
    }

    companion object {

        private val board = Board()

        fun newGameBoard(): Board {
            board.placePiecesForStart()
            return board
        }
    }
}