package chess

class Board {

    internal val matrix = generateMatrix()

    fun placePiecesForStart() {
        placePawns()

        matrix.
//        placeTowers()
//        placeKnights()
//        placeBishops()
//        placeRoyalty()
    }

    private fun placeRoyalty() {
        matrix[Pair('E', '1')] = King("white")
        matrix[Pair('D', '8')] = King("black")

        matrix[Pair('D', '1')] = Queen("white")
        matrix[Pair('E', '8')] = Queen("black")
    }

    private fun placeBishops() {
        matrix[Pair('C', '1')] = Bishop("white")
        matrix[Pair('F', '1')] = Bishop("white")

        matrix[Pair('C', '8')] = Bishop("black")
        matrix[Pair('F', '8')] = Bishop("black")
    }

    private fun placeKnights() {
        matrix[Pair('B', '1')] = Knight("white")
        matrix[Pair('G', '1')] = Knight("white")

        matrix[Pair('B', '8')] = Knight("white")
        matrix[Pair('G', '8')] = Knight("white")
    }

    private fun placeTowers() {
        matrix[Pair('1', 'A')] = Tower("white")
        matrix[Pair('1', 'H')] = Tower("white")
        matrix[Pair('8', 'A')] = Tower("black")
        matrix[Pair('8', 'H')] = Tower("black")
    }

    private fun generateMatrix(): MutableMap<Pair<Char, Char>, Piece> {

        val map = emptyMap<Pair<Char, Char>, Piece>().toMutableMap()

        for (row in ('A'..'H')) {
            for (column in '1'..'8') {
                val coordinate = Pair(row, column)
                map[coordinate] = NullPiece
            }
        }
        return map
    }

    private fun placePawns() {
        val blackPawnRow = getFullRow('7')
        val whitePawnRow = getFullRow('2')

        whitePawnRow.forEach { key -> matrix.replace(key, Pawn("white")) }
        blackPawnRow.forEach { key -> matrix.replace(key, Pawn("black")) }
    }

    private fun getFullRow(row: Char): Set<Pair<Char, Char>> {
        return matrix.filter { (key, _) ->
            key.second == row
        }.keys
    }
}