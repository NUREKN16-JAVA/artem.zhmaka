package ua.nure.kn.zhmaka.usermanagement.User.db

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.joda.time.DateTime
import ua.nure.kn.zhmaka.usermanagement.User.DAO.UserDao
import ua.nure.kn.zhmaka.usermanagement.User.Factory.UserDaoFactory
import ua.nure.kn.zhmaka.usermanagement.User.models.UserPOJO
import ua.nure.kn.zhmaka.usermanagement.User.models.Users
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull


class UserDaoTest : Spek({

    var userDao: UserDao? = null

    describe("Check DAO Logic") {
        beforeEachTest {
            userDao = UserDaoFactory.daoFactory.GetUserDao()
        }

        it("TestCreation") {
            var insertedUser = UserPOJO("Artem", "Zhmaka", DateTime.now())
            transaction {
                SchemaUtils.create(Users)
                var returnedUser = userDao!!.save(insertedUser)
                assertEquals(returnedUser.firstName, insertedUser.firstName)
                assertEquals(returnedUser.lastName, insertedUser.lastName)
                assertEquals(returnedUser.dateofBirth, insertedUser.dateTime)
                SchemaUtils.drop(Users)
            }
        }
        it("TestFind") {
            var insertedUser = UserPOJO("Artem", "Zhmaka", DateTime.now())
            transaction {
                SchemaUtils.create(Users)
                userDao!!.save(insertedUser)
                var find = userDao!!.find(1)
                assertEquals(find!!.firstName, insertedUser.firstName)
                SchemaUtils.drop(Users)
            }
        }
        it("TestFindAll") {
            var insertedUser = UserPOJO("Artem", "Zhmaka", DateTime.now())
            var tempUser = UserPOJO("Test", "Testov", DateTime.now())
            transaction {
                SchemaUtils.create(Users)
                userDao!!.save(insertedUser)
                userDao!!.save(tempUser)
                var all = userDao!!.findAll()
                assertEquals(all.count(), 2)
                SchemaUtils.drop(Users)
            }
        }
        it("TestUpdate") {
            var insertedUser = UserPOJO("Artem", "Zhmaka", DateTime.now())
            transaction {
                SchemaUtils.create(Users)
                userDao!!.save(insertedUser)
                var find = userDao!!.find(1)
                find!!.firstName = "Ivan"
                assertNotNull(find)
                userDao!!.update(find!!)
                var find2 = userDao!!.find(1)
                assertEquals(find2!!.firstName, "Ivan")
                SchemaUtils.drop(Users)
            }
        }
        it("TestDelete") {
            var insertedUser = UserPOJO("Artem", "Zhmaka", DateTime.now())
            transaction {
                SchemaUtils.create(Users)
                userDao!!.save(insertedUser)
                userDao!!.delete(1)
                var find = userDao!!.find(1)
                assertNull(find)
                SchemaUtils.drop(Users)
            }
        }
    }
})
