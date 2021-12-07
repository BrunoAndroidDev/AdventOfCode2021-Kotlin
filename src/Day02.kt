class Day02 {

    // https://adventofcode.com/2021/day/1#part1
    fun part1(input: List<String>): Int {
        // Variables initialization
        var hPosition = 0
        var depth = 0

        for (item in input) {
            val parts = item.split(" ")
            when (parts[0]) {
                "forward" -> hPosition += Integer.parseInt(parts[1])
                "up" -> depth -= Integer.parseInt(parts[1])
                "down" -> depth += Integer.parseInt(parts[1])
            }
        }
        return hPosition*depth
    }

    // https://adventofcode.com/2021/day/1#part2
    fun part2(input: List<String>): Int {

        return input.size
    }
}