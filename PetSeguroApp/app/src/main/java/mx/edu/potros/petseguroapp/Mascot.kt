package mx.edu.potros.petseguroapp

data class Mascot(
                var raza:String?=null,
                var nombre:String?=null,
                var edad:Int?=null,
                var cuidadoEspecial:String?=null,
                var idDuenio:String?=null
){
    override fun toString()=raza+"\t"+nombre+"\t"+edad+"\t"+cuidadoEspecial+"\t"+idDuenio
}
