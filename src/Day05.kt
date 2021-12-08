import kotlin.math.max
import kotlin.math.min
import kotlin.math.abs

class Map(private val size: Int) {

    private val matrix : IntArray = IntArray(size*size)

    fun addSegments(segments: List<Segment>) {
        segments.forEach {
            addSegment(it)
        }
    }

    fun howManyOverlapping(): Int {
        return matrix.filter { it > 1 }.size
    }

    private fun addSegment(segment: Segment) {
        with(segment) {
            if (isHorizontal()) {
                val from = min(start.x, end.x)
                val to = max(start.x, end.x)
                for (i in from..to) {
                    ++matrix[start.y * size + i]
                }
            } else if (isVertical()) {
                val from = min(start.y, end.y)
                val to = max(start.y, end.y)
                for (i in from..to) {
                    ++matrix[i*size + start.x]
                }
            } else {
                val incrementX = if (end.x > start.x) {
                    1
                } else {
                    -1
                }
                val incrementY = if (end.y > start.y) {
                    1
                } else {
                    -1
                }
                var col = start.x
                var row = start.y
                val distance = abs(start.x - end.x)
                for(i in 0..distance) {
                    ++matrix[row * size + col]
                    row += incrementY
                    col += incrementX
                }
            }
        }
    }

    fun print() {
        val chunked = matrix.toList().chunked(size)
        for (row in chunked) {
            println(row.toString())
        }
    }
}

class Point(input: String) {
    val x: Int
    val y: Int
    init {
        val parts = input.split(",")
        x = Integer.parseInt(parts.first())
        y = Integer.parseInt(parts.last())
    }
}

class Segment(input: String) {
    val start: Point
    val end: Point
    init {
        val parts = input.split(" -> ")
        start = Point(parts.first())
        end = Point(parts.last())
    }

    fun isHorizontal(): Boolean {
        return start.y == end.y
    }

    fun isVertical(): Boolean {
        return start.x == end.x
    }

    fun getTop(): Point {
        return if (start.y <= end.y) {
            start
        } else {
            end
        }
    }

    fun getBottom(): Point {
        return if (start.y >= end.y) {
            start
        } else {
            end
        }
    }
}

class Day05 {

    // https://adventofcode.com/2021/day/5#part1
    fun part1(input: List<String>): Int {

        // Parse input to create only straight segments
        val segments = input.map { line ->
           Segment(line)
        }.filter { it.start.x == it.end.x || it.start.y == it.end.y }

        val map = Map(1000)
        map.addSegments(segments)

        //map.print()

        return map.howManyOverlapping()
    }

    // https://adventofcode.com/2021/day/5#part2
    fun part2(input: List<String>): Int {

        // Parse input to create segments
        val segments = input.map { line ->
            Segment(line)
        }

        val map = Map(1000)
        map.addSegments(segments)

        //map.print()

        return map.howManyOverlapping()
    }

}