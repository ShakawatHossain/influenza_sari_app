package iedcr.shakawat.sariapplication.models

import java.util.*

class User {
    companion object{
        public var user: Int =0
        public val hos_name_arr = arrayOf<String>("Adhunik Sadar Hospital, Thakurgaon",
        "100 Bed District Sadar Hospital, Naogaon",
        "Sadar Hospital, Sathkira",
        "100 Bed District  Hospital, Narshingdi",
        "250 Bed Adhunik District Sadar Hospital, Hobiganj",
        "250 Bed District Sadar Hospital, Coxs Bazar",
        "Adhunik District Sadar Hospital, Joypurhat",
        "Dhaka Medical College Hospital, Dhaka",
        "250 Bed Adhunik Sadar Hospital, Patuakhali",
        "Shaheed Tajuddin Ahmad Medical College Hospital, Gazipur")

        public val hos_serial_arr = arrayOf<String>("01","03","06","08","10","14","15","16","17","18");
        public fun check(userId : String, password: String) : Boolean{
            var is_ok : Boolean = false
            var usrID  = ArrayList<String>()
            var pass = ArrayList<String>()
            for (i in 0..hos_serial_arr.size-1){
                usrID.add("iedcr"+hos_serial_arr[i])
                pass.add("pass"+hos_serial_arr[i])
            }
            for (i in 0..hos_serial_arr.size-1){
                if(userId.compareTo(usrID.get(i))==0 && password.compareTo(pass.get(i))==0) {
                    user = Integer.parseInt(hos_serial_arr.get(i))
                    Info.hos_id = hos_name_arr.get(i)
                    is_ok = true
                    break
                }
            }
            return is_ok
        }
    }

}