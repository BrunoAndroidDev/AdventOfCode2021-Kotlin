class Day02 {

    // https://adventofcode.com/2021/day/1#part1
    fun part1(input: List<String>): Int {
        // Variables initialization
        var hPosition = 0
        var depth = 0

        for (item in input) {
            val parts = item.split(" ")
            with (Integer.parseInt(parts[1])) {
                when (parts[0]) {
                    "forward" -> hPosition += this
                    "up" -> depth -= this
                    "down" -> depth += this
                }
            }
        }
        return hPosition*depth
    }

    // https://adventofcode.com/2021/day/1#part2
    fun part2(input: List<String>): Int {
        // Variables initialization
        var hPosition = 0
        var depth = 0
        var aim = 0

        for (item in input) {
            val parts = item.split(" ")

            with(Integer.parseInt(parts[1])) {
                when (parts[0]) {
                    "forward" -> {
                        hPosition += this
                        depth += aim * this
                    }
                    "up" -> aim -= this
                    "down" -> aim += this
                }
            }
        }
        return hPosition*depth
    }
}