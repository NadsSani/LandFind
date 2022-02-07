package com.nads.landfind.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nads.landfind.R
import com.nads.landfind.data.Land
import com.nads.landfind.databinding.LandListCardsBinding
import com.nads.landfind.ui.buy.BuyerViewModel
import kotlinx.android.synthetic.main.land_list_cards.view.*

class LandFinderBaseAdapter(
    diffCallback: DiffUtil.ItemCallback<Land>,
    private val buyerViewModel: BuyerViewModel
) :
    PagingDataAdapter<Land, LandFinderBaseAdapter.ViewHolder>(diffCallback) {


    class ViewHolder(view: LandListCardsBinding) : RecyclerView.ViewHolder(view.root) {
        val cardView: CardView

        init {
            cardView = view.root.findViewById(R.id.carlistcardid)
        }

    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val context =holder.cardView.context
        val placename = holder.cardView.placename
        val img = holder.cardView.siteimage
        val village = holder.cardView.villagetxt
        val taluk = holder.cardView.taluktxt
        val landtype= holder.cardView.landtypetxt
        val property= holder.cardView.propertytxt
        val price= holder.cardView.pricetxt
        price.text = item?.price.toString()
        property.text= item?.propertiesland.toString()
        landtype.text = item?.landtype.toString()
        taluk.text = item?.hbtaluk.toString()
        village.text = item?.village.toString()
        placename.text = item?.placename.toString()
        Glide.with(context).load(item?.img1).fitCenter().into(img)
        Log.e("HATTER",holder.cardView.toString())
        holder.cardView.setOnClickListener {
            item?.id?.let { it1 -> buyerViewModel.navigateToScreen(it1.toString()) }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
//            .inflate(R.layout.cards_for_list, parent, false)
        val landListCardsBinding = LandListCardsBinding.inflate(view,parent,false)


        return ViewHolder(landListCardsBinding)
    }

    object UserComparator : DiffUtil.ItemCallback<Land>() {
        override fun areItemsTheSame(oldItem: Land, newItem: Land): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Land, newItem: Land): Boolean {
            return oldItem == newItem
        }
    }
    fun refreshView(position: Int) {
        notifyItemChanged(position)
        refresh()
    }

}