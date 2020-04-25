package chess

import java.lang.IllegalArgumentException

class Board: HashMap<String, Piece>() {

    init {
        setupBoard()
    }

    fun reload() {
        clear()
        setupBoard()
    }

    fun move(piece: Piece, target: String) {
        val originalPosition: String = filterValues {
            it == piece
        }.keys.first()

        if (piece.validMove(target, this)) {
            this[target] = piece
            emptySquare(originalPosition)
        } else {
            throw IllegalArgumentException("Unvalid Move Target")
        }
    }

    private fun emptySquare(square: String) {
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
        this["A1"] = Rook("white")
        this["H1"] = Rook("white")

        this["A8"] = Rook("black")
        this["H8"] = Rook("black")
    }

    private fun placeKnights() {
        this["B1"] = Knight("white")
        this["G1"] = Knight("white")

        this["B8"] = Knight("black")
        this["G8"] = Knight("black")
    }

    private fun placeBishops() {
        this["C1"] = Bishop("white")
        this["F1"] = Bishop("white")

        this["C8"] = Bishop("black")
        this["F8"] = Bishop("black")
    }

    private fun placeRoyalty() {
        this["E1"] = King("white")
        this["E8"] = King("black")

        this["D1"] = Queen("white")
        this["D8"] = Queen("black")
    }

    private fun placePawns() {
        val blackPawnRow = getFullRow('7')
        val whitePawnRow = getFullRow('2')

        whitePawnRow.forEach { key -> this[key] = Pawn("white") }
        blackPawnRow.forEach { key -> this[key] = Pawn("black") }
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