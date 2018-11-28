package ua.nure.kn.zhmaka.usermanagement.User.Factory

import org.jetbrains.exposed.sql.Database

//connects to DB
interface ConnectionFactory {
    fun CreateConnection()
}