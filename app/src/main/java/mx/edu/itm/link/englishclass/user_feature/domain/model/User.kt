package mx.edu.itm.link.englishclass.user_feature.domain.model

data class User(
    var id:String = "",
    var name:String = "",
    var surNames:String = "",
    var profession:String = "",
    var status:Boolean = false,
    var photo:String = "")
