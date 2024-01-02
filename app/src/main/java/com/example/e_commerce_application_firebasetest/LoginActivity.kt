package com.example.e_commerce_application_firebasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private lateinit var edtEmail:EditText
    private lateinit var edtPassword:EditText
    private lateinit var btnLogin:Button
    private lateinit var btnSignup:Button
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)

        btnLogin = findViewById(R.id.btn_login)
        btnSignup = findViewById(R.id.btn_sign_up)

        mAuth = Firebase.auth

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            loginIntoAccount(email,password)
        }
        btnSignup.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            signUpIntoAccount(email,password)
        }

    }

    private fun loginIntoAccount(email:String,password:String){
        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task->
                if(task.isSuccessful){
                    startActivity(Intent(this,UploadProductActivity::class.java))
                    Toast.makeText(this,"Login SuccessFul",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Some error Occured",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signUpIntoAccount(email: String,password: String){
        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    startActivity(Intent(this,UploadProductActivity::class.java))
                    Toast.makeText(this,"Sign up SuccessFul",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Some Error Occured",Toast.LENGTH_SHORT).show()
                }
            }
    }

}