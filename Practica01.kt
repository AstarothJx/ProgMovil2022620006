import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit

fun main() {
    do {
        println("\nMenú Principal:")
        println("1. Sumar tres números")
        println("2. Ingresar nombre completo")
        println("3. Calcular tiempo vivido desde fecha de nacimiento")
        println("4. Salir")
        print("Seleccione una opción: ")

        val opcion = readln().trim().toInt()

        when (opcion) {
            1 -> sumar3()
            2 -> nombre()
            3 -> tiempoVivido()
            4 -> println("Saliendo del programa...")
            else -> println("Opción no válida. Intente nuevamente.")
        }
    } while (opcion != 4)
}

fun sumar3() {
    print("Ingrese el primer número: ")
    val n1 = readln().toDouble()
    print("Ingrese el segundo número: ")
    val n2 = readln().toDouble()
    print("Ingrese el tercer número: ")
    val n3 = readln().toDouble()

    val suma = n1 + n2 + n3
    println("Resultado: $suma")
}

fun nombre() {
    print("Ingrese su nombre completo: ")
    val nombre = readln()
    println("Nombre ingresado: $nombre")
}

fun tiempoVivido() {
    print("Ingrese su fecha de nacimiento (YYYY-MM-DD): ")
    val fechaNacimiento = LocalDate.parse(readln().trim())
    val fechaActual = LocalDate.now()
    val periodo = Period.between(fechaNacimiento, fechaActual)
    val diasTotales = ChronoUnit.DAYS.between(fechaNacimiento, fechaActual)
    val semanasTotales = diasTotales / 7
    val horasTotales = diasTotales * 24
    val minutosTotales = horasTotales * 60
    val segundosTotales = minutosTotales * 60

    println("Tiempo vivido:")
    println("Años: ${periodo.years}, Meses: ${periodo.months}, Días: ${periodo.days}")
    println("Semanas: $semanasTotales")
    println("Horas: $horasTotales")
    println("Minutos: $minutosTotales")
    println("Segundos: $segundosTotales")
}
