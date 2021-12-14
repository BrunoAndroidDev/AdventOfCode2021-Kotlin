class Day10 {

    // https://adventofcode.com/2021/day/10#part1
    fun part1(input: List<String>): Int {

        val openChars = "([{<"
        val closeChars = mutableMapOf(
            ')' to Pair('(',3),
            ']' to Pair('[',57),
            '}' to Pair('{',1197),
            '>' to Pair('<',25137)
        )

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
    fun part2(input: List<String>): Long {

        val openChars = mutableMapOf(
            '(' to Pair(')',1),
            '[' to Pair(']',2),
            '{' to Pair('}',3),
            '<' to Pair('>',4)
        )
        val closeChars = mutableMapOf(
            ')' to '(',
            ']' to '[',
            '}' to '{',
            '>' to '<'
        )

        val scoreTabs = mutableSetOf<Long>()

        input.forEach { line ->
            var skip = false
            val chunks = StringBuilder()
            for (char in line) {
                if (openChars.keys.contains(char)) {
                    chunks.append(char)
                } else {
                    if (chunks.last() == closeChars[char]!!) {
                        chunks.deleteCharAt(chunks.length-1)
                    } else {
                        skip = true
                        break
                    }
                }
            }
            if (!skip) {
                var score = 0L
                chunks.reverse().forEach {
                    score = score * 5 + openChars[it]!!.second
                }
                scoreTabs.add(score)
            }
        }

        return scoreTabs.sorted()[scoreTabs.size/2]
    }
}
