package tarc.edu.my.coursestreet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.databinding.FragmentRegisterBinding
import tarc.edu.my.coursestreet.databinding.FragmentResultsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [resultsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class resultsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
//
//        // Spinner
//        val spinner1: Spinner = findViewById(R.id.spinner_qualification)
//        val spinner2: Spinner = findViewById(R.id.spinner_field_of_interest)
//        val spinner3: Spinner = findViewById(R.id.spinner_academic_1)
//        val spinner4: Spinner = findViewById(R.id.spinner_grade_1)
//        val spinner5: Spinner = findViewById(R.id.spinner_academic_2)
//        val spinner6: Spinner = findViewById(R.id.spinner_grade_2)
//        val spinner7: Spinner = findViewById(R.id.spinner_academic_3)
//        val spinner8: Spinner = findViewById(R.id.spinner_grade_3)
//        val spinner9: Spinner = findViewById(R.id.spinner_academic_4)
//        val spinner10: Spinner = findViewById(R.id.spinner_grade_4)
//        val spinner11: Spinner = findViewById(R.id.spinner_academic_5)
//        val spinner12: Spinner = findViewById(R.id.spinner_grade_5)
//        val spinner13: Spinner = findViewById(R.id.spinner_academic_6)
//        val spinner14: Spinner = findViewById(R.id.spinner_grade_6)
//        val spinner15: Spinner = findViewById(R.id.spinner_academic_7)
//        val spinner16: Spinner = findViewById(R.id.spinner_grade_7)
//        val spinner17: Spinner = findViewById(R.id.spinner_academic_8)
//        val spinner18: Spinner = findViewById(R.id.spinner_grade_8)
//        val spinner19: Spinner = findViewById(R.id.spinner_academic_9)
//        val spinner20: Spinner = findViewById(R.id.spinner_grade_9)
//        val spinner21: Spinner = findViewById(R.id.spinner_academic_10)
//        val spinner22: Spinner = findViewById(R.id.spinner_grade_10)
//        val spinner23: Spinner = findViewById(R.id.spinner_academic_11)
//        val spinner24: Spinner = findViewById(R.id.spinner_grade_11)
//        val spinner25: Spinner = findViewById(R.id.spinner_academic_12)
//        val spinner26: Spinner = findViewById(R.id.spinner_grade_12)
//
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter.createFromResource(
//            this,
//            R.array.qualification_array,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            spinner1.adapter = adapter
//        }
//
//        ArrayAdapter.createFromResource(
//            this,
//            R.array.field_of_interest_array,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            spinner2.adapter = adapter
//        }
//
//        // academic
//        // subjects
//        ArrayAdapter.createFromResource(
//            this,
//            R.array.subjects_array,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            spinner3.adapter = adapter
//            spinner5.adapter = adapter
//            spinner7.adapter = adapter
//            spinner9.adapter = adapter
//            spinner11.adapter = adapter
//            spinner13.adapter = adapter
//            spinner15.adapter = adapter
//            spinner17.adapter = adapter
//            spinner19.adapter = adapter
//            spinner21.adapter = adapter
//            spinner23.adapter = adapter
//            spinner25.adapter = adapter
//        }
//
//        // grade
//        ArrayAdapter.createFromResource(
//            this,
//            R.array.grades_array,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            spinner4.adapter = adapter
//            spinner6.adapter = adapter
//            spinner8.adapter = adapter
//            spinner10.adapter = adapter
//            spinner12.adapter = adapter
//            spinner14.adapter = adapter
//            spinner16.adapter = adapter
//            spinner18.adapter = adapter
//            spinner20.adapter = adapter
//            spinner22.adapter = adapter
//            spinner24.adapter = adapter
//            spinner26.adapter = adapter
//        }
//
//        val submitButton : Button = findViewById(R.id.button_submit)
//
//        submitButton.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                //your implementation goes here
//                var qualification = spinner1.selectedItemId.toString()
//                var fieldOfInterest = spinner2.selectedItemId.toString()
//                var academic1 = spinner3.selectedItemId.toString()
//                var grade1 = spinner4.selectedItemId.toString()
//
////                Toast.makeText(getApplicationContext(),
////                    qualification + "" + fieldOfInterest + "" + academic1 + "" + grade1,
////                    Toast.LENGTH_LONG)
////                    .show();
//
//                val text = "Hello toast!"
//                val duration = Toast.LENGTH_SHORT
//                val toast = Toast.makeText(applicationContext, text, duration)
//                toast.show()
////                Toast.makeText(context, text, duration).show()
////                Toast.makeText(getApplicationContext(),
////                    "This a toast message",
////                    Toast.LENGTH_LONG)
////                    .show();
//            }
//        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment resultsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            resultsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}