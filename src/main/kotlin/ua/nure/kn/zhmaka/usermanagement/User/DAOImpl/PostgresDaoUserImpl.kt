package ua.nure.kn.zhmaka.usermanagement.User.DAOImpl


import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import ua.nure.kn.zhmaka.usermanagement.User.DAO.UserDao
import ua.nure.kn.zhmaka.usermanagement.User.Factory.ConnectionFactory
import ua.nure.kn.zhmaka.usermanagement.User.Factory.ConnectionFactoryImpl
import ua.nure.kn.zhmaka.usermanagement.User.models.User
import ua.nure.kn.zhmaka.usermanagement.User.models.UserPOJO
import java.sql.DriverManager

//DAO Implementation for PostgresDB

class PostgresDaoUserImpl(val connFactory: ConnectionFactory) : UserDao {

    var user: User? = null
    var users: SizedIterable<User>? = null
    override fun save(u: UserPOJO): User {
        connFactory.CreateConnection()
        user = transaction {
            User.new {
                firstName = u.firstName
                lastName = u.lastName
                dateofBirth = u.dateTime
            }
        }
        return user!!
    }

    override fun find(id: Int): User? {
        connFactory.CreateConnection()
        user = transaction { User.findById(5) }
        return user
    }

    override fun findAll(): SizedIterable<User> {
        connFactory.CreateConnection()
        users = transaction { User.all() }
        return users!!
    }

    override fun update(u: User) {
        connFactory.CreateConnection()
        user = transaction { User.findById(u.id) }
        user?.firstName = u.firstName
        user?.lastName = u.lastName
        user?.dateofBirth = u.dateofBirth
    }


    override fun delete(id: Int) {
        connFactory.CreateConnection()
        val user = transaction { User.findById(id)}
        user?.delete()
    }

}