package mx.edu.itm.link.englishclass.presenter.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itm.link.englishclass.call_feature.data.model.Call
import mx.edu.itm.link.englishclass.R
import mx.edu.itm.link.englishclass.presenter.fragments.CallsFragment


class CallsAdapter(var context: CallsFragment, val res: Int, var list: ArrayList<Call>): RecyclerView.Adapter<CallsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview= LayoutInflater.from(parent.context).inflate(res,parent,false)
        //val a=LayoutInflater.from(parent.context).inflate(res,parent,false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem= list[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView){
        fun bind(item: Call){

            val emisor: TextView=itemView.findViewById(R.id.emisorCalls)
            val receptor: TextView=itemView.findViewById(R.id.receptorCalls)
            val date: TextView=itemView.findViewById(R.id.dateCalls)

            emisor.text="From : ${item.emisor}"
            receptor.text="To: ${item.receptor}"
            date.text="Date:${item.date}"
        }
    }
}