package converter

enum class Unit(val type: String, val names: List<String>, val rate: Double) {
    METERS("length", listOf("m", "meter", "meters"), 1.0),
    KILOMETERS("length", listOf("km", "kilometer", "kilometers"), 1000.0),
    CENTIMETERS("length", listOf("cm", "centimeter", "centimeters"), 0.01),
    MILLIMETERS("length", listOf("mm", "millimeter", "millimeters"), 0.001),
    MILES("length", listOf("mi", "mile", "miles"), 1609.35),
    YARDS("length", listOf("yd", "yard", "yards"), 0.9144),
    FEET("length", listOf("ft", "foot", "feet"), 0.3048),
    INCHES("length", listOf("in", "inch", "inches"), 0.0254),

    GRAMS("weight", listOf("g", "gram", "grams"), 1.0),
    KILOGRAMS("weight", listOf("kg", "kilogram", "kilograms"), 1000.0),
    MILLIGRAMS("weight", listOf("mg", "milligram", "milligrams"), 0.001),
    POUNDS("weight", listOf("lb", "pound", "pounds"), 453.592),
    OUNCES("weight", listOf("oz", "ounce", "ounces"), 28.3495);

    fun equalsType(unit: Unit) = this.type == unit.type

    companion object {
        fun findUnit(string: String) = Unit.values().find { string in it.names }
    }
}


fun main() {
    var s1: String
    var s2: String
    while (true) {
        print("Enter what you want to convert (or exit): ")
        val input = readln()
        if (input == "exit") break

        val list = input.split(' ')
        s1 = if (list.size > 4) list[2].lowercase() else list[1].lowercase()
        s2 = list.last()
        val srcUnit = Unit.findUnit(s1)
        val trgUnit = Unit.findUnit(s2)
        if (srcUnit == null || trgUnit == null || !srcUnit.equalsType(trgUnit)) {
            println("Conversion from ${srcUnit?.name?.lowercase() ?: "???"} to ${trgUnit?.name?.lowercase() ?: "???"} is impossible\n")
            continue
        }
//
//        val num = (list[0].toDouble() * srcUnit.rate) / trgUnit.rate
//        val s1 = if (list[0].toDouble() == 1.0) srcUnit.names[1] else srcUnit.names[2]
//        val s2 = if (num == 1.0) trgUnit.names[1] else trgUnit.names[2]
//        println("${list[0].toDouble()} $s1 is $num $s2\n")
    }
}
