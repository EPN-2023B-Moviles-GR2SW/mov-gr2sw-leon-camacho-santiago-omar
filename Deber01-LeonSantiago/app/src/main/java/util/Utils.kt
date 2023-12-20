package util

import java.io.File

object Utils {
    fun leerArchivo(ruta: String): String {
        val archivo = File(ruta)
        return if (archivo.exists()) {
            archivo.readText()
        } else {
            ""
        }
    }

    fun escribirArchivo(ruta: String, contenido: String, append: Boolean = false) {
        val archivo = File(ruta)
        archivo.writeText(contenido, Charsets.UTF_8, append)
    }

    fun crearArchivoSiNoExiste(ruta: String) {
        val archivo = File(ruta)
        if (!archivo.exists()) {
            archivo.createNewFile()
        }
    }

    fun eliminarArchivo(ruta: String) {
        val archivo = File(ruta)
        if (archivo.exists()) {
            archivo.delete()
        }
    }
}
