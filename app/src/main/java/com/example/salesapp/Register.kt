package com.example.salesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tv_signup.setOnClickListener {

            if (et_password_reg.text.toString() == et_repassword_reg.text.toString()){
                var url = "http://192.168.1.3/PhpDemo/add_user.php?mobile=" + et_mobilenum_reg.text.toString() + "&password=" +
                        et_password_reg.text.toString() + "&name=" + et_name_reg.text.toString() + "&address=" + et_address_reg.text.toString()

                var request : RequestQueue = Volley.newRequestQueue(this)
                var stringRequest = StringRequest(Request.Method.GET,url,
                    { response ->
                        if (response == "0"){
                            Toast.makeText(this,"Mobile number is already used",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            UserInfo.mobile = et_mobilenum_reg.text.toString()
                            Toast.makeText(this,"New User Created",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,HomeActivity::class.java)
                            startActivity(intent)
                        }
                    }, { error ->
                        Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
                    })
                request.add(stringRequest)
            }
            else{
                Toast.makeText(this,"Password does not match.",Toast.LENGTH_SHORT).show()
            }

        }
    }
}