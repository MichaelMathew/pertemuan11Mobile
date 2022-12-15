package com.michael.pertemuan11.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.michael.pertemuan11.R
import com.michael.pertemuan11.databinding.StudentItemBinding
import com.michael.pertemuan11.entity.Student

class StudentAdapter(val students: ArrayList<Student>) : Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : ViewHolder(itemView) {
        private lateinit var binding: StudentItemBinding
        init {
            binding = StudentItemBinding.bind(itemView)
        }
        fun setStudentData(student: Student){
            binding.tvId.text = student.id
            binding.tvName.text = "${student.firstName} ${student.lastName}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item,parent,false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.setStudentData(students[position])
    }

    override fun getItemCount(): Int {
        return students.size
    }
}