package mx.edu.itm.link.englishclass.call_feature.presentation.call_history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itm.link.englishclass.call_feature.domain.model.RealtimeCall
import mx.edu.itm.link.englishclass.databinding.ItemCallsEndedBinding


class CallsAdapter(
    private val data:List<RealtimeCall>
): RecyclerView.Adapter<CallsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ItemCallsEndedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.emisorCalls.text = data[position].emisor!!.name
        holder.binding.receptorCalls.text = data[position].receptor!!.name
        //holder.binding.dateCalls.text = data[position].date
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(val binding:ItemCallsEndedBinding):  RecyclerView.ViewHolder(binding.root)
}