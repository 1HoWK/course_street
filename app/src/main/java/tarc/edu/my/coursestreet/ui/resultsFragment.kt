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
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.*
import tarc.edu.my.coursestreet.databinding.FragmentRegisterBinding
import tarc.edu.my.coursestreet.databinding.FragmentResultsBinding
import tarc.edu.my.coursestreet.util.cropToBlob
import tarc.edu.my.coursestreet.util.errorDialog
import tarc.edu.my.coursestreet.util.infoDialog

class resultsFragment : Fragment() {

    private lateinit var binding: FragmentResultsBinding
    private val auth: AuthViewModel by activityViewModels()
    private val nav by lazy { findNavController() }
    private val result: ResultsViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentResultsBinding.inflate(inflater, container, false)
        infoDialog("Please provide your study information to find suitable universities and courses")


        // Spinner
        val spinner1: Spinner = binding.spinnerQualification
        val spinner2: Spinner = binding.spinnerFieldOfInterest
        val spinner3: Spinner = binding.spinnerAcademic1
        val spinner4: Spinner = binding.spinnerGrade1
        val spinner5: Spinner = binding.spinnerAcademic2
        val spinner6: Spinner = binding.spinnerGrade2
        val spinner7: Spinner = binding.spinnerAcademic3
        val spinner8: Spinner = binding.spinnerGrade3
        val spinner9: Spinner = binding.spinnerAcademic4
        val spinner10: Spinner = binding.spinnerGrade4
        val spinner11: Spinner = binding.spinnerAcademic5
        val spinner12: Spinner = binding.spinnerGrade5
        val spinner13: Spinner = binding.spinnerAcademic6
        val spinner14: Spinner = binding.spinnerGrade6
        val spinner15: Spinner = binding.spinnerAcademic7
        val spinner16: Spinner = binding.spinnerGrade7
        val spinner17: Spinner = binding.spinnerAcademic8
        val spinner18: Spinner = binding.spinnerGrade8
        val spinner19: Spinner = binding.spinnerAcademic9
        val spinner20: Spinner = binding.spinnerGrade9
        val spinner21: Spinner = binding.spinnerAcademic10
        val spinner22: Spinner = binding.spinnerGrade10
        val spinner23: Spinner = binding.spinnerAcademic11
        val spinner24: Spinner = binding.spinnerGrade11
        val spinner25: Spinner = binding.spinnerAcademic12
        val spinner26: Spinner = binding.spinnerGrade12

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.qualification_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner1.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.field_of_interest_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner2.adapter = adapter
        }

        // academic
        // subjects
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.subjects_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner3.adapter = adapter
            spinner5.adapter = adapter
            spinner7.adapter = adapter
            spinner9.adapter = adapter
            spinner11.adapter = adapter
            spinner13.adapter = adapter
            spinner15.adapter = adapter
            spinner17.adapter = adapter
            spinner19.adapter = adapter
            spinner21.adapter = adapter
            spinner23.adapter = adapter
            spinner25.adapter = adapter
        }

        // grade
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.grades_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner4.adapter = adapter
            spinner6.adapter = adapter
            spinner8.adapter = adapter
            spinner10.adapter = adapter
            spinner12.adapter = adapter
            spinner14.adapter = adapter
            spinner16.adapter = adapter
            spinner18.adapter = adapter
            spinner20.adapter = adapter
            spinner22.adapter = adapter
            spinner24.adapter = adapter
            spinner26.adapter = adapter
        }

        binding.buttonSubmit.setOnClickListener{submit()}
        return binding.root
    }

    private fun submit(){

        var qualification = binding.spinnerQualification.selectedItem.toString()
        var fieldOfInterest = binding.spinnerFieldOfInterest.selectedItem.toString()
        var academic1 = binding.spinnerAcademic1.selectedItem.toString()
        var grade1 = binding.spinnerGrade1.selectedItem.toString()
        var academic2 = binding.spinnerAcademic2.selectedItem.toString()
        var grade2 = binding.spinnerGrade2.selectedItem.toString()
        var academic3 = binding.spinnerAcademic3.selectedItem.toString()
        var grade3 = binding.spinnerGrade3.selectedItem.toString()
        var academic4 = binding.spinnerAcademic4.selectedItem.toString()
        var grade4 = binding.spinnerGrade4.selectedItem.toString()
        var academic5 = binding.spinnerAcademic5.selectedItem.toString()
        var grade5 = binding.spinnerGrade5.selectedItem.toString()
        var academic6 = binding.spinnerAcademic6.selectedItem.toString()
        var grade6 = binding.spinnerGrade6.selectedItem.toString()
        var academic7 = binding.spinnerAcademic7.selectedItem.toString()
        var grade7 = binding.spinnerGrade7.selectedItem.toString()
        var academic8 = binding.spinnerAcademic8.selectedItem.toString()
        var grade8 = binding.spinnerGrade8.selectedItem.toString()
        var academic9 = binding.spinnerAcademic9.selectedItem.toString()
        var grade9 = binding.spinnerGrade9.selectedItem.toString()
        var academic10 = binding.spinnerAcademic10.selectedItem.toString()
        var grade10 = binding.spinnerGrade10.selectedItem.toString()
        var academic11 = binding.spinnerAcademic11.selectedItem.toString()
        var grade11 = binding.spinnerGrade11.selectedItem.toString()
        var academic12 = binding.spinnerAcademic12.selectedItem.toString()
        var grade12 = binding.spinnerGrade12.selectedItem.toString()


        val user = User(
            id = auth.getUser()?.id.toString(),
            results = true
        )

        val academic = Academic(
            user = auth.getUserID(),
            fieldOfInterest = fieldOfInterest,
            qualification = qualification,
            Subject1 = academic1,
            Grade1 = grade1,
            Subject2 = academic2,
            Grade2 = grade2,
            Subject3 = academic3,
            Grade3 = grade3,
            Subject4 = academic4,
            Grade4= grade4,
            Subject5 = academic5,
            Grade5 = grade5,
            Subject6 = academic6,
            Grade6 = grade6,
            Subject7 = academic7,
            Grade7 = grade7,
            Subject8 = academic8,
            Grade8 = grade8,
            Subject9 = academic9,
            Grade9 = grade9,
            Subject10 = academic10,
            Grade10 = grade10,
            Subject11 = academic11,
            Grade11 = grade11,
            Subject12 = academic12,
            Grade12 = grade12

        )

        val err = result.validate(academic)
        if(err != ""){
            errorDialog(err)
            return
        }

        result.update(user)
        Toast.makeText(context, "Upload successfully", Toast.LENGTH_SHORT).show()
        result.upload(academic)
        nav.popBackStack(R.id.nav_graph,false)
        nav.navigate(R.id.homeFragment)
    }

}