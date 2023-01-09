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
        val chat = chats[userId] ?: throw ChatNotFoundException()
        return chat.messages.takeLast(count).onEach { it.read = true }
    }

    fun printLastMessages() {
        println("Последние сообщения в чатах:")
        for ((userId, chat) in chats) {
             println(if (chat.messages.isEmpty()) "Пользователь ID $userId: " + LastMessageResult.System("Нет сообщений")
             else "Пользователь ID $userId: " + LastMessageResult.Result(chat.messages.last()))
        }
    }

    fun emptyChat(userId: Int) {
        chats[userId] = Chat()
    }

    fun printChats() {
        println(chats)
    }

    fun getChats(): MutableMap<Int, Chat> {
        return chats
    }

    fun deleteMessage(userId: Int, message: Message): Boolean {
        println("Удаление сообщения /$message/ в чате с пользователем ID $userId:")
        return when {
            chats[userId]?.messages?.contains(message) == true -> {
                chats[userId]?.messages?.remove(message)
                if (chats[userId]?.messages?.isEmpty() == true) {
                    chats.remove(userId)
                }
                true
            }
            else -> throw MessageNotFoundException()
        }
    }

    fun deleteChat(userId: Int): Boolean {
        println("Удаление чата cс пользователем ID $userId:")
        return when {
            chats.containsKey(userId) -> {
                chats.remove(userId)
                true
            }
            else -> throw ChatNotFoundException()
        }
    }
}

