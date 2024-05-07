package mx.edu.itm.link.englishclass.core.utils

import mx.edu.itm.link.englishclass.authentication_feature.domain.model.UserEntity
import mx.edu.itm.link.englishclass.user_feature.domain.model.User

fun User.toUserEntity():UserEntity= UserEntity(
    id = id,
    name = name,
    surNames = surNames,
    profession = profession,
    photo = photo
)

fun UserEntity.toUser():User= User(
    id = id,
    name = name!!,
    surNames = surNames!!,
    profession = profession!!,
    photo = photo
)