package ua.nure.kn.zhmaka.usermanagement.User

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert
import java.text.SimpleDateFormat
import java.util.*


class UserTest : Spek({
    var userDate:Date = SimpleDateFormat("d-MMM-yyyy").parse("4-Feb-1999")
    var user: User = User("Ivan", "Ivanov", userDate)
    describe("Check  User logic") {

        it("getName") {
            Assert.assertEquals("Ivan Ivanov", user.getFullName())
        }
        it("calculateAge with happy flow") {
            user.birthDate =  SimpleDateFormat("d-MMM-yyyy").parse("14-Nov-1995")
            Assert.assertEquals(23, user.calculateAge())
        }
        it("calculate witn unhappy flow") {
            user.birthDate = SimpleDateFormat("d-MMM-yyyy").parse("4-Jan-2020")
            Assert.assertEquals(0, user.calculateAge())
        }
        it("calculate with same year but different month"){
            user.birthDate = SimpleDateFormat("d-MMM-yyyy").parse("14-Nov-2018")
            Assert.assertEquals(0, user.calculateAge())
        }
        it("calculate with same year and mouth but different day"){
            user.birthDate =  SimpleDateFormat("d-MMM-yyyy").parse("4-Dec-2018")
            Assert.assertEquals(0, user.calculateAge())
        }
        it("calculate with precisely 1 year difference"){
            user.birthDate =  SimpleDateFormat("d-MMM-yyyy").parse("4-Jan-2017")
            Assert.assertEquals(1, user.calculateAge())
        }
    }
})
