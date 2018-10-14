package me.aberrantfox.etherealbot.arguments

import me.aberrantfox.etherealbot.services.chess.Point
import me.aberrantfox.kjdautils.api.dsl.CommandEvent
import me.aberrantfox.kjdautils.internal.command.ArgumentResult
import me.aberrantfox.kjdautils.internal.command.ArgumentType
import me.aberrantfox.kjdautils.internal.command.ConsumptionType

private val regex = Regex("[a-h][1-8]|[1-8][a-h]")

open class ChessCoordinate(override val name: String = "Chess Coordinate") : ArgumentType {
    companion object : ChessCoordinate()

    override val consumptionType: ConsumptionType = ConsumptionType.Single
    override val examples: ArrayList<String> = arrayListOf("a1", "b3", "b4", "c2")

    override fun convert(arg: String, args: List<String>, event: CommandEvent): ArgumentResult {
        if(arg.length > 2 ) {
            return ArgumentResult.Error("Coordinate length is too long")
        }

        if(arg.length < 2) {
            return ArgumentResult.Error("Coordinates must contain a letter and a number, length is too short.")
        }

        if(!arg.matches(regex)) {
            return ArgumentResult.Error("Coordinates must be in the format letterNumber or numberLetter, e.g. a1, b3, 1a in the range 1 - 8, a - h")
        }

        val chars = arg.toCharArray()
        val first = chars.component1()
        val second = chars.component2()

        return if(first.isDigit()) {
            ArgumentResult.Single(
                    Point(charLookup[second]!!, numberLookup[Character.getNumericValue(first)]!!)
            )
        } else {
            ArgumentResult.Single(
                    Point(charLookup[first]!!, numberLookup[Character.getNumericValue(second)]!!)
            )
        }
    }
}


private val numberLookup = mapOf(
        8 to 0,
        7 to 1,
        6 to 2,
        5 to 3,
        4 to 4,
        3 to 5,
        2 to 6,
        1 to 8
)

private val charLookup = mapOf(
        'a' to 0,
        'b' to 1,
        'c' to 2,
        'd' to 3,
        'e' to 4,
        'f' to 5,
        'g' to 6,
        'h' to 7
)

