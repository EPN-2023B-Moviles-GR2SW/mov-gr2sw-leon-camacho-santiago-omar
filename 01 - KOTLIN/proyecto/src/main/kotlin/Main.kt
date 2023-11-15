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