
class Fish(var timer: Int) {

    // Decrease the internal counter
    // it returns true if a new cycle has begun
    fun decrease(): Boolean {
        timer--
        return if (timer < 0) {
            timer = 6
            true
        } else {
            false
        }
    }

    override fun toString(): String {
        return timer.toString()
    }
}

class Day06 {

    // https://adventofcode.com/2021/day/6#part1
    fun part1(input: List<String>): Int {

        val fishes: MutableList<Fish> = input[0].split(",").filter { it.toIntOrNull() != null }.map { Fish(it.toInt()) } as MutableList<Fish>
        for (i in 1..80) {
            var newFishes = 0
            fishes.forEach {
                if (it.decrease()) {
                    newFishes++
                }
            }
            while (newFishes != 0) {
                fishes.add(Fish(8))
                newFishes--
            }

            if (i < 10) {
                print("After  $i days: ")
            } else {
                print("After $i days: ")
            }
            println(fishes.toString())
        }

        return fishes.size
    }

    // https://adventofcode.com/2021/day/6#part2
    fun part2(input: List<String>): Int {

        return input.size
    }

}