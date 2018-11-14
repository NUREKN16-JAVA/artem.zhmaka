package ua.nure.kn.zhmaka.usermanagement.User

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


class User : Serializable {
    companion object {
        val serialVersionUID = 1L
    }

    var firstName: String = ""
    var lastName: String = ""
    var birthDate: Date? = null

    constructor(firstName: String, lastName: String, date: Date) {
        this.birthDate = date
        this.firstName = firstName
        this.lastName = lastName
    }

    constructor() {}

    fun getFullName(): String = "$firstName $lastName"

    fun calculateAge(): Int {
        var currentDate:Calendar = Calendar.getInstance()
        var birth:Calendar = getCalendar(birthDate!!)
        var diff:Int = currentDate.get(Calendar.YEAR) - birth.get(Calendar.YEAR)
        if (birth.get(Calendar.MONTH) > currentDate.get(Calendar.MONTH) ||
                (birth.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH) && birth.get(Calendar.DATE) > currentDate.get(Calendar.DATE))) {
            diff--
        }

        if (diff < 0){
            return 0
        }
        return diff
    }


     private fun getCalendar(date: Date):Calendar{
        var cal: Calendar = Calendar.getInstance()
        cal.setTime(date)
        return cal
    }

}

fun main(args: Array<String>) {
    var user: User = User("Ivan", "Ivanov", SimpleDateFormat("d-MMM-yyyy").parse("4-Jan-1999"))
    var date:Date =  SimpleDateFormat("d-MMM-yyyy").parse("5-Jan-1999")
    println(" User age is %d years".format(user.calculateAge()))
    println(" User name is - ".format(user.getFullName()))
}