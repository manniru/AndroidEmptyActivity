package com.mannir.emptyactivity

//import android.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import java.util.ArrayList
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener





class RecyclerViewActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var callsList: MutableList<Call>? = null
    private var callsAdapter: CallsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        // Set Layout Manager
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = linearLayoutManager

        // Limiting the size
        recyclerView!!.setHasFixedSize(true)

        // Initialize list items
        init()

        // Firebase
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        myRef.setValue("Hello World form Android Studio")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)
                Log.d("13131", "Value is: " + value!!)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("13131", "Failed to read value.", error.toException())
            }
        })

    }

    private fun init() {
        callsList = ArrayList()

        // Initiating Adapter
        callsAdapter = CallsAdapter(this@RecyclerViewActivity)
        recyclerView!!.adapter = callsAdapter

        // Adding some demo data(Call Objects).
        // You can get them from your data server
        callsList!!.add(Call("John", "9:30 AM"))
        callsList!!.add(Call("Rob", "9:40 AM"))
        callsList!!.add(Call("Peter", "9:45 AM"))
        callsList!!.add(Call("Jack", "9:50 AM"))
        callsList!!.add(Call("Bob", "9:55 AM"))
        callsList!!.add(Call("Sandy", "10:00 AM"))
        callsList!!.add(Call("Kate", "10:05 AM"))
        callsList!!.add(Call("Daniel", "10:10 AM"))
        callsList!!.add(Call("Roger", "10:15 AM"))
        callsList!!.add(Call("Sid", "10:20 AM"))
        callsList!!.add(Call("Kora", "10:25 AM"))
        callsList!!.add(Call("Nick", "10:30 AM"))
        callsList!!.add(Call("Rose", "10:35 AM"))
        callsList!!.add(Call("Mia", "10:40 AM"))
        callsList!!.add(Call("Scott", "10:45 AM"))

        // Set items to adapter
        callsAdapter!!.setCallsFeed(callsList!!)
        callsAdapter!!.notifyDataSetChanged()
    }
}