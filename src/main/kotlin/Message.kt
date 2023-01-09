data class Message(val text: String, var read: Boolean = false) {
    override fun toString(): String {
        return if (read == true) "$text (прочитано)" else "$text (не прочитано)"
    }
}

sealed class LastMessageResult {
    data class Result(val message: Message): LastMessageResult() {
        override fun toString(): String {
            return "$message"
        }
    }
    data class System(val text: String): LastMessageResult() {
        override fun toString(): String {
            return "$text"
        }
    }
}




