package converter

fun main() {
    print("Enter a number and a measure: ")
    val (num, unit) = readln().split(' ')
    val units = listOf("km", "kilometer", "kilometers")
    if (unit.lowercase() in units) {
        println("$num ${if (num == "1") units[1] else units[2]} is ${num.toInt().times(1000)} meters")
    } else {
        println("Wrong input")
    }
}
