package chess

class Board : HashMap<String, Piece>() {

    fun reload() {
        clear()
        setupBoard()
    }

    fun emptySquare(square: String) {
        this[square] = NullPiece
    }

    private fun setupBoard() {
        for (row in 'A'..'H') {
            for (column in '1'..'8') {
                val coordinate = "${row}${column}"
                emptySquare(coordinate)
            }
        }
        placePiecesForStart()
    }

    private fun placePiecesForStart() {
        placePawns()
        placeRooks()
        placeKnights()
        placeBishops()
        placeRoyalty()
    }

    private fun placeRooks() {
        this["A1"] = Rook(Team.White)
        this["H1"] = Rook(Team.White)

        this["A8"] = Rook(Team.Black)
        this["H8"] = Rook(Team.Black)
    }

    private fun placeKnights() {
        this["B1"] = Knight(Team.White)
        this["G1"] = Knight(Team.White)

        this["B8"] = Knight(Team.Black)
        this["G8"] = Knight(Team.Black)
    }

    private fun placeBishops() {
        this["C1"] = Bishop(Team.White)
        this["F1"] = Bishop(Team.White)

        this["C8"] = Bishop(Team.Black)
        this["F8"] = Bishop(Team.Black)
    }

    private fun placeRoyalty() {
        this["E1"] = King(Team.White)
        this["E8"] = King(Team.Black)

        this["D1"] = Queen(Team.White)
        this["D8"] = Queen(Team.Black)
    }

    private fun placePawns() {
        val blackPawnRow = getFullRow('7')
        val whitePawnRow = getFullRow('2')

        whitePawnRow.forEach { key -> this[key] = Pawn(Team.White) }
        blackPawnRow.forEach { key -> this[key] = Pawn(Team.Black) }
    }

    private fun getFullRow(row: Char): Set<String> {
        return this.filter { (key, _) ->
            key[1] == row
        }.keys
    }

    companion object {

        private val board = Board()

        fun newGameBoard(): Board {
            board.placePiecesForStart()
            return board
        }
    }
}