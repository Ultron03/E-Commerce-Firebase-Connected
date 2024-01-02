package com.example.e_commerce_application_firebasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class UploadProductActivity : AppCompatActivity() {
    private lateinit var img:ImageView
    private lateinit var edtProductName:EditText
    private lateinit var edtProductPrice: EditText
    private lateinit var edtProductDes:EditText
    private lateinit var btnSelectImage:Button
    private lateinit var btnUploadProduct:Button
    private lateinit var progressBar:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_product)

        img = findViewById(R.id.productimg)
        edtProductName = findViewById(R.id.edt_product_name)
        edtProductPrice = findViewById(R.id.edt_productPrice)
        edtProductDes = findViewById(R.id.edt_product_des)
        btnSelectImage = findViewById(R.id.btn_select_product)
        btnUploadProduct = findViewById(R.id.btn_upload_product)
        progressBar = findViewById(R.id.progress_bar)

        btnSelectImage.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent,101)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101 && resultCode == RESULT_OK){
            val uri= data?.data
            img.setImageURI(uri)
            btnUploadProduct.setOnClickListener {

                progressBar.visibility = View.VISIBLE
                val productName = edtProductName.text.toString()
                val productPrice = edtProductPrice.text.toString()
                val productDes = edtProductDes.text.toString()
                val fileName = UUID.randomUUID().toString()+".jpg"
                val storage = FirebaseStorage.getInstance().reference.child("producctImage/${fileName}")
                storage.putFile(uri!!).addOnSuccessListener {
                    val result = it.metadata?.reference?.downloadUrl
                    result?.addOnSuccessListener {
                        uploadProduct(
                            productName,
                            productDes,
                            productPrice,
                            it.toString()
                        )
                    }

                    Log.i("Images123",it.toString())
                }
            }
        }
    }

    private fun uploadProduct(name:String,description:String,price:String,image:String){
        Firebase.database.getReference("Product").child(name).setValue(
            Product(
                product_image = image,
                product_name = name,
                product_price = price,
                product_des = description
            )

        ).addOnSuccessListener {
            progressBar.visibility = View.GONE
            Toast.makeText(this,"Product Add Successfully ${image}",Toast.LENGTH_LONG).show()
        }
    }


}