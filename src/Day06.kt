
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

            /*
            if (i < 10) {
                print("After  $i days: ")
            } else {
                print("After $i days: ")
            }
            println(fishes.toString())
*/

        }

        return fishes.size
    }

    // https://adventofcode.com/2021/day/6#part2
    fun part2(input: List<String>): Long {

        // get number of times each day occurs in input
        val init = input[0].split(",").map { it.toInt() }.groupingBy { it }.eachCount()

        // map of days left to amount of fish
        val pool = mutableMapOf(
            0 to 0L, 1 to 0L, 2 to 0L, 3 to 0L, 4 to 0L,
            5 to 0L, 6 to 0L, 7 to 0L, 8 to 0L
        )

        init.forEach{
            pool[it.key] = it.value.toLong()
        }

        for (i in 1..256) {
            // separate 0s from the pack
            val zeroDayFish = pool[0]!!

            // shift all fish down one day
            pool.keys.forEach {
                if (it != 0)
                    pool[it - 1] = pool[it]!!
            }

            // add 0s into 8s (new spawn)
            pool[8] = zeroDayFish

            // merge 0s into 6s (resets 0s)
            pool.merge(6, zeroDayFish) { a, b -> a + b }
        }

        return pool.values.sum()
    }

}