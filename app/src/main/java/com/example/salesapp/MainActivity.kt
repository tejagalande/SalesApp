package com.example.salesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_login.setOnClickListener {


            var url =
                "http://192.168.1.3/PhpDemo/login.php?mobile=" + et_mobilenum.text.toString() + "&password=" +
                        et_password.text.toString()

            val request: RequestQueue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    if (response == "0") {
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                    } else {
                        UserInfo.mobile = et_mobilenum.text.toString()
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    }
                }, { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                })
            request.add(stringRequest)
        }



        tv_register.setOnClickListener {
            var intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}
