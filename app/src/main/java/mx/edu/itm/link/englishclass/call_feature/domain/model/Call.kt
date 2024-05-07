package mx.edu.itm.link.englishclass.call_feature.domain.model

data class Call(
    var idEmisor:String ="",
    var idReceptor:String ="",
    var date:String = "",
    var emisor:String = "",
    var receptor:String = ""
)