package com.example.kmm_performance

fun UserDto.toUser(): User {
    return User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}

fun List<UserDto>.toUserList(): List<User> {
    return map{it.toUser()}
}

fun User.toUserDto(): UserDto {
    return UserDto(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}



