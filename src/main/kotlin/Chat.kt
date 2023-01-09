data class Chat(
    val messages: MutableList<Message> = mutableListOf()
) {
    override fun toString(): String {
        return "Чат: $messages"
    }
}

