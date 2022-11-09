package converter

enum class Length(val rate: (Double, Double) -> Double, vararg val names: String) {
    METERS      ({ k, r -> (k * 1.0) / r },      "meter", "m", "meters"),
    KILOMETERS  ({ k, r -> (k * 1000.0) / r },  "kilometer", "km", "kilometers"),
    CENTIMETERS ({ k, r -> (k * 0.01) / r },    "centimeter", "cm", "centimeters"),
    MILLIMETERS ({ k, r -> (k * 0.001) / r },   "millimeter", "mm", "millimeters"),
    MILES       ({ k, r -> (k * 1609.35) / r }, "mile", "mi", "miles"),
    YARDS       ({ k, r -> (k * 0.9144) / r },  "yard", "yd", "yards"),
    FEET        ({ k, r -> (k * 0.3048) / r },  "foot", "ft", "feet"),
    INCHES      ({ k, r -> (k * 0.0254) / r },  "inch", "in", "inches");

    val only = names.first()
    val plural = names.last()
}

enum class Weight(val rate: (Double, Double) -> Double, vararg val names: String) {
    GRAMS       ({ k, r -> (k * 1.0) / r },      "gram", "g", "grams"),
    KILOGRAMS   ({ k, r -> (k * 1000.0) / r },  "kilogram", "kg", "kilograms"),
    MILLIGRAMS  ({ k, r -> (k * 0.001) / r },   "milligram", "mg", "milligrams"),
    POUNDS      ({ k, r -> (k * 453.592) / r }, "pound", "lb", "pounds"),
    OUNCES      ({ k, r -> (k * 28.3495) / r }, "ounce", "oz", "ounces");

    val only = names.first()
    val plural = names.last()
}

enum class Temperature(val rate: Double, vararg val names: String) {
    CELSIUS     (1.0,    "degree Celsius", "c", "dc", "celsius", "degrees Celsius"),
    FAHRENHEIT  (1000.0, "degree Fahrenheit", "r", "df", "fahrenheit", "degrees Fahrenheit"),
    KELVINS     (0.001,  "kelvin", "k", "kelvins");

    val only = names.first()
    val plural = names.last()
}

class Converter(from: String, to: String) {
    val fromUnit = find(from)
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
        return when ((fromUnit, toUnit)) {
            Temperature.CELSIUS
        }
    }

    companion object {
        fun find(string: String) = Length.values().find { string in it.names } ?:
            Weight.values().find { string in it.names } ?:
            Temperature.values().find { string in it.names }
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
        println(Temperature.values().asSequence()
            .flatMap { it.names.asSequence().map { name -> name to it } }
            .toMap()["miles"].)
//        s1 = if (list.size > 4) list[2].lowercase() else list[1].lowercase()
//        s2 = list.last()
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
