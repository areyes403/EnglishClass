package mx.edu.itm.link.englishclass.Adapters



import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itm.link.englishclass.data.model.User
import mx.edu.itm.link.englishclass.R
import mx.edu.itm.link.englishclass.presenter.fragments.SocialFragment


  class ActiveUsersAdapter(var context: SocialFragment, val res: Int, var list: ArrayList<User>) : RecyclerView.Adapter<ActiveUsersAdapter.ViewHolder>(){

     private lateinit var mListener: onItemClickListener

     interface onItemClickListener{
         fun onItemClickListener(position: Int)
     }

     fun setOnItemClickListener(listener:onItemClickListener){
         mListener=listener
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview=LayoutInflater.from(parent.context).inflate(res,parent,false)
        //val a=LayoutInflater.from(parent.context).inflate(res,parent,false)
        return ViewHolder(itemview,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val currentItem= list[position]
         holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                listener.onItemClickListener(adapterPosition)
            }
        }

        //val icon : ImageView = itemView.findViewById(R.id.callUserActive)
        fun bind(item: User){
            val tvName : TextView = itemView.findViewById(R.id.nameUserActive)
            val tvCarrera : TextView = itemView.findViewById(R.id.carreraUserActive)
            val tvLevelEnglish : TextView = itemView.findViewById(R.id.englishLevelUserActive)
            val img: ImageView = itemView.findViewById(R.id.callUserActive)
            val status: Button=itemView.findViewById(R.id.viewStatus)
            val btnCalls:ImageView = itemView.findViewById(R.id.callUserActive)

            tvName.text="User: ${item.nombre} "+ item.apellidos
            tvCarrera.text="Carrera: ${item.carrera}"
            tvLevelEnglish.text="English level: Basic"
            //status.setEnabled(true);
            if (item.status == "true"){
                status.setBackgroundColor(Color.GREEN)
            }else{
                btnCalls.setImageResource(R.drawable.ic_calls_off)
                status.setBackgroundColor(Color.RED)

            }

/*
            icon.setOnClickListener {
                //println("hice un click en el icono")
                val activity = itemView.context as Activity
                val intent = Intent( activity, CallActivity::class.java)
                var friendsname="${item.nombre}"
                intent.putExtra("friendsname",friendsname)
                activity.startActivity(intent)
            }*/


        }
    }

 }