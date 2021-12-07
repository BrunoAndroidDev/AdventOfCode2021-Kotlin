class Day03 {

    // https://adventofcode.com/2021/day/3#part1
    fun part1(input: List<String>): Int {

        // pair of counters : first for '0' and second for '1'
        val values = ArrayList<Pair<Int,Int>>()

        repeat(input[0].length) {
            values.add(Pair(0, 0))
        }

        input.forEach {
            for (i in it.indices) {
                val pair = when (it[i]) {
                    '0' -> values[i].copy(first = values[i].first+1)
                    '1' -> values[i].copy(second = values[i].second+1)
                    else -> Pair(0,0)
                }
                values[i] = pair
            }
        }

        // get resulted binary
        val binaryGamma = StringBuilder("")
        val binaryEpsilon = StringBuilder("")
        for (item in values) {
            if (item.first > item.second) {
                binaryGamma.append("0")
                binaryEpsilon.append("1")
            } else {
                binaryGamma.append("1")
                binaryEpsilon.append("0")
            }
        }

        val gamma = Integer.parseInt(binaryGamma.toString(),2)
        val epsilon = Integer.parseInt(binaryEpsilon.toString(),2)

        return gamma*epsilon
    }

    // https://adventofcode.com/2021/day/3#part2
    fun part2(input: List<String>): Int {

        return input.size
    }
}