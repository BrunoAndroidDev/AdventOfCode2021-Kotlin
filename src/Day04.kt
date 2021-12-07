
// A cell is made of a value and a boolean indicating if this value has been marked
class Cell(val value: Int, var marked: Boolean) {
    fun match(input: Int) : Boolean {
        if (value == input) {
            marked = true
        }
        return marked
    }
}

class Board(input: List<String>) {

    companion object {
        private var _id = 0
    }

    val id: Int = _id++

    // 2D Array containing a bunch of cells
    private val matrix = ArrayList<List<Cell>>()
    init {
        if (input.isNotEmpty()) {
            input.forEach { line ->
                val lineOfCells = line.split(" ").filter { it.isNotEmpty() }.map {
                    Cell(Integer.parseInt(it), false)
                }
                matrix.add(lineOfCells)
            }
        }
    }

    private fun sumUnmarked(): Int {
        return matrix.flatten().filter { !it.marked }.sumOf {
            it.value
        }
    }

    // Check if the value is present in one line.
    private fun checkLines(): Boolean {
        matrix.forEach { line ->
            if (line.all { it.marked }) {
                return true
            }
        }
        return false
    }

    // Check if the value is present in one column.
    private fun checkColumns(): Boolean {

        for (col in 0 until matrix[0].size) {
            val column = ArrayList<Cell>()
            for (row in 0 until matrix.size) {
                column.add(matrix[row][col])
            }
            if (column.all { it.marked }) {
                return true
            }
        }
        return false
    }

    fun find(value: Int): Int {
        var found = false
        matrix.forEach { line ->
            line.forEach {
                if (it.match(value)) {
                    found = true
                }
            }
        }
        return if (found) {
            if (!checkLines()) {
                if (!checkColumns()) {
                    -1
                } else {
                    sumUnmarked()
                }
            } else {
                sumUnmarked()
            }
        } else {
            -1
        }
    }
}

class Day04 {

    // https://adventofcode.com/2021/day/4#part1
    fun part1(input: List<String>): Int {

        // save random values
        val randomData = input.first().split(",")

        // create Boards
        val boards = ArrayList<Board>()
        val lines = ArrayList<String>()
        input.drop(2).forEach {
            if (it.isEmpty()) {
                boards.add(Board(lines))
                lines.clear()
            } else {
                lines.add(it)
            }
        }
        boards.add(Board(lines))

        var number = 0
        randomData.forEach {
            number = Integer.parseInt(it)
            boards.forEach { board ->
                val sum = board.find(number)
                if (sum != -1) {
                    return number*sum
                }
            }
        }

        return 0
    }

    // https://adventofcode.com/2021/day/4#part2
    fun part2(input: List<String>): Int {

        // save random values
        val randomData = input.first().split(",")

        // create Boards
        val boards = ArrayList<Board>()
        val lines = ArrayList<String>()
        input.drop(2).forEach {
            if (it.isEmpty()) {
                boards.add(Board(lines))
                lines.clear()
            } else {
                lines.add(it)
            }
        }
        boards.add(Board(lines))

        var remainingBoards = boards.map { it.id }
        var result = 0
        randomData.forEach { data ->
            val number = Integer.parseInt(data)
            boards.forEach { board ->
                val sum = board.find(number)
                if (sum != -1) {
                    if (remainingBoards.contains(board.id)) {
                        result = number*sum
                        remainingBoards = remainingBoards.filter { it != board.id }
                    }
                }
            }
        }

        return result
    }

}