package mx.edu.potros.petseguroapp

data class Mascot(
    val raza: String = "",
    val nombre: String = "",
    val edad: Int = 0,
    val cuidadoEspecial: String = "",
    val idDuenio: String? = ""
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "raza" to raza,
            "nombre" to nombre,
            "edad" to edad,
            "cuidadoEspecial" to cuidadoEspecial
            // No incluir idDuenio aquí para evitar actualizarlo
        )
    }
}

