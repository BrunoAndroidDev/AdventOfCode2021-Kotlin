
class Dumbo(
    var coord: Coord,
    var energy: Int,
    var flashed: Boolean = false
) {
    fun reset() {
        energy = 0
        flashed = false
    }
}

class Day11 {

    private val width = 10
    private val height = 10
    private val nStep = 100

    // https://adventofcode.com/2021/day/11#part1
    fun part1(input: List<String>): Int {

        val matrix = input.mapIndexed { l, line ->
            line.split("").filter { n -> n.isNotBlank() }.mapIndexed { c, col ->
                Dumbo(coord = Coord(x = l, y = c), energy = col.toInt())
            }
        }.flatten()
        var totalFlashes = 0

        var step = 0
        while (step++ < nStep) {

            val stepHasFlashedList = mutableListOf<Dumbo>()

            // increase energy
            matrix.forEach { it.energy++ }

            // flashes
            val currentFlashes = mutableListOf<Dumbo>()
            for (i in 0 until height) {
                for (j in 0 until width) {
                    val dumbo = matrix[i*width+j]
                    if (dumbo.energy > 9) {
                        currentFlashes.add(dumbo)
                        dumbo.flashed = true
                    }
                }
            }
            while (currentFlashes.isNotEmpty()) {
                stepHasFlashedList.addAll(currentFlashes)

                val nextFlashers = mutableListOf<Dumbo>()

                currentFlashes.forEach { dumbo ->
                    val newDumbos = flash(matrix, dumbo.coord)
                    newDumbos.filter {
                        !it.flashed && it.energy > 9 && it !in stepHasFlashedList }.forEach {
                        it.flashed = true
                        nextFlashers.add(it)
                    }
                }

                currentFlashes.clear()
                currentFlashes.addAll(nextFlashers)
            }

            with (stepHasFlashedList.distinct()) {
                totalFlashes += this.size
                forEach {
                    matrix[it.coord.x * width + it.coord.y].reset()
                }
            }

            println("After step $step")
            printMatrix(matrix)
        }
        return totalFlashes
    }

    // https://adventofcode.com/2021/day/11#part2
    fun part2(input: List<String>): Int {

        return input.size
    }

    private fun indexValid(point: Coord): Boolean {
        return point.x in 0 until height && point.y in 0 until width
    }

    private fun flash(matrix: List<Dumbo>, point: Coord): List<Dumbo> {
        val newFlashes = mutableListOf<Dumbo>()
        for (i in -1..1) {
            for (j in -1..1) {
                val neighbor = Coord(point.x+i, point.y+j)
                if (indexValid(neighbor)) {
                    val dumbo = matrix[neighbor.x * width + neighbor.y]
                    newFlashes.add(dumbo)
                    dumbo.energy++
                }
            }
        }

        return newFlashes
    }

    private fun printMatrix(matrix: List<Dumbo>) {
        matrix.toList().chunked(width).forEach {list ->
            println(list.map { it.energy }.toString())
        }
        println()
    }

}
