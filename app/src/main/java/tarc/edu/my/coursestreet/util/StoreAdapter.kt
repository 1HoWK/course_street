package tarc.edu.my.coursestreet.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.Store

class StoreAdapter (
    val fn: (ViewHolder, Store) -> Unit = {_,_ ->}
        ): ListAdapter<Store,StoreAdapter.ViewHolder>(DiffCallback){

    companion object DiffCallback: DiffUtil.ItemCallback<Store>(){
        override fun areItemsTheSame(a: Store, b: Store) = a.id == b.id
        override fun areContentsTheSame(a: Store, b: Store) = a == b

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val root = view
        val itemImage: ImageView = view.findViewById(R.id.itemImageBox)
        val itemName:  TextView = view.findViewById(R.id.itemNameTxt)
        val itemPrice: TextView = view.findViewById(R.id.itemPriceTxt)
        val redeemBtn: Button = view.findViewById(R.id.redeemBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.store_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val storeItem = getItem(position)

        holder.itemName.text = storeItem.itemName
        holder.itemPrice.text = "${storeItem.itemPrice.toString()} credits"
        holder.itemImage.setImageBitmap(storeItem.itemPhoto.toBitmap())

        fn(holder,storeItem)
    }

}