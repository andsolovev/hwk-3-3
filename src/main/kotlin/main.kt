fun main() {
    ChatService.addMessage(1, Message("Hi!"))
    ChatService.addMessage(2, Message("Hello!"))
    ChatService.addMessage(2, Message("How are you?"))
    ChatService.emptyChat(3)
    ChatService.printChats()
    println()
    println(ChatService.unreadChatsCount())
    ChatService.getMessages(1,1)
    println()
    ChatService.printChats()
    println()
    println(ChatService.unreadChatsCount())
    println()
    ChatService.printLastMessages()
    println()

    ChatService.deleteMessage(2, Message("How are you?"))
    ChatService.printChats()
    println()

    ChatService.deleteChat(2)
    ChatService.printChats()

    println()
    println(ChatService.getChats())

}