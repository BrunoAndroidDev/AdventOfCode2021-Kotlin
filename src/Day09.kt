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

        // get matrix size
        val width = input[0].length
        val height = input.size

        var lowestPoints = mutableListOf<Pair<Int,Int>>()

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
                    lowestPoints.add(Pair(i, j))
                }
            }
        }

        val biggestBasins = mutableListOf(0, 0, 0)
        lowestPoints.forEach {
            val size = getBasinSize(ArrayList(matrix), width, height, it.first, it.second)
            biggestBasins.sort()
            if (size > biggestBasins.first()) {
                biggestBasins[0] = size
            }
        }

        return biggestBasins.reduce { acc, i -> acc * i }
    }

    // get size of the basin centered in (x,y)
    // Use the 'growing-region' method
    private fun getBasinSize(matrix: ArrayList<Int> , width: Int , height: Int , row: Int , col: Int): Int {

        var size = 0

        if (matrix[row * width + col] != 9) {
            // 'deja-vu' marker
            matrix[row * width + col] = 9

            // init size
            size = 1

            // find neighbors
            val neighbors = arrayListOf<Pair<Int,Int>>()
            if (row > 0 && row < height-1) {
                neighbors.add(Pair(row-1, col))
                neighbors.add(Pair(row+1, col))
                if (col > 0) {
                    neighbors.add(Pair(row, col-1))
                }
                if (col<width-1) {
                    neighbors.add(Pair(row, col+1))
                }
            }
            if (row == 0) {
                neighbors.add(Pair(1, col))
                when (col) {
                    0 -> {
                        neighbors.add(Pair(0, 1))
                    }
                    width-1 -> {
                        neighbors.add(Pair(0, col-1))
                    }
                    else -> {
                        neighbors.add(Pair(0, col+1))
                        neighbors.add(Pair(0, col-1))
                    }
                }
            }
            if (row == height-1) {
                neighbors.add(Pair(row-1, col))
                when (col) {
                    0 -> {
                        neighbors.add(Pair(row-1, 0))
                    }
                    width-1 -> {
                        neighbors.add(Pair(row, col-1))
                    }
                    else -> {
                        neighbors.add(Pair(row, col+1))
                        neighbors.add(Pair(row, col-1))
                    }
                }
            }

            // check id neighbors are part of the region
            neighbors.forEach { size += getBasinSize(matrix, width, height, it.first, it.second) }
        }

        return size
    }
}
