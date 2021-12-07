class Day01 {

    // https://adventofcode.com/2021/day/1#part1
    fun part1(input: List<String>): Int {
        // Variables initialization
        var counter = 0
        val intInput = input.map { it.toInt() }

        if (input.isNotEmpty()) {
            var previous = intInput[0]
            for (item in intInput) {
                if (item > previous) {
                    counter++
                }
                previous = item
            }
        }
        return counter
    }

    // https://adventofcode.com/2021/day/1#part2
    fun part2(input: List<String>): Int {
        // Variables initialization
        var counter = 0
        val intInput = input.map { it.toInt() }

        if (input.isNotEmpty()) {
            var previous = intInput[0] + intInput[1] + intInput[2]
            for (i in 1..intInput.size-3) {
                val sum = intInput[i] + intInput[i+1] + intInput[i+2]
                if (sum > previous) {
                    counter++
                }
                previous = sum
            }
        }
        return counter
    }
}