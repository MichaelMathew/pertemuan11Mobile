package com.michael.pertemuan11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.michael.pertemuan11.databinding.ActivityAddBinding
import com.michael.pertemuan11.entity.Student

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val id = binding.etStudentId.text.toString()
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            if (!id.trim().isEmpty() && !firstName.trim().isEmpty() && !lastName.trim().isEmpty()){
                val student = Student()
                student.id = id
                student.firstName = firstName
                student.lastName = lastName
                saveFireBaseData(student)
                Toast.makeText(this@AddActivity,id + firstName + lastName , Toast.LENGTH_SHORT).show()
                binding.etStudentId.text.clear()
                binding.etFirstName.text.clear()
                binding.etLastName.text.clear()
            }
        }
    }

    private fun saveFireBaseData(student: Student){
        val database = Firebase.database("https://pertemuan11mobile-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val reference = database.getReference(MainActivity.STUDENT_REF)
        reference.push().setValue(student).addOnSuccessListener {
        }.addOnFailureListener {
            Toast.makeText(this@AddActivity, it.message, Toast.LENGTH_SHORT).show()
        }
//        val reference = student.id?.let { database.getReference(MainActivity.STUDENT_REF).child(it) }
//        reference?.setValue(student)
    }
}