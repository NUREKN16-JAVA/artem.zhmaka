package ua.nure.kn.zhmaka.usermanagement.User.Factory

import org.jetbrains.exposed.sql.Database
import java.sql.DriverManager

class ConnectionFactoryImpl(val url:String,val user:String,val password:String) : ConnectionFactory {

    override fun CreateConnection() {
        Database.connect({ DriverManager.getConnection(url, user, password) })
    }
}