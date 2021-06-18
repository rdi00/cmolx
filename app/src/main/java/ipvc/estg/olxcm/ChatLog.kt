package ipvc.estg.olxcm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList


class ChatLog : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var mRootRef: DatabaseReference
    private lateinit var currentUserId: String
    private lateinit var chatUserId: String
    private lateinit var etMessage: EditText

    private lateinit var rvMensagens: RecyclerView
    private lateinit var srlMensagens: SwipeRefreshLayout
    private lateinit var mensagesAdatper: MensagemAdapter
    private lateinit var mensagens: MutableList<Mensagem>

    private val currentPage = 1
    private val RECORD_PER_PAGE = 30

    private lateinit var dbReferenceMessages: DatabaseReference
    private lateinit var childEventListener: ChildEventListener



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        auth = FirebaseAuth.getInstance()
        mRootRef = FirebaseDatabase.getInstance().reference
        currentUserId = auth.currentUser!!.uid

        etMessage = findViewById(R.id.etMessage)
        var chat_to : String = ""
        if (intent.hasExtra("USER_KEY")) {
            chat_to = intent.getStringExtra("USER_KEY").toString();
        }
        var email = ""

        when (chat_to) {
            "1" -> chatUserId = "Lqz2L4NJ2TbnCICx2kcb5pAVOG83" //zé
            "2" -> chatUserId = "Jv3rI6Kg9Iglyt2xTJDdjOvKeKi1" //rafa
            "3"  -> chatUserId = "RK3jw2xYdKTuDX88UrhHMDK4ay93" //Xavier
            else -> chatUserId = "y6XsYXjKfmWqCqtXxo5dkVtr4NC2" //bragança
        }


        val btn = findViewById<Button>(R.id.send_button_chat_log)
        // set on-click listener
        btn.setOnClickListener {
            val userMessagePush =
                mRootRef.child(NodeNames.MESSAGES).child(currentUserId).child(chatUserId).push()
            val pushId = userMessagePush.key
            sendMessage(
                etMessage.text.toString().trim { it <= ' ' },
                pushId!!
            )
        }


        rvMensagens = findViewById(R.id.rvMessages)
        srlMensagens = findViewById(R.id.srlMessages)


        mensagens = ArrayList()
        mensagesAdatper = MensagemAdapter(this,mensagens)

        rvMensagens.setLayoutManager(LinearLayoutManager(this))
        rvMensagens.setAdapter(mensagesAdatper)

    }

    private fun sendMessage(msg: String, pushId: String) {
        try {
            if (msg != "") {
                val messageMap = HashMap<Any, Any>()
                messageMap[NodeNames.MESSAGE_ID] = pushId
                messageMap[NodeNames.MESSAGE] = msg
                messageMap[NodeNames.MESSAGE_FROM] = currentUserId
                messageMap[NodeNames.MESSAGE_TIME] = ServerValue.TIMESTAMP

                val currentUserRef: String =
                    NodeNames.MESSAGES.toString() + "/" + currentUserId + "/" + chatUserId
                val chatUserRef: String =
                    NodeNames.MESSAGES.toString() + "/" + chatUserId + "/" + currentUserId
                val messageUserMap = HashMap<String, Any>()

                messageUserMap["$currentUserRef/$pushId"] = messageMap
                messageUserMap["$chatUserRef/$pushId"] = messageMap
                etMessage.setText("")

                mRootRef.updateChildren(
                    messageUserMap,
                    object : DatabaseReference.CompletionListener {
                        override fun onComplete(
                            @Nullable databaseError: DatabaseError?,
                            databaseReference: DatabaseReference
                        ) {
                            if (databaseError != null) {
                                Toast.makeText(
                                    this@ChatLog,
                                    getString(
                                        R.string.failed_to_send_message,
                                        databaseError.message
                                    ),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            run {
                                Toast.makeText(
                                    this@ChatLog,
                                    R.string.message_sent_successfully,
                                    Toast.LENGTH_SHORT
                                ).show()
                                var title = ""
                                /*if (msgType == Constants.MESSAGE_TYPE_TEXT) title =
                                    "New Message" else if (msgType == Constants.MESSAGE_TYPE_IMAGE) title =
                                    "New Image" else if (msgType == Constants.MESSAGE_TYPE_VIDEO) title =
                                    "New Video"
                                //Util.sendNotification(this@ChatLog, title, msg, chatUserId)*/
                            }
                        }
                    })
            }
        } catch (ex: Exception) {
            Toast.makeText(
                this@ChatLog,
                getString(R.string.failed_to_send_message, ex.message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun loadMessages() {
        mensagens.clear()
        dbReferenceMessages =
            mRootRef.child(NodeNames.MESSAGES).child(currentUserId).child(chatUserId)
        val messageQuery: Query =
            dbReferenceMessages.limitToLast(currentPage * RECORD_PER_PAGE)
        if (childEventListener != null) messageQuery.removeEventListener(childEventListener)
        childEventListener = object : ChildEventListener {

            override fun onChildAdded(dataSnapshot: DataSnapshot, @Nullable s: String?) {
                val message: Mensagem? = dataSnapshot.getValue(Mensagem::class.java)
                if (message != null) {
                    mensagens.add(message)
                }
                mensagesAdatper.notifyDataSetChanged()
                rvMensagens.scrollToPosition(mensagens.size - 1)
                srlMensagens.setRefreshing(false)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, @Nullable s: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                loadMessages()
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, @Nullable s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {
                srlMensagens.setRefreshing(false)
            }
        }
        messageQuery.addChildEventListener(childEventListener)
    }



    companion object{



    }
}


