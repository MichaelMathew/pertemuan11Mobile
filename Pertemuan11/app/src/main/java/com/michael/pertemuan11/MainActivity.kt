package com.michael.pertemuan11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.michael.pertemuan11.adapter.StudentAdapter
import com.michael.pertemuan11.databinding.ActivityMainBinding
import com.michael.pertemuan11.databinding.StudentItemBinding
import com.michael.pertemuan11.entity.Student

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var students: ArrayList<Student>
    private lateinit var studentAdapter: StudentAdapter

    companion object{
        const val STUDENT_REF = "student"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        students = ArrayList()
        studentAdapter = StudentAdapter(students)
        binding.rvData.adapter = studentAdapter
        binding.rvData.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this@MainActivity,AddActivity::class.java)
            startActivity(intent)
        }
        fetchFirebaseData()
    }

    private fun fetchFirebaseData(){
        val database = Firebase.database("https://pertemuan11mobile-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val reference = database.getReference(STUDENT_REF)
        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                students.clear()
                for(item: DataSnapshot in snapshot.children){
                    item.getValue(Student::class.java)?.let {
                        students.add(it)
                    }
                }
                studentAdapter.notifyItemChanged(0)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}