package mx.edu.potros.petseguroapp

data class User(var usuario:String?=null,
                var correo:String?=null,
                var contrasenia:String?=null
){
    override fun toString()=usuario+"\t"+correo+"\t"+contrasenia
}
