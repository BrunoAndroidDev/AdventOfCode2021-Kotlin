import kotlin.math.abs
import kotlin.math.min
import kotlin.math.roundToInt

class Day08 {

    // https://adventofcode.com/2021/day/8#part1
    fun part1(input: List<String>): Int {

        val uniqueSizes = arrayListOf(2, 3, 4, 7)

        // prepare counting
        val total = mutableMapOf(
            0 to 0, 1 to 0, 2 to 0, 3 to 0, 4 to 0,
            5 to 0, 6 to 0, 7 to 0
        )
        // split entry and compute each right parts
        input.forEach {entry ->
            val map = entry.split("|")[1].split(" ").map { it.length }.groupingBy { it }.eachCount()
            map.forEach {
                total.replace(it.key, total[it.key]!!.plus(it.value))
            }
        }
        return total.filter { uniqueSizes.contains(it.key)  }.values.sum()
    }

    // https://adventofcode.com/2021/day/8#part2
    fun part2(input: List<String>): Int {

        // return
        return 0
    }

}