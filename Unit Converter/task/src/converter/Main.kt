package converter

enum class Unit(val supportUnits: List<String>, val rate: Double) {
    Meters(listOf("m", "meter", "meters"), 1.0),
    Kilometers(listOf("km", "kilometer", "kilometers"), 1000.0),
    Centimeters(listOf("cm", "centimeter", "centimeters"), 0.01),
    Millimeters(listOf("mm", "millimeter", "millimeters"), 0.001),
    Miles(listOf("mi", "mile", "miles"), 1609.35),
    Yards(listOf("yd", "yard", "yards"), 0.9144),
    Feet(listOf("ft", "foot", "feet"), 0.3048),
    Inches(listOf("in", "inch", "inches"), 0.0254);
}

fun main() {
    print("Enter a number and a measure of length: ")
    val (inputNum, inputUnit) = readln().split(' ')
    val unit = Unit.values().find { inputUnit.lowercase() in it.supportUnits }
    if (unit != null) {
        val units = unit.supportUnits
        val num = inputNum.toDouble() * unit.rate
        val s1 = if (inputNum.toDouble() == 1.0) units[1] else units[2]
        val s2 = if (num == 1.0) "meter" else "meters"
        println("${inputNum.toDouble()} $s1 is $num $s2")
    } else {
        println("Wrong input. Unknown unit $inputUnit")
    }
}
