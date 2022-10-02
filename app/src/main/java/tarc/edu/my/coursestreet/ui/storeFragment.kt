package tarc.edu.my.coursestreet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.AuthViewModel
import tarc.edu.my.coursestreet.data.Items
import tarc.edu.my.coursestreet.data.StoreViewModel
import tarc.edu.my.coursestreet.data.USERS
import tarc.edu.my.coursestreet.databinding.FragmentStoreBinding
import tarc.edu.my.coursestreet.util.SimpleEmail
import tarc.edu.my.coursestreet.util.StoreAdapter
import tarc.edu.my.coursestreet.util.snackbar

class storeFragment : Fragment() {
    private lateinit var binding: FragmentStoreBinding
    private val vm : AuthViewModel by activityViewModels()
    private val svm: StoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStoreBinding.inflate(inflater,container,false)
        var credits: Int  = 0

        vm.getUserLiveData().observe(viewLifecycleOwner) { user ->
            if (credits != null) {
                binding.creditAmountTxt.text = "${user!!.credit} credits"
            }
            credits = user!!.credit
        }



        val adapter = StoreAdapter(){ holder, store ->
            holder.redeemBtn.setOnClickListener{
                if (credits >= store.itemPrice){
                    val itemSet = Items(
                        id = "",
                        user = "${vm.getUserID()}",
                        itemName = "${store.itemName}",
                        itemPhoto = store.itemPhoto,
                        itemQty = +1
                    )
                    svm.setItemUser(itemSet)
                    val currentUser = vm.getUserLiveData()
                    USERS.document("${vm.getUserID()}").update("credit", currentUser.value!!.credit - store.itemPrice)
                    sendemail(store.itemName)
                    //Toast.makeText(context, "Successfully Redeemed", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(context, "Not enough credit", Toast.LENGTH_SHORT).show()
                }

            }

        }

        binding.storeItemRV.adapter = adapter
        svm.getAllStore().observe(viewLifecycleOwner){ store ->
            adapter.submitList(store)
        }

        return binding.root
    }

    private fun sendemail(name: String){
        val email = vm.getUserLiveData().value!!.email

        val subject = "Store Redeem"
        val content = """
            <p> You have redeemed <p>
            <h1> $name </h1>
        """.trimIndent()

        SimpleEmail()
            .to(email)
            .subject(subject)
            .content(content)
            .isHtml()
            .send() {
                snackbar("Redeemed")
            }
        snackbar("Redeeming...")


    }

}