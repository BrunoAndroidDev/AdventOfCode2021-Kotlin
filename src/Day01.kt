class Day01 {

    fun part1(input: List<String>): Int {
        // Variables initialization
        var counter = 0

        if (input.isNotEmpty()) {
            var value = Integer.valueOf(input[0])
            var previous = value
            for (item in input) {
                value = Integer.valueOf(item)
                if (value > previous) {
                    counter++
                }
                previous = value
            }
        }
        return counter
    }

    fun part2(input: List<String>): Int {
        return input.size
    }
}