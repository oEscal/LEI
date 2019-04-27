import sys
import socket
import selectors
import json


clients = {}
new_client_object = {"id": None,
                     "messages": None}


sel = selectors.DefaultSelector()

HOST = sys.argv[1]
PORT = int(sys.argv[2])
MESSAGE_SIZE = 1024

# function to receive a message from a server
def receiveMessage(client):
   try:
      message_received = json.loads(client.recv(MESSAGE_SIZE).decode("UTF-8"))
      return message_received
   except json.decoder.JSONDecodeError:
      for cl_index in range(len(clients.values())):

         # verify if client exists to remove him from database
         if client == list(clients.values())[cl_index]["id"]:
            client_name = list(clients.keys())[cl_index]
            unregister(client, client_name)

      # return False to alert that was an error
      return False

# function to send some message to a server
def sendMessage(client, message_to_send):
   message_to_send = json.dumps(message_to_send).encode("UTF-8")
   client.send(message_to_send)


# select a client (socket)
def accept(sock):
   client, client_address = sock.accept()
   sel.register(client, selectors.EVENT_READ, initialize)

# function where are managed all options
def initialize(client):
   message_received = receiveMessage(client)
   if not message_received:
      return

   client_action = message_received["action"]
   name_from = message_received["name"]
   name_to = message_received["name_receiver"]
   message = message_received["message"]


   if client_action == "register":
      register(client, name_from)
   elif client_action == "get_clients":
      sendClients(client)
   elif client_action == "send_message":
      sendMessageToClient(name_from, name_to, message)
   elif client_action == "recv_message":
      sendInbox(client, name_from, name_to)
   elif client_action == "unregister":
      unregister(client, name_from)

# to register a client
def register(client, client_name):
   already_exists = False
   # verify if there are clients with requested name
   if client_name not in clients.keys():
      new_client_object["id"] = client
      new_client_object["messages"] = {}

      clients[client_name] = dict.copy(new_client_object)

      already_exists = True

   sendMessage(client, already_exists)

# to unregister a client
def unregister(client, client_name):
   clients.pop(client_name)
   sel.unregister(client)
   client.close()

# send list of registered clients
def sendClients(client):
   client_names = list(clients.keys())
   sendMessage(client, client_names)

# send message to other specific
def sendMessageToClient(client_name_from, client_name_receiver, message):
   inbox_receiver_per_client = clients[client_name_receiver]["messages"]

   # list all the clients which already had sent a message to the receiver
   names_inbox_receiver = list(inbox_receiver_per_client.keys())

   # create a chat from client_name_from to client_name_receiver
   if client_name_from not in names_inbox_receiver:
      inbox_receiver_per_client[client_name_from] = []

   # add a new message to the chat
   inbox_receiver_per_client[client_name_from].append(message)

   # send a notification of new message to the receiver
   client_id_receiver = clients[client_name_receiver]["id"]
   sendMessage(client_id_receiver, "New message from " + client_name_from)


# show all messages received from specific client
def sendInbox(client, client_name_from, client_name_receiver):
   inbox_per_client = clients[client_name_from]["messages"]

   # list all the clients which already had sent a message to the client
   names_inbox_client = list(inbox_per_client.keys())

   # select all messages from client_name_receiver to client_name_from
   if client_name_receiver in names_inbox_client:
      inbox = inbox_per_client[client_name_receiver]
   else:
      inbox = []

   sendMessage(client, inbox)


def main():
   sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

   sock.bind((HOST, PORT))
   sock.listen(100)
   sock.setblocking(False)
   sel.register(sock, selectors.EVENT_READ, accept)

   while True:
      events = sel.select()
      for key, mask in events:
         callback = key.data
         callback(key.fileobj)

main()
