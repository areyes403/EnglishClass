package mx.edu.itm.link.englishclass.Data

data class User(
    var id:String? = null,
    var correo:String? = null,
    var pass: String? = null,
    var nombre:String? = null,
    var apellidos:String?= null,
    var carrera:String?=null,
    var status:String?=null,
    var profileIMG:String?=null)
