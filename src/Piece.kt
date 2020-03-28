package chess

import java.util.Objects.toString
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass

open class Piece(_team: String) {

    val team = _team
    val type = javaClass.toString().split(".")[1]
    val name = "$team $type"


    private var moved = false

    fun sameTeam(otherPiece: Piece): Boolean {
        return otherPiece.team == team
    }

}

object NullPiece: Piece("none")

