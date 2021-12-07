fun main() {
    var day = Day01()

    //val testInput = readInput("Day01_test")
    //check(day.part1(testInput) == 1)

    val input = readInput("Day01")
    println("Result of part 1: ${day.part1(input)}")
    println("Result of part 2: ${day.part2(input)}")

}