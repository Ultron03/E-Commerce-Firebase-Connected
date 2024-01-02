package com.example.e_commerce_application_firebasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.EventListener

class MainActivity : AppCompatActivity() {
    private lateinit var rv:RecyclerView
    private lateinit var procductAdapter:ProductAdapter
    private lateinit var fab:FloatingActionButton
    var listOfProduct = mutableListOf<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById(R.id.fab)
        rv = findViewById(R.id.rv)
        FirebaseDatabase.getInstance().getReference("Product")
            .addValueEventListener(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    listOfProduct.clear()
                    for(dataSnapShot in snapshot.children){
                        val product = dataSnapShot.getValue(Product::class.java)
                        listOfProduct.add(product!!)
                    }
                    procductAdapter = ProductAdapter(listOfProduct,this@MainActivity)
                    rv.adapter = procductAdapter
                    rv.layoutManager = GridLayoutManager(this@MainActivity,2)

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })




        fab.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }
}