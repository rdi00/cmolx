package ipvc.estg.olxcm

class Mensagem(val messageID: String, val text: String, val fromId: String, val toId: String,val time: Long) {
    constructor() : this("", "", "", "", -1)
}