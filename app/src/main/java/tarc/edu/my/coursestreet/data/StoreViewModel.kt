package tarc.edu.my.coursestreet.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.toObjects

class StoreViewModel: ViewModel() {
    private val storeItem  = MutableLiveData<List<Store>>()

    init {
        STORE.addSnapshotListener { snap, _ -> storeItem.value = snap?.toObjects() }
    }

    fun getAllStore() = storeItem

    fun setItemUser(I: Items){
        ITEMS.document().set(I)
    }
}