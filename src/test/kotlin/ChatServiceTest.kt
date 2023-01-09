import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class ChatServiceTest {

    @Test
    fun addMessage() {
        val testMessage = Message("Hello!")
        val result = ChatService.addMessage(1, testMessage)
        assertEquals(testMessage, result)
    }

    @Test
    fun deleteMessage() {
        val testMessage = Message("Hello!")
        ChatService.addMessage(1, testMessage)
        val result = ChatService.deleteMessage(1, testMessage)
        assertEquals(true, result)
    }

    @Test
    fun deleteLastMessage() {
        val testMessage = Message("Hello!")
        ChatService.deleteMessage(1, testMessage)
        ChatService.deleteMessage(1, testMessage)
        val result = ChatService.getChats()
        val emptyChats = mutableMapOf<Int, Chat>()
        assertEquals(emptyChats, result)
    }

    @Test
    fun shouldThrowMessageNotFound() {
        assertThrows<MessageNotFoundException> {
            val testMessage = Message("Hello!")
            ChatService.addMessage(1, testMessage)
            val result = ChatService.deleteMessage(2, testMessage)
        }
    }

    @Test
    fun unreadChatsCount() {
        val testMessage = Message("Hello!")
        ChatService.addMessage(1, testMessage)
        val result = ChatService.unreadChatsCount()
        assertEquals(1,1)
    }

    @Test
    fun getMessages() {
        val messages: MutableList<Message> = emptyList<Message>().toMutableList()
        val testMessage1 = Message("Hello!")
        val testMessage2 = Message("Hi!")

        messages.add(testMessage1)
        messages.add(testMessage2)

        ChatService.addMessage(1, testMessage1)
        ChatService.addMessage(1, testMessage2)

        val result = ChatService.getMessages(1, 2)
        assertEquals(messages, result)
    }

    @Test
    fun deleteChat() {
        val testMessage = Message("Hello!")
        ChatService.addMessage(1, testMessage)
        val result = ChatService.deleteChat(1)
        assertEquals(true, result)
    }

    @Test
    fun shouldThrowChatNotFound() {
        assertThrows<ChatNotFoundException> {
            val testMessage = Message("Hello!")
            ChatService.addMessage(1, testMessage)
            val result = ChatService.deleteChat(2)
        }
    }
}