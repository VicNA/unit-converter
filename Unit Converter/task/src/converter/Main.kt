package converter

enum class Length(val rate: Double, vararg val names: String) {
    METERS      (1.0,     "meter", "m", "meters"),
    KILOMETERS  (1000.0,  "kilometer", "km", "kilometers"),
    CENTIMETERS (0.01,    "centimeter", "cm", "centimeters"),
    MILLIMETERS (0.001,   "millimeter", "mm", "millimeters"),
    MILES       (1609.35, "mile", "mi", "miles"),
    YARDS       (0.9144,  "yard", "yd", "yards"),
    FEET        (0.3048,  "foot", "ft", "feet"),
    INCHES      (0.0254,  "inch", "in", "inches");

    fun convertTo(value: Double, target: Length) = (value * rate) / target.rate

    val only = names.first()
    val plural = names.last()

    companion object {
        private val values = values()
        fun find(string: String) = values.find { string in it.names }
    }
}

enum class Weight(val rate: Double, vararg val names: String) {
    GRAMS       (1.0,     "gram", "g", "grams"),
    KILOGRAMS   (1000.0,  "kilogram", "kg", "kilograms"),
    MILLIGRAMS  (0.001,   "milligram", "mg", "milligrams"),
    POUNDS      (453.592, "pound", "lb", "pounds"),
    OUNCES      (28.3495, "ounce", "oz", "ounces");

    fun convertTo(value: Double, target: Weight) = (value * rate) / target.rate

    val only = names.first()
    val plural = names.last()

    companion object {
        private val values = values()
        fun find(string: String) = values.find { string in it.names }
    }
}

enum class Temperature(vararg val names: String) {
    CELSIUS     ("degree Celsius", "c", "dc", "celsius", "degrees Celsius"),
    FAHRENHEIT  ("degree Fahrenheit", "r", "df", "fahrenheit", "degrees Fahrenheit"),
    KELVINS     ("kelvin", "k", "kelvins");



    val only = names.first()
    val plural = names.last()

    companion object {
        private val values = values()
        fun find(string: String) = values.find { string in it.names }
    }
}

class Converter(from: String, to: String) {
//    val fromUnit = Length.find(from) ?: Weight.find(from) ?: Temperature.find(from)
    val fromUnit = from.let { Length.find(it) ?: Weight.find(it) ?: Temperature.find(it) }
    val toUnit = find(to)

    fun isPossible(): Boolean {
        if (fromUnit != null && toUnit != null) {
            if (fromUnit is Length && toUnit is Length) return true
            if (fromUnit is Weight && toUnit is Weight) return true
            if (fromUnit is Temperature && toUnit is Temperature) return true
        }
        return false
    }

    fun convertTo() {
        return when (toUnit) {
            fromUnit == Temperature.CELSIUS && toUnit == Temperature.KELVINS ->
        }
    }

//    companion object {
//        fun find(string: String) = Length.values().find { string in it.names } ?:
//            Weight.values().find { string in it.names } ?:
//            Temperature.values().find { string in it.names }
//    }
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
//        val converter = Converter(s1, s2)
//        if (converter.isPossible()) {
//            println("Conversion from ${srcUnit?.name?.lowercase() ?: "???"} to ${trgUnit?.name?.lowercase() ?: "???"} is impossible\n")
//            continue
//
//        }
//        enumValueOf<Length>().names
//        val srcUnit = Converter.toUnit(s1)
//        val trgUnit = Converter.toUnit(s2)
//        if (srcUnit == null || trgUnit == null || !srcUnit.equalsType(trgUnit)) {
//        }
//        println(srcUnit?.name)
//
//        val num = (list[0].toDouble() * srcUnit.rate) / trgUnit.rate
//        val s1 = if (list[0].toDouble() == 1.0) srcUnit.names[1] else srcUnit.names[2]
//        val s2 = if (num == 1.0) trgUnit.names[1] else trgUnit.names[2]
//        println("${list[0].toDouble()} $s1 is $num $s2\n")
    }
}
