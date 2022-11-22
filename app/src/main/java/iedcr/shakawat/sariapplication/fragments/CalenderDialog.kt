package iedcr.shakawat.sariapplication.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import iedcr.shakawat.sariapplication.R
import iedcr.shakawat.sariapplication.interfaces.CalenderInterface
import kotlinx.android.synthetic.main.cld_view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class CalenderDialog : Dialog{
    var calenderInterface: CalenderInterface
    var day : Int =0
    var mth : Int =0
    var yr : Int =0
    var title_name: String
    var text: TextView

    constructor(
        calenderInterface: CalenderInterface,
        date: String,
        title_name: String,
        text: TextView,
        context: Context
    ): super(context){
        this.calenderInterface = calenderInterface
        this.title_name = title_name
        this.text = text
        Log.d("CalenderDialog",date)
        if(date.compareTo("")==0 || date.compareTo("null")==0 || date.compareTo("YYYY-MM-DD")==0){

            var dt:Date = Date()
            Log.d("today",dt.toString())
            var cal : Calendar = Calendar.getInstance()
            cal.time = dt
            this.day = cal.get(Calendar.DAY_OF_MONTH)
            this.mth = cal.get(Calendar.MONTH)
            this.yr = cal.get(Calendar.YEAR)
        }else{
            var spec_c_date = SimpleDateFormat("yyyy-MM-dd")
            try {
                var dt:Date = spec_c_date.parse(date)
                var cal : Calendar = Calendar.getInstance()
                cal.time = dt
                this.day = cal.get(Calendar.DAY_OF_MONTH)
                this.mth = cal.get(Calendar.MONTH)
                this.yr = cal.get(Calendar.YEAR)
            }catch (e: ParseException){
                Log.e("CalenderDialogue", "Exception")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cld_view)
        cald.init(this.yr, this.mth, this.day, null)
        submit.setOnClickListener {
            val dat: String = cald.getYear()
                .toString() + "-" + (cald.getMonth() + 1).toString() + "-" + cald.getDayOfMonth()
                .toString()
            calenderInterface.getDate(dat, text)
            dismiss()
        }
    }
}