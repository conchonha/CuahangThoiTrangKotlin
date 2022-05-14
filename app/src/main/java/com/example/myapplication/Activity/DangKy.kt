package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import com.example.myapplication.R
import android.widget.Toast
import com.example.myapplication.Service.DataService
import com.example.myapplication.Service.APIServices
import android.content.Intent
import android.util.Log
import android.widget.Button
import com.example.myapplication.Activity.DangNhap
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DangKy : AppCompatActivity() {
    private var text_UserName: EditText? = null
    private var text_PassWord: EditText? = null
    private var text_PhoneNumBer: EditText? = null
    private var text_Email: EditText? = null
    private var text_Adress: EditText? = null
    private var btndangky: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_ky)
        anhxa()
        btndangky?.setOnClickListener {
            if (text_UserName?.text.toString() == "" || text_PassWord?.text.toString() == "" || text_PhoneNumBer?.text.toString() == "" || text_Email?.text.toString() == "" || text_Adress?.text.toString() == "") {
                Toast.makeText(this@DangKy, "Không được để trống dữ liệu", Toast.LENGTH_SHORT)
                    .show()
            } else if (!text_Email?.text.toString().endsWith("@gmail.com")) {
                Toast.makeText(this@DangKy, "Sai email", Toast.LENGTH_SHORT).show()
            } else if (text_PhoneNumBer?.text.toString().length != 10) {
                Toast.makeText(this@DangKy, "Sai sdt", Toast.LENGTH_SHORT).show()
            } else {
                val dataService = APIServices.getService()
                val callback = dataService.dangkytaikhoan(
                    text_UserName?.text.toString(),
                    text_PassWord?.text.toString(),
                    text_PhoneNumBer?.text.toString(),
                    text_Email?.text.toString(),
                    text_Adress?.text.toString()
                )
                callback.enqueue(object : Callback<String?> {
                    override fun onResponse(call: Call<String?>, response: Response<String?>) {
                        Log.d("AAA", "Create Tai Khoan: $response")
                        if (response.isSuccessful) {
                            Toast.makeText(this@DangKy, "Đăng Ký Thành Công", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(applicationContext, DangNhap::class.java))
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<String?>, t: Throwable) {}
                })
            }
        }
    }

    private fun anhxa() {
        text_UserName = findViewById(R.id.text_UserName)
        text_PassWord = findViewById(R.id.text_PassWord)
        text_PhoneNumBer = findViewById(R.id.text_PhoneNumBer)
        text_Email = findViewById(R.id.text_Email)
        text_Adress = findViewById(R.id.text_Adress)
        btndangky = findViewById(R.id.btndangky)
    }
}