fun main() {
    val timerValue = readLine()!!.toInt()
    val timer = ByteTimer(timerValue)
    println(timer.time)
}

class ByteTimer(var time: Int) {
    init {
        when  {
            time < -128 -> time = -128
            time > 127 -> time = 127
        }
    }
}