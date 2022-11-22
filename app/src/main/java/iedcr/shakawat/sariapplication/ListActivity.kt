package iedcr.shakawat.sariapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import iedcr.shakawat.sariapplication.adapters.RecAdapter
import iedcr.shakawat.sariapplication.models.Info
import iedcr.shakawat.sariapplication.models.List
import iedcr.shakawat.sariapplication.models.User
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class ListActivity : AppCompatActivity() {

    var pBar : ProgressBar? = null
    var eList : ArrayList<List>? = null
    var rec : RecyclerView? = null
    var linearLayoutManager: LinearLayoutManager?=null
    var recAdapter: RecAdapter?=null
    val url = "http://119.148.17.100:8080/influenza/get_sari.php?user_id="+
            User.user.toString()
    var loading : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        fab.setOnClickListener {
            Log.d("List","Fab clicked");
            Info.clear()
            startActivity(Intent(this@ListActivity,MainActivity::class.java))
        }
        pBar =findViewById<View>(R.id.pBar) as ProgressBar
        eList = ArrayList()

        getData()
        rec = findViewById(R.id.rec) as RecyclerView
        linearLayoutManager = LinearLayoutManager(this@ListActivity)
        rec!!.layoutManager = linearLayoutManager
        recAdapter = RecAdapter(this@ListActivity,this@ListActivity,
            eList!!,rec!!)
        rec!!.adapter = recAdapter
        rec!!.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount: Int = linearLayoutManager!!.itemCount
                val lastVisibleItem: Int = linearLayoutManager!!.findLastVisibleItemPosition()
                if(!loading && totalItemCount<=(lastVisibleItem+10))
                    getData()
            }
        })
//        val scrollListener = object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//
//                val totalItemCount = rec!!.layoutManager!!.itemCount
//
//            }
//        }
    }
    fun getData(){
        pBar?.visibility = View.VISIBLE
        loading=true
        val queue = Volley.newRequestQueue(this@ListActivity)

        val strReq =  StringRequest(
            Request.Method.GET,url+"&ofset="+this.eList!!.size.toString(),
            Response.Listener { response ->
                pBar?.visibility = View.GONE
                loading=false
                Log.d("ListResponse", "response: " + response)

                // Handle Server response here
                try {
                    val responseObj = JSONObject(response)
                    val success = responseObj.getInt("success")
                    val message = responseObj.getString("msg")
                    if(success == 1){
                        val entries : JSONArray = responseObj.getJSONArray("entries")

                        var i = 0
                        while (i<entries.length()){
                            var list : List = List()
                            val jobj : JSONObject = entries.getJSONObject(i)
                            list.id = jobj.getInt("id")
                            list.hos_id = jobj.getString("hos_id")
                            list.pt_id = jobj.getString("pt_id")
                            list.ward_no = jobj.getString("ward_no")
                            list.bed_no = jobj.getString("bed_no")
                            list.dept = jobj.getInt("dept")
                            list.scd = jobj.getString("scd")
                            list.da = jobj.getString("da")
                            list.name = jobj.getString("name")
                            list.gen = jobj.getInt("gen")
                            list.age = jobj.getString("age")
                            list.vil = jobj.getString("vil")
                            list.un = jobj.getString("un")
                            list.upz = jobj.getString("upz")
                            list.dis = jobj.getString("dis")
                            list.mob = jobj.getString("mob")
                            list.is_hck = jobj.getInt("is_hck")
                            list.is_cpw = jobj.getInt("is_cpw")
                            list.is_bpr = jobj.getInt("is_bpr")
                            list.is_lbmw = jobj.getInt("is_lbmw")
                            list.has_th = jobj.getInt("has_th")
                            list.th_place = jobj.getString("th_place")
                            list.asthma = jobj.getInt("asthma")
                            list.diabetes = jobj.getInt("diabetes")
                            list.crd = jobj.getInt("crd")
                            list.ccd = jobj.getInt("ccd")
                            list.cld = jobj.getInt("cld")
                            list.crnd = jobj.getInt("crnd")
                            list.cnd = jobj.getInt("cnd")
                            list.chd = jobj.getInt("chd")
                            list.preg = jobj.getInt("preg")
                            list.cancer = jobj.getInt("cancer")
                            list.oth_med_his = jobj.getString("oth_med_his")
                            list.onset_ill = jobj.getString("onset_ill")
                            list.sore_throat = jobj.getInt("sore_throat")
                            list.run_nose = jobj.getInt("run_nose")
                            list.diff_breath = jobj.getInt("diff_breath")
                            list.headache = jobj.getInt("headache")
                            list.bodyache = jobj.getInt("bodyache")
                            list.diarrhoea = jobj.getInt("diarrhoea")
                            list.symp_oth = jobj.getInt("symp_oth")
                            list.symp_oth_txt = jobj.getString("symp_oth_txt")
                            list.convul = jobj.getInt("convul")
                            list.lathergy = jobj.getInt("lathergy")
                            list.udrink = jobj.getInt("udrink")
                            list.vomit = jobj.getInt("vomit")
                            list.stridor = jobj.getInt("stridor")
                            list.chest = jobj.getInt("chest")
                            list.pulse = jobj.getString("pulse")
                            list.temp = jobj.getString("temp")
                            list.sys = jobj.getString("sys")
                            list.dbp = jobj.getString("dbp")
                            list.rr = jobj.getInt("rr")
                            list.cyn = jobj.getInt("cyn")
                            list.rhonchi = jobj.getInt("rhonchi")
                            list.crep = jobj.getInt("crep")
                            list.xray = jobj.getInt("xray")
                            list.xray_txt = jobj.getString("xray_txt")
                            list.ts = jobj.getInt("ts")
                            list.ns = jobj.getInt("ns")
                            list.nasao = jobj.getInt("nasao")
                            list.sputum = jobj.getInt("sputum")
                            list.spec_oth = jobj.getInt("spec_oth")
                            list.spec_oth_txt = jobj.getString("spec_oth_txt")
                            eList?.add(list)
                            i++
                        }
                        this.recAdapter!!.notifyDataSetChanged()
                    }
                } catch (e: Exception) { // caught while parsing the response
                    Log.e("ListResponse", "problem occurred")
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { volleyError -> // error occurred
                pBar?.visibility = View.GONE
                loading=false
                Log.e("ListResponse", "problem occurred, volley error: " + volleyError.message)
            })
// Add the request to the RequestQueue.
        queue.add(strReq)
    }
    override fun onStop() {
        super.onStop()
        finish()
    }
}

