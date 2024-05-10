package mx.edu.itm.link.englishclass.call_feature.domain.model

import mx.edu.itm.link.englishclass.core.domain.model.GeneralId

data class RealtimeCall(
    var id:String,
    var created_At:Any? = null,
    var finished_At:Any?=null,
    var state:String="",
    var emisor:GeneralId?=null,
    var receptor:GeneralId?=null
)