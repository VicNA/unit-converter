package converter

class Unit(val textUnit: String) {

    enum class Length(val supportUnits: List<String>, val rate: Double) {
        METERS(listOf("m", "meter", "meters"), 1.0),
        KILOMETERS(listOf("km", "kilometer", "kilometers"), 1000.0),
        CENTIMETERS(listOf("cm", "centimeter", "centimeters"), 0.01),
        MILLIMETERS(listOf("mm", "millimeter", "millimeters"), 0.001),
        MILES(listOf("mi", "mile", "miles"), 1609.35),
        YARDS(listOf("yd", "yard", "yards"), 0.9144),
        FEET(listOf("ft", "foot", "feet"), 0.3048),
        INCHES(listOf("in", "inch", "inches"), 0.0254);
    }

    enum class Weight(val supportUnits: List<String>, val rate: Double) {
        GRAMS(listOf("g", "gram", "grams"), 1.0),
        KILOGRAMS(listOf("kg", "kilogram", "kilograms"), 1000.0),
        MILLIGRAMS(listOf("mg", "milligram", "milligrams"), 0.001),
        POUNDS(listOf("lb", "pound", "pounds"), 453.592),
        OUNCES(listOf("oz", "ounce", "ounces"), 28.3495);
    }

}

fun findUnit(string: String): Unit? {
    val unit: Unit? = Length.values().find { string in it.supportUnits }
    return unit ?: Weight.values().find { string in it.supportUnits }
}

fun main() {
    while (true) {
        print("Enter what you want to convert (or exit): ")
        val input = readln()
        if (input == "exit") break
        val list = input.split(' ')
        val srcUnit = findUnit(list[1])
        val trgUnit = findUnit(list[3])
        when {
            srcUnit == null || trgUnit == null -> println("Conversion from ${list[1]} to feet is impossible")
            trgUnit == null -> println("Conversion from ${list[3]} to feet is impossible")
        }
    }
//    val (inputNum, inputUnit) = readln().split(' ')
//    val unit = Unit.values().find { inputUnit.lowercase() in it.supportUnits }
//    if (unit != null) {
//        val units = unit.supportUnits
//        val num = inputNum.toDouble() * unit.rate
//        val s1 = if (inputNum.toDouble() == 1.0) units[1] else units[2]
//        val s2 = if (num == 1.0) "meter" else "meters"
//        println("${inputNum.toDouble()} $s1 is $num $s2")
//    } else {
//        println("Wrong input. Unknown unit $inputUnit")
//    }

}
