import java.util.Date

fun main() {
    println("Hola Mundo")
    //INMUTABLES (NO se reasignan "=")
    val inmutable: String = "Adrian";
    //inmutable = "Vicente";

    //Mutables (REasignar)
    var mutable: String = "Vicente";
    mutable = "Adrian";

    //val > var
    //Duck Typing
    var ejemploVariable = " Santi Leon "
    var edadEjemplo: Int = 12;
    //var edadEjemplo: Int = 12.00; No funciona
    ejemploVariable.trim()
    //ejemploVariable = edadEjemplo

    //Variables primitivas
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true
    //Clases Java
    val fechaNacimiento: Date = Date()

    //Switch
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }

    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00) //Named Parameters
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)

    val sumaUno = Suma(1, 1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //Arreglo estatico
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //Arreglo dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10
    )

    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //For each -> Unit
    //Iterar arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual:Int ->
            println("Valor actual: ${valorActual}")
        }

    //it (en ingles eso) significa el elemento iterado
    arregloDinamico.forEach{println("Valor actual: ${it}")}

    arregloEstatico
        .forEachIndexed { indice:Int, valorActual:Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    //Map -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve un NUEVO ARREGLO con valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual:Int ->
            return@map valorActual.toDouble()+ 100.00
        }

    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it + 15 }

    //Filter -> Filtrar el arreglo
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual:Int ->
            //Expresion condicion
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }

    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR AND
    //OR -> ANY (Alguno cumple?)
    //AND -> ALL (Todos cumplen?)
    
    val respuestaAny: Boolean = arregloDinamico.any { valorActual:Int ->
        return@any (valorActual > 5)
    }
    println(respuestaAny) //true

    val respuestaAll: Boolean = arregloDinamico.all { valorActual:Int ->
        return@all (valorActual > 5)
    }
    println(respuestaAll) //false

    //REDUCE -> Valor Acumulado
    //Valor acumulado = 0 (siempre 0 en lenguaje Kotlin)
    //[1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    //valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    //valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    //valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    //valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    //valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5
    val respuestaReduce: Int = arregloDinamico.reduce { //acumulado = 0 -> Siempre empieza en 0
        acumulado:Int, valorActual:Int ->
        return@reduce (acumulado + valorActual) // -> Logica de negocio
    }
    println(respuestaReduce) //78
}

abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ) { //Bloque de codigo del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros (// Constructor PRIMARIO
    //Ejemplo
    // uno: Int, (Parametro (sin modificar de acceso))
    // private var uno: int, //Propiedad Publica Clase numeros.uno
    // var uno: Int, //Propiedad de la clase (por defecto es PUBLIC)
    //public var uno: Int,
    protected val numeroUno: Int, //propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int, //propiedad de la clase protected numeros.numeroDos
) {
    // var cedula: String = "" (public es por defecto)
    //private valorCalculado: Int = 0 (private)
    init {
        this.numeroUno; this.numeroDos; //this es opcional
        numeroUno; numeroDos; //sin el "this", es lo mismo
        println("Inicializando")
    }
}

class Suma(//Constructor Primario Suma
    //Parametros
    unoParametro: Int,
    dosParametro: Int,
): Numeros(unoParametro, dosParametro) { //Extendiendo y mandando los parametros (super)
        init { //Bloque codigo constructor primario
            this.numeroUno
            this.numeroDos
        }

    //Para que nos permita recibir nosotros primer parametro nulo o segundo
    constructor( //Segundo constructor
        //Parametros
        uno: Int?,
        dos: Int
    ):this (
        if(uno == null) 0 else uno,
        dos
    )
    constructor( //Tercer constructor
        //Parametros
        uno:Int,
        dos: Int?
    ):this (
        uno,
        if(dos == null) 0 else dos,
    )

    constructor(
        uno: Int?,
        dos: Int?
    ):this (
        if(uno == null) 0 else uno,
        if(dos == null) 0 else dos,
    )

    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        // Suma.agregarHistorial(total)
        agregarHistorial(total)
        return total
    }

    // Atributos y Metodos "Compartidos"
    companion object {
        // entre las instancias
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
        }
    }
}



//void in Kotlin is Unit
fun imprimirNombre (nombre: String): Unit {
    //"Nombre : " + nombre
    println("Nombre: ${nombre}") //template settings
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcional (defecto)
    bonoEspecial: Double? = null, //Opcion null -> nullable
): Double {
    //Int -> Int? (nullable)
    //String -> String? (nullable)
    //Date -> Date? (nullable)
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return  sueldo * (100 / tasa) + bonoEspecial
    }
}