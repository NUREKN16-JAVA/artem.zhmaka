package ua.nure.kn.zhmaka.usermanagement.User.Factory

import ua.nure.kn.zhmaka.usermanagement.User.DAO.UserDao
import ua.nure.kn.zhmaka.usermanagement.User.DAOImpl.PostgresDaoUserImpl
import ua.nure.kn.zhmaka.usermanagement.User.models.User

//creates connection and return userdao
//here can be properties parsing with different connection params
class UserDaoFactory {
    val Url = "jdbc:postgresql://localhost:5432/java_course"
    val User = "gous"
    val Password = "it"

    companion object {
        val daoFactory: UserDaoFactory = UserDaoFactory()
    }

    fun GetInstance(): UserDaoFactory {
        return daoFactory
    }

    fun GetUserDao(): UserDao {
        return PostgresDaoUserImpl(GetConnectionFactory())
    }

    private fun GetConnectionFactory(): ConnectionFactory {
        return ConnectionFactoryImpl(Url, User, Password)
    }


}