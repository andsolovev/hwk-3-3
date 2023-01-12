object ChatService {
    private val chats = mutableMapOf<Int, Chat>()

    fun addMessage(userId: Int, message: Message): Message {
        return chats.getOrPut(userId) { Chat() }.messages.let {
            it.add(message)
            it.last()
        }
    }

    fun unreadChatsCount(): Int {
        println("Количество чатов с непрочитанными сообщениями:")
        return chats.values.count { chat -> chat.messages.any { !it.read } }
    }

    fun getMessages(userId: Int, count: Int): List<Message> {
        return chats[userId].let { it ?: throw ChatNotFoundException() }
            .messages.takeLast(count)
            .onEach { it.read = true }
    }

    fun emptyChat(userId: Int) {
        chats[userId] = Chat()
    }

    fun getChats(): MutableMap<Int, Chat> {
        return chats
    }

    fun deleteMessage(userId: Int, message: Message): Boolean {
        println("Удаление сообщения /$message/ в чате с пользователем ID $userId:")
        return chats[userId].let { it ?: throw ChatNotFoundException() }
            .messages.remove(message)
            .also { if (chats[userId]?.messages?.isEmpty() == true) chats.remove(userId) }
    }

    fun deleteChat(userId: Int): Boolean {
        println("Удаление чата cс пользователем ID $userId:")
        chats[userId].let { it ?: throw ChatNotFoundException() }
        return chats.remove(userId, chats[userId])
    }

    fun printLastMessages() {
        println("Последние сообщения в чатах:")
        for ((userId, chat) in chats) {
            println(if (chat.messages.isEmpty()) "Пользователь ID $userId: " + LastMessageResult.System("Нет сообщений")
            else "Пользователь ID $userId: " + LastMessageResult.Result(chat.messages.last()))
        }
    }

    fun printChats() {
        println(chats)
    }
}

