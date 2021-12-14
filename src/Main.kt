
fun main() {

    val day = Day10()
    val input = readInput("Day10")

    val timeStart = System.currentTimeMillis()
    println("Result of part 1: ${day.part1(input)}")
    val timePart1 = System.currentTimeMillis()
    println(" -> Elapsed time: ${timePart1 - timeStart} ms")

    println("Result of part 2: ${day.part2(input)}")
    val timePart2 = System.currentTimeMillis()
    println(" -> Elapsed time: ${timePart2 - timePart1} ms")
}