import sys
import socket
import json


# constants
HOST = "localhost"
MESSAGE_SIZE = 1024
PORT = int(sys.argv[1])


# function to receive a message from a server
def receiveMessage(sock):
   message_received = json.loads(sock.recv(MESSAGE_SIZE).decode("UTF-8"))
   return message_received

# function to send some message to a server
def sendMessage(sock, client_name_from, client_name_to, action, message):
   message_to_send = {"name": client_name_from,
                      "action": action,
                      "name_receiver": client_name_to,
                      "message": message}
   message_to_send = json.dumps(message_to_send).encode("UTF-8")
   sock.send(message_to_send)


# message to register a client
def register(sock):
   sock.connect((HOST, PORT))

   request_name = True
   while request_name:
      client_name = input("\nName?\n> ")
      sendMessage(sock, client_name, "", "register", "")

      request_name = not receiveMessage(sock)
   return client_name


# function to unregister a client
def unregister(sock, my_name):
   sendMessage(sock, my_name, "", "unregister", "")
   sock.close()

# request list of clients registered in the chat
# returns a list with all clients
def getClients(sock, my_name):
   sendMessage(sock, my_name, "", "get_clients", "")

   return receiveMessage(sock)

# function to send a message to a client through a server
def sendMessageToClient(sock, my_name, client_send):
   message = input("> ")
   sendMessage(sock, my_name, client_send, "send_message", message)

# function to receive a message from a client through a server
def recvMessageFromClient(sock, my_name, client_recv):
   sendMessage(sock, my_name, client_recv, "recv_message", "")

   message_received = receiveMessage(sock)

   print("\n\nMessages from " + client_recv)
   for m in message_received:
      print("< " + m)


# main function, where is created the client's socket and where are called the functions of some option
def main():
   sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

   my_name = register(sock)
   keep_running = True
   while keep_running:
      opt = input("\n\n\n"
                  "What to do?\n"
                  "1 - Send message\n"
                  "2 - See inbox\n"
                  "3 - Leave chat\n"
                  "> ")


      all_clients = getClients(sock, my_name)
      print("\n\n\n"
            "Clients:")
      for i in range(len(all_clients)):
         print(str(i + 1) + " - " + all_clients[i])

      if int(opt) == 1:
         client_send = input("> ")
         sendMessageToClient(sock, my_name, all_clients[int(client_send) - 1])
      elif int(opt) == 2:
         client_recv = input("> ")
         recvMessageFromClient(sock, my_name, all_clients[int(client_recv) - 1])
      elif int(opt) == 3:
         unregister(sock, my_name)
         keep_running = False


main()
