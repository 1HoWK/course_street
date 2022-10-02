package tarc.edu.my.coursestreet.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL

//TODO : INSERT OUR OWN DATA

@Serializable
data class EventPhoto(
    var id: String,
    var author: String,
    var width: Int,
    var height: Int,
    var url: String,
    var download_url: String,
){
    val eventPhoto = "https://picsum.photos/id/$id/500/500.webp"
}

class EventViewModel: ViewModel (){
    val eventPhoto = MutableLiveData<List<EventPhoto>>()

    init {
        viewModelScope.launch {
            val str= withContext(Dispatchers.IO){
                URL("https://picsum.photos/v2/list?limit=100").readText()
            }
            eventPhoto.value = Json.decodeFromString(str)
        }
    }

    fun get(id: String) = eventPhoto.value?.find { p -> p.id == id }
}