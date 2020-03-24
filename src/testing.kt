package chess

fun main() {
    val board = Board()
    println(board.matrix)
    board.placePiecesForStart()
    print(board.matrix.filter { (key, _) ->
        key.second == '1'
    }.values)

}