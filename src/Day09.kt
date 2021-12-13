import kotlin.math.min

class Day09 {

    // https://adventofcode.com/2021/day/9#part1
    fun part1(input: List<String>): Int {

        // get matrix size
        val width = input[0].length
        val height = input.size

        var total = 0

        val matrix = input.map { line -> line.map { Integer.parseInt(it.toString()) } }.flatten()
        for ( i in 0 until height) {
            for (j in 0 until width) {
                val neighbors = arrayListOf<Int>()
                if (i > 0 && i < height-1) {
                    neighbors.add(matrix[(i-1) * width + j  ])
                    neighbors.add(matrix[(i+1) * width + j  ])
                    if (j > 0) {
                        neighbors.add(matrix[i * width + j - 1])
                    }
                    if (j<width-1) {
                        neighbors.add(matrix[i * width + j + 1])
                    }
                }
                if (i == 0) {
                    neighbors.add(matrix[width+j])
                    when (j) {
                        0 -> {
                            neighbors.add(matrix[1])
                        }
                        width-1 -> {
                            neighbors.add(matrix[j-1])
                        }
                        else -> {
                            neighbors.add(matrix[j+1])
                            neighbors.add(matrix[j-1])
                        }
                    }
                }
                if (i == height-1) {
                    neighbors.add(matrix[(i-1)*width+j])
                    when (j) {
                        0 -> {
                            neighbors.add(matrix[(i-1)*width])
                        }
                        width-1 -> {
                            neighbors.add(matrix[i*width + j-1])
                        }
                        else -> {
                            neighbors.add(matrix[i*width + j+1])
                            neighbors.add(matrix[i*width + j-1])
                        }
                    }
                }

                if (matrix[i*width+j] < neighbors.minOf { it }) {
                    total+=matrix[i*width+j]+1
                }
            }
        }
        return total
    }

    // https://adventofcode.com/2021/day/9#part2
    fun part2(input: List<String>): Int {

        return input.size
    }
}
