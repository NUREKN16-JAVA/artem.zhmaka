package ua.nure.kn.zhmaka.usermanagement.User.DAO

import org.jetbrains.exposed.sql.SizedIterable
import ua.nure.kn.zhmaka.usermanagement.User.models.User
import ua.nure.kn.zhmaka.usermanagement.User.models.UserPOJO

//define a basic CRUD operations
interface UserDao {
    fun save(u : UserPOJO):User

    fun find(id :Int):User?

    fun findAll():SizedIterable<User>

    fun update(u:User)

    fun delete(id: Int)
}