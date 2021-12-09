import kotlin.math.abs

fun List<Int>.median(): Int =
    if (this.size % 2 == 0)
        (this[this.size / 2] + this[(this.size - 1) / 2]) / 2
    else
        this[this.size / 2]

class Day07 {

    // https://adventofcode.com/2021/day/7#part1
    fun part1(input: List<String>): Int {

        // convert input
        val init = input[0].split(",").map { it.toInt() }

        // get median of the sampling
        val median = init.sorted().median()

        // return the fuel consumption
        return init.sumOf { abs(it-median) }
    }

    // https://adventofcode.com/2021/day/7#part2
    fun part2(input: List<String>): Int {

        return input.size
    }

}