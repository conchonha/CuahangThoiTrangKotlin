package com.example.myapplication.Adapter

import android.content.Context
import android.view.View
import com.example.myapplication.Model.Taikhoan
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.Adapter_Quanlytaikhoan.Viewhlder
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.example.myapplication.Service.APIServices
import android.widget.TextView
import com.example.myapplication.R
import java.util.ArrayList

class Adapter_Quanlytaikhoan(
    private val context: Context,
    private val layout: Int,
    private val arrayList: ArrayList<Taikhoan>
) : RecyclerView.Adapter<Viewhlder>() {
    private lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewhlder {
        view = View.inflate(context, layout, null)
        return Viewhlder(view)
    }

    override fun onBindViewHolder(holder: Viewhlder, position: Int) {
        val taikhoan = arrayList[position]
        holder.txtdiachiadmin.text = taikhoan.diaChi
        holder.txtemailadmin.text = taikhoan.email
        holder.txtmakhachhangadmin.text = taikhoan.idUser.toString() + ""
        holder.txtpasswordadmin.text = taikhoan.password
        holder.txtsodienthoaiadmin.text = taikhoan.soDienThoai
        holder.txttentaikhoanadmin.text = taikhoan.username
        if (taikhoan.hinhanh?.endsWith("jpg") == true) {
            Picasso.with(context).load(APIServices.urlhinh + taikhoan.hinhanh)
                .into(holder.imghinhanhtaikhoanadmin)
        } else {
            Picasso.with(context).load(taikhoan.hinhanh).into(holder.imghinhanhtaikhoanadmin)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class Viewhlder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtmakhachhangadmin: TextView
        val txttentaikhoanadmin: TextView
        val txtpasswordadmin: TextView
        val txtsodienthoaiadmin: TextView
        val txtemailadmin: TextView
        val txtdiachiadmin: TextView
        val imghinhanhtaikhoanadmin: ImageView

        init {
            txtmakhachhangadmin = itemView.findViewById(R.id.txtmakhachhangadmin)
            imghinhanhtaikhoanadmin = itemView.findViewById(R.id.imghinhanhtaikhoanadmin)
            txttentaikhoanadmin = itemView.findViewById(R.id.txttentaikhoanadmin)
            txtpasswordadmin = itemView.findViewById(R.id.txtpasswordadmin)
            txtsodienthoaiadmin = itemView.findViewById(R.id.txtsodienthoaiadmin)
            txtemailadmin = itemView.findViewById(R.id.txtemailadmin)
            txtdiachiadmin = itemView.findViewById(R.id.txtdiachiadmin)
        }
    }
}