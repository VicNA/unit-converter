package converter

enum class TypeUnit {
    LENGTH, WEIGHT, TEMPERATURE, UNKNOWN;

    override fun toString(): String {
        return name[0] + name.drop(1).lowercase()
    }
}

enum class Unit(val rate: (Double) -> Double, val type: TypeUnit, vararg val names: String) {
    METERS      ({ m -> m },                TypeUnit.LENGTH, "meter", "m", "meters"),
    KILOMETERS  ({ km -> km * 1000.0 },     TypeUnit.LENGTH, "kilometer", "km", "kilometers"),
    CENTIMETERS ({ cm -> cm * 0.01 },       TypeUnit.LENGTH, "centimeter", "cm", "centimeters"),
    MILLIMETERS ({ mm -> mm * 0.001 },      TypeUnit.LENGTH, "millimeter", "mm", "millimeters"),
    MILES       ({ mi -> mi * 1609.35 },    TypeUnit.LENGTH, "mile", "mi", "miles"),
    YARDS       ({ yd -> yd * 0.9144 },     TypeUnit.LENGTH, "yard", "yd", "yards"),
    FEET        ({ ft -> ft * 0.3048 },     TypeUnit.LENGTH, "foot", "ft", "feet"),
    INCHES      ({ inch -> inch * 0.0254 }, TypeUnit.LENGTH, "inch", "in", "inches"),

    GRAMS       ({ g -> g },             TypeUnit.WEIGHT, "gram", "g", "grams"),
    KILOGRAMS   ({ kg -> kg * 1000.0 },  TypeUnit.WEIGHT, "kilogram", "kg", "kilograms"),
    MILLIGRAMS  ({ mg -> mg * 0.001 },   TypeUnit.WEIGHT, "milligram", "mg", "milligrams"),
    POUNDS      ({ lb -> lb * 453.592 }, TypeUnit.WEIGHT, "pound", "lb", "pounds"),
    OUNCES      ({ oz -> oz * 28.3495 }, TypeUnit.WEIGHT, "ounce", "oz", "ounces"),

    CELSIUS     ({ c -> c },     TypeUnit.TEMPERATURE, "degree Celsius", "c", "dc", "celsius", "degrees Celsius"),
    FAHRENHEIT  ({ f -> f },     TypeUnit.TEMPERATURE, "degree Fahrenheit", "f", "df", "fahrenheit", "degrees Fahrenheit"),
    KELVINS     ({ k -> k },     TypeUnit.TEMPERATURE, "kelvin", "k", "kelvins"),

    UNKNOWN     ({ 0.0 },     TypeUnit.UNKNOWN, "???");

    val onlyName = names.first()
    val pluralName = names.last()

    companion object {
        private val values = values()
        fun find(string: String) = values.find { string in it.names } ?: UNKNOWN
    }
}

class Converter(val fromUnit: Unit, val toUnit: Unit) {

    fun isConverted() = fromUnit.type != TypeUnit.UNKNOWN && toUnit.type != TypeUnit.UNKNOWN
            && fromUnit.type == toUnit.type

    fun convertTo(value: Double): Double {
        return when {
            fromUnit == Unit.CELSIUS && toUnit == Unit.KELVINS -> fromUnit.rate(value) + 273.15
            fromUnit == Unit.CELSIUS && toUnit == Unit.FAHRENHEIT -> fromUnit.rate(value) * 9 / 5 + 32
            fromUnit == Unit.FAHRENHEIT && toUnit == Unit.CELSIUS -> (fromUnit.rate(value) - 32) * 5 / 9
            fromUnit == Unit.FAHRENHEIT && toUnit == Unit.KELVINS -> (fromUnit.rate(value) + 459.67) * 5 / 9
            fromUnit == Unit.KELVINS && toUnit == Unit.CELSIUS -> fromUnit.rate(value) - 273.15
            fromUnit == Unit.KELVINS && toUnit == Unit.FAHRENHEIT -> fromUnit.rate(value) * 9 / 5 - 459.67
            fromUnit == toUnit -> fromUnit.rate(value)
            else -> fromUnit.rate(value) / toUnit.rate(1.0)
        }
    }
}


fun main() {
    while (true) {
        print("Enter what you want to convert (or exit): ")
        val input = readln()
        if (input == "exit") break

        val list = input.split(' ')

        val fromNumber = list[0].toDoubleOrNull()
        if (fromNumber == null) {
            println("Parse error\n")
            continue
        }

        val fromUnit = Unit.find(if (list.size > 4) list[2].lowercase() else list[1].lowercase())
        val toUnit = Unit.find(list.last().lowercase())
        if (fromNumber < 0 && fromUnit.type == TypeUnit.LENGTH || fromUnit.type == TypeUnit.WEIGHT) {
            println("${fromUnit.type.name} shouldn't be negative\n")
            continue
        }

        val converter = Converter(fromUnit, toUnit)
        if (!converter.isConverted()) {
            println("Conversion from ${fromUnit.pluralName} to ${toUnit.pluralName} is impossible\n")
            continue
        }

        val toNumber = converter.convertTo(fromNumber)
        val s1 = if (fromNumber == 1.0) fromUnit.onlyName else fromUnit.pluralName
        val s2 = if (toNumber == 1.0) toUnit.onlyName else toUnit.pluralName
        println("$fromNumber $s1 is $toNumber $s2\n")
    }
}
