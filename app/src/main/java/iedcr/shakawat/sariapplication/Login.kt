package iedcr.shakawat.sariapplication

import android.content.ContextParams
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import iedcr.shakawat.sariapplication.models.User
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        submit.setOnClickListener(clickListener)
        if(User.user>0){
            startActivity(Intent(this@Login,ListActivity::class.java))
        }
    }

    val clickListener = View.OnClickListener { view ->
        when(view.id){
            R.id.submit ->{
                if(user_id.text.toString()=="" || pass.text.toString()==""){
                    Toast.makeText(applicationContext,"Check user id and password!",Toast.LENGTH_SHORT).show()
                }else{
                    if (User.check(user_id.text.toString(),pass.text.toString())){
                        startActivity(Intent(this@Login,ListActivity::class.java))
                    }else{
                        Toast.makeText(applicationContext,"User id or password mismatched!",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}