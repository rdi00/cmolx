package ipvc.estg.olxcm


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.messages.view.*
import java.text.SimpleDateFormat
import java.util.*


class MensagemAdapter(context: Context?, messageList: List<Mensagem>?): RecyclerView.Adapter<MensagemAdapter.MessageViewHolder>()  {
    private lateinit var context: Context
    private lateinit var messageList: List<Mensagem>
    private lateinit var firebaseAuth: FirebaseAuth



    fun MensagemAdapter(context: Context?, messageList: List<Mensagem>?) {
        this.context = context!!
        this.messageList = messageList!!
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.messages,viewGroup,false)
        return MessageViewHolder(view)
    }



    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message: Mensagem = messageList[position]
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUserId = firebaseAuth.currentUser!!.uid

        val fromUserId: String = message.fromId

        val sfd = SimpleDateFormat("dd-MM-yyyy HH:mm")
        val dateTime = sfd.format(Date(message.time))
        val splitString = dateTime.split(" ").toTypedArray()
        val messageTime = splitString[1]

        if (fromUserId.equals(currentUserId)){
            holder.llSent.setVisibility(View.VISIBLE)
            holder.llReceived.setVisibility(View.GONE)
            holder.tvSentMessage.setText(message.text)
            holder.tvSentMessageTime.setText(messageTime)

        }else{
            holder.llReceived.setVisibility(View.VISIBLE)
            holder.llSent.setVisibility(View.GONE)
            holder.tvReceivedMessage.setText(message.text)
            holder.tvReceivedMessageTime.setText(messageTime)
        }

    }
    class MessageViewHolder(itemView: View) : ViewHolder(itemView) {

        val llSent: LinearLayout
        val llReceived: LinearLayout
        val tvSentMessage: TextView
        val tvSentMessageTime: TextView
        val tvReceivedMessage: TextView
        val tvReceivedMessageTime: TextView
        val clMessage: ConstraintLayout

        init {
            llSent = itemView.findViewById(R.id.llSent)
            llReceived = itemView.findViewById(R.id.llReceived)
            tvSentMessage = itemView.findViewById(R.id.tvSentMessage)
            tvSentMessageTime = itemView.findViewById(R.id.tvSentMessageTime)
            tvReceivedMessage = itemView.findViewById(R.id.tvReceivedMessage)
            tvReceivedMessageTime = itemView.findViewById(R.id.tvReceivedMessageTime)
            clMessage = itemView.findViewById(R.id.clMessage)
        }
    }
}