package mx.edu.itm.link.englishclass.user_feature.presentation.social.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import mx.edu.itm.link.englishclass.databinding.ItemActiveUserBinding

class AdapterActiveUsers(
    private val data:List<User>,
    val onClick:(User)->Unit
) : RecyclerView.Adapter<AdapterActiveUsers.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ItemActiveUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder){
            binding.apply {
                nameUserActive.text=data[position].name
                carreraUserActive.text=data[position].profession
                englishLevelUserActive.text="A1"
                callUserActive.setOnClickListener {
                    onClick.invoke(data[position])
                }
            }
        }
    }

    inner class ViewHolder(val binding: ItemActiveUserBinding):RecyclerView.ViewHolder(binding.root)
    /*

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val itemview=LayoutInflater.from(parent.context).inflate()
        //val a=LayoutInflater.from(parent.context).inflate(res,parent,false)
        return ViewHolder(itemview,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val currentItem= data[position]
         holder.bind(currentItem)
    }

    override fun getItemCount(): Int = data.size

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

            //tvName.text="User: ${item.nombre} "+ item.apellidos
            //tvCarrera.text="Carrera: ${item.carrera}"
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


     */
 }