package iedcr.shakawat.sariapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import iedcr.shakawat.sariapplication.fragments.CalenderDialog
import iedcr.shakawat.sariapplication.interfaces.CalenderInterface
import iedcr.shakawat.sariapplication.models.Info
import iedcr.shakawat.sariapplication.models.User
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity(), CalenderInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hos_id.text = User.user.toString()
        init()
    }
    fun init(){
        da.setOnClickListener { CalenderDialog(
            this, da.text.toString(),
            "Date of Admission", da, this@MainActivity
        ).show() }
        scd.setOnClickListener { CalenderDialog(
            this, scd.text.toString(),
            "Date of SCD", scd, this@MainActivity
        ).show() }
        onset_ill.setOnClickListener { CalenderDialog(
            this, onset_ill.text.toString(),
            "Date of Onset of ill", onset_ill, this@MainActivity
        ).show() }
        age.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d("Main_age", s.toString())
                try {
                    var x: Double = s.toString().toDouble()
                    if (x > 4.99) {
                        un5label.visibility = View.GONE
                        un5r1.visibility = View.GONE
                        un5r2.visibility = View.GONE
                        un5r3.visibility = View.GONE
                    } else {
                        un5label.visibility = View.VISIBLE
                        un5r1.visibility = View.VISIBLE
                        un5r2.visibility = View.VISIBLE
                        un5r3.visibility = View.VISIBLE
                        preg.visibility = View.GONE
                    }
                } catch (ex: Exception) {
                    Log.e("Main_ageEx", ex.message.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        has_th.setOnCheckedChangeListener(cclistener)
        mh_oth.setOnCheckedChangeListener(cclistener)
        symp_oth.setOnCheckedChangeListener(cclistener)
        xray.setOnCheckedChangeListener(cclistener)
        spec_oth.setOnCheckedChangeListener(cclistener)
        submit.setOnClickListener {
            if(check()){
                setdata()
            }
        }
        initData()
    }
    fun initData(){
        pat_id.setText(Info.pt_id)
        ward_no.setText(Info.ward_no)
        bed_no.setText(Info.bed_no)
        if(Info.dept==1) dept.check(R.id.dept_med)
        else if(Info.dept==2) dept.check(R.id.dept_paed)
        scd.setText(Info.scd)
        da.setText(Info.da)
        name.setText(Info.name)
        if(Info.gen==1) gen.check(R.id.gen_male)
        else if(Info.gen==2) gen.check(R.id.gen_female)
        age.setText(Info.age)
        vil.setText(Info.vil)
        un.setText(Info.un)
        upz.setText(Info.upz)
        dis.setText(Info.dis)
        mob.setText(Info.mob)
        if(Info.is_hck==1) hcw.isChecked = true
        if(Info.is_cpw==1) cpw.isChecked = true
        if(Info.is_bpr==1) bpr.isChecked = true
        if(Info.is_lbmw==1) lbmw.isChecked = true
        if(Info.has_th==1){
            has_th.isChecked==true
            th_place_layout.visibility = View.VISIBLE
        }
        th_place.setText(Info.th_place)
        if(Info.asthma==1) asthma.isChecked = true
        if(Info.diabetes==1) diabates.isChecked = true
        if(Info.crd==1) crd.isChecked=true
        if(Info.crd==1) crd.isChecked=true
        if(Info.ccd==1) ccd.isChecked=true
        if(Info.cld==1) cld.isChecked=true
        if(Info.crnd==1) crnd.isChecked=true
        if(Info.cnd==1) cnd.isChecked=true
        if(Info.chd==1) chd.isChecked=true
        if(Info.preg==1) preg.isChecked=true
        if(Info.cancer==1) cancer.isChecked=true
        if(Info.oth_med_his.length>3){
            mh_oth_txt_layout.visibility = View.VISIBLE
            mh_oth_txt.setText(Info.oth_med_his)
            mh_oth.isChecked = true
        }
        onset_ill.setText(Info.onset_ill)
        if(Info.sore_throat==1) sore_throat.isChecked=true
        if(Info.run_nose==1) run_nose.isChecked=true
        if(Info.diff_breath==1) diff_breath.isChecked=true
        if(Info.headache==1) headache.isChecked=true
        if(Info.bodyache==1) bodyache.isChecked=true
        if(Info.diarrhoea==1) diar.isChecked=true
        if(Info.symp_oth==1) {
            symp_oth.isChecked=true
            symp_oth_txt.visibility = View.VISIBLE
        }
        symp_oth_txt.setText(Info.symp_oth_txt)
        if(Info.convul==1) convul.isChecked=true
        if(Info.lathergy==1) lathergy.isChecked=true
        if(Info.udrink==1) udrink.isChecked=true
        if(Info.vomit==1) vomit.isChecked=true
        if(Info.stridor==1) stridor.isChecked=true
        if(Info.chest==1) chest.isChecked=true
        pulse.setText(Info.pulse)
        temp.setText(Info.temp)
        sys.setText(Info.sys)
        dbp.setText(Info.dbp)

        rr.setText(Info.rr.toString())
        if(Info.cyn==1) cyn.isChecked=true
        if(Info.rhonchi==1) rhonchi.isChecked=true
        if(Info.crep==1) crep.isChecked=true
        if(Info.xray==1) {
            xray.isChecked=true
            xray_txt.visibility = View.GONE
        }
        xray_txt.setText(Info.xray_txt)
        if(Info.ts==1) ts.isChecked=true
        if(Info.ns==1) ns.isChecked=true
        if(Info.nasao==1) nasao.isChecked=true
        if(Info.sputum==1) sputum.isChecked=true
        if(Info.spec_oth==1) {
            spec_oth.isChecked=true
            spec_oth_txt.visibility = View.VISIBLE
        }
        spec_oth_txt.setText(Info.spec_oth_txt)
    }
    public val cclistener = object : CompoundButton.OnCheckedChangeListener{
        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
            if (p0 != null) {
                if (p0.id == has_th.id){
                    if (p1) th_place_layout.visibility = View.VISIBLE
                    else{ th_place_layout.visibility = View.GONE
                        th_place.text.clear()
                    }
                }else if (p0.id == mh_oth.id){
                    if (p1) mh_oth_txt_layout.visibility = View.VISIBLE
                    else {mh_oth_txt_layout.visibility = View.GONE
                        mh_oth_txt.text.clear()
                    }
                }else if (p0.id == symp_oth.id){
                    if (p1) symp_oth_txt.visibility = View.VISIBLE
                    else {symp_oth_txt.visibility = View.GONE
//                        symp_oth_txt.text = getString(R.string.empty)
                        symp_oth_txt.text.clear()
                    }
                }else if (p0.id == xray.id){
                    if (p1) xray_txt.visibility = View.GONE
                    else { xray_txt.visibility = View.VISIBLE
//                        xray_txt.text = getString(R.string.empty)
                        xray_txt.text.clear()
                    }
                }else if (p0.id == spec_oth.id){
                    if (p1) spec_oth_txt.visibility = View.VISIBLE
                    else {spec_oth_txt.visibility = View.GONE
//                        spec_oth_txt.text = getString(R.string.empty)
                        spec_oth_txt.text.clear()
                    }
                }
            }
        }
    }
    private fun check()
    : Boolean {

        return if (pat_id.text.toString().length != 5) {
            Toast.makeText(this@MainActivity, "Check Patient ID", Toast.LENGTH_SHORT).show()
            false
        } else if (scd.text.toString().isEmpty()) {
            Toast.makeText(this@MainActivity, "Check sample collection date", Toast.LENGTH_SHORT)
                .show()
            false
        } else if (dept.checkedRadioButtonId == -1) {
            Toast.makeText(this@MainActivity, "Check department", Toast.LENGTH_SHORT).show()
            false
        } else if (name.text.toString().isEmpty()) {
            Toast.makeText(this@MainActivity, "Check Name", Toast.LENGTH_SHORT).show()
            false
        } else if (gen.checkedRadioButtonId == -1) {
            Toast.makeText(this@MainActivity, "Check Gender", Toast.LENGTH_SHORT).show()
            false
        } else if (age.text.toString().isEmpty()) {
            Toast.makeText(this@MainActivity, "Check age", Toast.LENGTH_SHORT).show()
            false
        } else if (mob.text.toString().length != 11) {
            Toast.makeText(this@MainActivity, "Check mobile number", Toast.LENGTH_SHORT).show()
            false
        } else if (has_th.isChecked && th_place.text.toString().isEmpty()) {
            Toast.makeText(this@MainActivity, "Insert Travel history", Toast.LENGTH_SHORT).show()
            false
        } else if (mh_oth.isChecked && mh_oth_txt.text.toString().isEmpty()) {
            Toast.makeText(this@MainActivity, "Insert other medical history", Toast.LENGTH_SHORT)
                .show()
            false
        } else if (gen.checkedRadioButtonId == R.id.gen_male && preg.isChecked) {
            Toast.makeText(this@MainActivity, "How pregnant", Toast.LENGTH_SHORT).show()
            false
        } else if (onset_ill.text.toString().isEmpty()) {
            Toast.makeText(this@MainActivity, "Check onset of illness", Toast.LENGTH_SHORT).show()
            false
        } else if (temp.text.toString().isEmpty() || temp.text.toString().toDouble() < 97.00) {
            Toast.makeText(this@MainActivity, "Check temparature", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }
    private fun setdata(){
        val queue = Volley.newRequestQueue(this@MainActivity)
        val url = "http://119.148.17.100:8080/influenza/insert_sari.php"
        val strReq: StringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                Log.e("MainResponse", "response: " + response)

                // Handle Server response here
                try {
                    val responseObj = JSONObject(response)
                    val success = responseObj.getInt("success")
                    val message = responseObj.getString("message")
                    if (success == 1) {
                        finish()
                        startActivity(Intent(this@MainActivity, ListActivity::class.java))
                    }
                } catch (e: Exception) { // caught while parsing the response
                    Log.e("MainResponse", "problem occurred")
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { volleyError -> // error occurred
                Log.e("MainResponse", "problem occurred, volley error: " + volleyError.message)
            }) {

            override fun getParams(): MutableMap<String, String> {
                val parameters: MutableMap<String, String> = HashMap()
                parameters.put("id", Info.id.toString())
                parameters.put("hos_id", hos_id.text.toString())
                parameters.put("pt_id", pat_id.text.toString())
                parameters.put("ward_no", ward_no.text.toString())
                parameters.put("bed_no", bed_no.text.toString())
                parameters.put(
                    "dept", if (dept.checkedRadioButtonId == dept_med.id) "1"
                    else if (dept.checkedRadioButtonId == dept_paed.id) "2" else ""
                )
                parameters.put("da", da.text.toString())
                parameters.put("scd", scd.text.toString())
                parameters.put("name", name.text.toString())
                parameters.put(
                    "gen", if (gen.checkedRadioButtonId == gen_male.id) "1"
                    else if (gen.checkedRadioButtonId == gen_female.id) "2" else ""
                )
                parameters.put("age", age.text.toString())
                parameters.put("vil", vil.text.toString())
                parameters.put("un", un.text.toString())
                parameters.put("upz", upz.text.toString())
                parameters.put("dis", dis.text.toString())
                parameters.put("mob", mob.text.toString())
                parameters.put("is_hck", if (hcw.isChecked()) "1" else "0")
                parameters.put("is_cpw", if (cpw.isChecked()) "1" else "0")
                parameters.put("is_bpr", if (bpr.isChecked()) "1" else "0")
                parameters.put("is_lbmw", if (lbmw.isChecked()) "1" else "0")
                parameters.put("has_th", if (has_th.isChecked()) "1" else "0")
                parameters.put("th_place", th_place.text.toString())
                parameters.put("asthma", if (asthma.isChecked()) "1" else "0")
                parameters.put("diabetes", if (diabates.isChecked()) "1" else "0")
                parameters.put("crd", if (crd.isChecked()) "1" else "0")
                parameters.put("ccd", if (ccd.isChecked()) "1" else "0")
                parameters.put("cld", if (cld.isChecked()) "1" else "0")
                parameters.put("crnd", if (crnd.isChecked()) "1" else "0")
                parameters.put("cnd", if (cnd.isChecked()) "1" else "0")
                parameters.put("chd", if (chd.isChecked()) "1" else "0")
                parameters.put("preg", if (preg.isChecked()) "1" else "0")
                parameters.put("cancer", if (cancer.isChecked()) "1" else "0")
                parameters.put("oth_med_his", mh_oth_txt.text.toString())
                parameters.put("onset_ill", onset_ill.text.toString())
                parameters.put("sore_throat", if (sore_throat.isChecked()) "1" else "0")
                parameters.put("run_nose", if (run_nose.isChecked()) "1" else "0")
                parameters.put("diff_breath", if (diff_breath.isChecked()) "1" else "0")
                parameters.put("headache", if (headache.isChecked()) "1" else "0")
                parameters.put("bodyache", if (bodyache.isChecked()) "1" else "0")
                parameters.put("diarrhoea", if (diar.isChecked()) "1" else "0")
                parameters.put("symp_oth", if (symp_oth.isChecked()) "1" else "0")
                parameters.put("symp_oth_txt", symp_oth_txt.text.toString())
                parameters.put("convul", if (convul.isChecked()) "1" else "0")
                parameters.put("lathergy", if (lathergy.isChecked()) "1" else "0")
                parameters.put("udrink", if (udrink.isChecked()) "1" else "0")
                parameters.put("vomit", if (vomit.isChecked()) "1" else "0")
                parameters.put("stridor", if (stridor.isChecked()) "1" else "0")
                parameters.put("chest", if (chest.isChecked()) "1" else "0")
                parameters.put("pulse", pulse.text.toString())
                parameters.put("temp", temp.text.toString())
                parameters.put("sys", sys.text.toString())
                parameters.put("dbp", dbp.text.toString())
                parameters.put("rr", rr.text.toString())
                parameters.put("cyn", if (cyn.isChecked()) "1" else "0")
                parameters.put("rhonchi", if (rhonchi.isChecked()) "1" else "0")
                parameters.put("crep", if (crep.isChecked()) "1" else "0")
                parameters.put("xray", if (xray.isChecked()) "1" else "0")
                parameters.put("xray_txt", xray_txt.text.toString())
                parameters.put("ts", if (ts.isChecked()) "1" else "0")
                parameters.put("ns", if (ns.isChecked()) "1" else "0")
                parameters.put("nasao", if (nasao.isChecked()) "1" else "0")
                parameters.put("sputum", if (sputum.isChecked()) "1" else "0")
                parameters.put("spec_oth", if (spec_oth.isChecked()) "1" else "0")
                parameters.put("spec_oth_txt", spec_oth_txt.text.toString())
                parameters.put("user_id", User.user.toString())
                return parameters
            }

        }

// Add the request to the RequestQueue.
        queue.add(strReq)
    }

    override fun getDate(dat: String, text: TextView){
        text.setText(dat)
    }
}
