class Day10 {

    private val openChars = "([{<"
    private val closeChars = mutableMapOf(
        ')' to Pair('(',3),
        ']' to Pair('[',57),
        '}' to Pair('{',1197),
        '>' to Pair('<',25137)
    )

    // https://adventofcode.com/2021/day/10#part1
    fun part1(input: List<String>): Int {

        var score = 0

        input.forEach { line ->
            val chunks = StringBuilder()
            for (char in line) {
                if (openChars.contains(char)) {
                    chunks.append(char)
                } else {
                    if (chunks.last() == closeChars[char]!!.first) {
                        chunks.deleteCharAt(chunks.length-1)
                    } else {
                        score += closeChars[char]!!.second
                        break
                    }
                }
            }
        }

        return score
    }

    // https://adventofcode.com/2021/day/10#part2
    fun part2(input: List<String>): Int {

        return input.size
    }
}
