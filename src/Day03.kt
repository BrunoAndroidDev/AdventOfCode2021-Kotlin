class Day03 {

    // https://adventofcode.com/2021/day/3#part1
    fun part1(input: List<String>): Int {

        // pair of counters : first for '0' and second for '1'
        val values = ArrayList<Pair<Int , Int>>()

        repeat(input[0].length) {
            values.add(Pair(0 , 0))
        }

        input.forEach {
            for (i in it.indices) {
                val pair = when (it[i]) {
                    '0' -> values[i].copy(first = values[i].first + 1)
                    '1' -> values[i].copy(second = values[i].second + 1)
                    else -> Pair(0 , 0)
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

        val gamma = Integer.parseInt(binaryGamma.toString() , 2)
        val epsilon = Integer.parseInt(binaryEpsilon.toString() , 2)

        return gamma * epsilon
    }

    // https://adventofcode.com/2021/day/3#part2
    fun part2(input: List<String>): Int {

        var binaryOxygen = input
        var binaryCO2 = input

        var charIndex= 0
        while (binaryOxygen.size > 1) {
            binaryOxygen = binaryOxygen.filter {
                it[charIndex] == mostCommonCharacter(binaryOxygen, charIndex)
            }
            charIndex++
        }

        charIndex = 0
        while (binaryCO2.size > 1) {
            binaryCO2 = binaryCO2.filter {
                it[charIndex] == leastCommonCharacter(binaryCO2, charIndex)
            }
            charIndex++
        }

        // get resulted binaries
        val oxygen = Integer.parseInt(binaryOxygen[0], 2)
        val co2 = Integer.parseInt(binaryCO2[0], 2)

        return oxygen*co2
    }

    private fun mostCommonCharacter(array: List<String> , bitIndex: Int): Char {
        val counter = compute0and1(array , bitIndex)
        return if (counter.second >= counter.first) {
            '1'
        } else {
            '0'
        }
    }

    private fun leastCommonCharacter(array: List<String> , bitIndex: Int): Char {
        val counter = compute0and1(array , bitIndex)
        return if (counter.first <= counter.second) {
            '0'
        } else {
            '1'
        }
    }

    private fun compute0and1(array: List<String> , bitIndex: Int): Pair<Int , Int> {

        var counter0 = 0
        var counter1 = 0

        for (binary in array) {
            if (binary.isNotEmpty()) {

                when (binary[bitIndex]) {
                    '0' -> counter0 += 1
                    else -> counter1 += 1
                }
            }
        }

        return Pair(counter0 , counter1)
    }
}