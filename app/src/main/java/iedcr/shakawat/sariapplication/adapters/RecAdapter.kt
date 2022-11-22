package iedcr.shakawat.sariapplication.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import iedcr.shakawat.sariapplication.ListActivity
import iedcr.shakawat.sariapplication.MainActivity
import iedcr.shakawat.sariapplication.R
import iedcr.shakawat.sariapplication.models.Info
import iedcr.shakawat.sariapplication.models.List
import java.util.*

class RecAdapter : RecyclerView.Adapter<RecAdapter.MyViewHolder>{
    var context: Context
    var listActivity: ListActivity
    var listArrayList: ArrayList<List>
    var recyclerView: RecyclerView

    constructor(
        ctx: Context, listActivity: ListActivity, listArrayList: ArrayList<List>,
        recyclerView: RecyclerView
    ){
        this.context = ctx
        this.listActivity = listActivity
        this.listArrayList = listArrayList
        this.recyclerView = recyclerView
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.name)
        val age : TextView = itemView.findViewById(R.id.age)
        val sex : TextView = itemView.findViewById(R.id.sex)
        val mob : TextView = itemView.findViewById(R.id.mob)
        val dis : TextView = itemView.findViewById(R.id.dis)
        val samDT : TextView = itemView.findViewById(R.id.samDT)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewHolder = LayoutInflater.from(this.context).inflate(
            R.layout.custom_list,
            parent, false
        )
        viewHolder.setOnClickListener {
            val position:Int = this.recyclerView.getChildAdapterPosition(it)
            val list: List = this.listArrayList.get(position)
            Info.id = list.id
            Info.pt_id = list.pt_id
            Info.ward_no = list.ward_no
            Info.bed_no = list.bed_no
            Info.dept = list.dept
            Info.da = list.da
            Info.scd = list.scd
            Info.name = list.name
            Info.gen = list.gen
            Info.age = list.age
            Info.vil = list.vil
            Info.un = list.un
            Info.upz = list.upz
            Info.dis = list.dis
            Info.mob = list.mob
            Info.is_hck = list.is_hck
            Info.is_cpw = list.is_cpw
            Info.is_bpr = list.is_bpr
            Info.is_lbmw = list.is_lbmw
            Info.has_th = list.has_th
            Info.th_place = list.th_place
            Info.asthma = list.asthma
            Info.diabetes = list.diabetes
            Info.crd = list.crd
            Info.ccd = list.ccd
            Info.cld = list.cld
            Info.crnd = list.crnd
            Info.cnd = list.cnd
            Info.chd = list.chd
            Info.preg = list.preg
            Info.cancer = list.cancer
            Info.oth_med_his = list.oth_med_his
            Info.onset_ill = list.onset_ill
            Info.temp = list.temp
            Info.sore_throat = list.sore_throat
            Info.run_nose = list.run_nose
            Info.diff_breath = list.diff_breath
            Info.headache = list.headache
            Info.bodyache = list.bodyache
            Info.diarrhoea = list.diarrhoea
            Info.symp_oth = list.symp_oth
            Info.symp_oth_txt = list.symp_oth_txt
            Info.convul = list.convul
            Info.lathergy = list.lathergy
            Info.udrink = list.udrink
            Info.vomit = list.vomit
            Info.stridor = list.stridor
            Info.chest = list.chest
            Info.pulse = list.pulse
            Info.temp = list.temp
            Info.sys = list.sys
            Info.dbp = list.dbp
            Info.rr = list.rr
            Info.cyn = list.cyn
            Info.rhonchi = list.rhonchi
            Info.crep = list.crep
            Info.xray = list.xray
            Info.xray_txt = list.xray_txt
            Info.ts = list.ts
            Info.ns = list.ns
            Info.nasao = list.nasao
            Info.sputum = list.sputum
            Info.spec_oth = list.spec_oth
            Info.spec_oth_txt = list.spec_oth_txt
            this.context.startActivity(Intent(context, MainActivity::class.java))
        }
        return MyViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list: List = this.listArrayList.get(position)
        holder.name.setText(list.name)
        holder.age.setText(list.age)
        holder.mob.setText(list.mob)
        if (list.gen==1)
            holder.sex.setText("Sex: M")
        else
            holder.sex.setText("Sex: F")
        holder.dis.setText("District: " + list.dis)
        holder.samDT.setText(list.scd)
    }

    override fun getItemCount(): Int {
        return this.listArrayList.size
    }
}