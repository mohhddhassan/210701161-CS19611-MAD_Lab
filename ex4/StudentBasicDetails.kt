package com.example.app4

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app4.databinding.ActivityMainBinding
import com.example.app4.databinding.FragmentStudentBasicDetailsBinding
import com.example.app4.R


class StudentBasicDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_basic_details, container, false)
    }

}