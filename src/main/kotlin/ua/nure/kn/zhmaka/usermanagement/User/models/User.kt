package ua.nure.kn.zhmaka.usermanagement.User.models

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SchemaUtils
import org.joda.time.DateTime

//object class describe created table in DB
object Users : IntIdTable() {
    val firstName: Column<String> = varchar("first_name", 50)
    val lastName: Column<String> = varchar("last_name", 50)
    val dateOfBirth: Column<DateTime> = date("date_of_birth")
}

//class User is presented a simple data object
class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)

    var firstName by Users.firstName
    var lastName by Users.lastName
    var dateofBirth by Users.dateOfBirth
}

data class UserPOJO(var firstName:String,var lastName:String,var dateTime:DateTime )