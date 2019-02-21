import sys
import socket
import selectors
import json


clients = {}
sel = selectors.DefaultSelector()

HOST = "localhost"
PORT = int(sys.argv[1])
MESSAGE_SIZE = 1024


# select a client (socket)
def accept(sock, mask):
   client, client_address = sock.accept()
   client.setblocking(False)
   sel.register(client, selectors.EVENT_READ, initialize)

# function where are managed all options
def initialize(client, mask):
   message_received = json.loads(client.recv(MESSAGE_SIZE).decode("UTF-8"))
   client_action = message_received["action"]

   if client_action == "register":
      client.setblocking(True)

      client_name = message_received["name"]
      register(client, client_name)

      client.setblocking(False)
   elif client_action == "get_clients":
      sendClients(client)
   elif client_action == "send_message":
      client_name_from = message_received["name"]
      client_name_to = message_received["name_receiver"]
      message_to_send = message_received["message"]

      sendMessage(client_name_from, client_name_to, message_to_send)
   elif client_action == "recv_message":
      sendInbox(client, message_received["name"], message_received["name_receiver"])
   elif client_action == "unregister":
      client_name = message_received["name"]
      clients.pop(client_name)
      sel.unregister(client)
      client.close()

# to register a client
def register(client, client_name):
   # verify if there are clients with requested name
   if client_name not in clients.keys():
      clients[client_name] = {"id": client,
                              "messages": {}}

      message_send = json.dumps(True)
   else:
      message_send = json.dumps(False)

   client.send(message_send.encode("UTF-8"))


# send list of registered clients
def sendClients(client):
   client_names = list(clients.keys())
   message_send = json.dumps(client_names)
   client.send(message_send.encode("UTF-8"))


# send message to other specific
def sendMessage(client_name_from, client_name_to, message):
   if client_name_from not in clients[client_name_to]["messages"].keys():
      clients[client_name_to]["messages"][client_name_from] = []
   clients[client_name_to]["messages"][client_name_from].append(message)


# show all messages received from specific client
def sendInbox(client, client_name_from, client_name_to):
   if client_name_to in clients[client_name_from]["messages"].keys():
      inbox = clients[client_name_from]["messages"][client_name_to]
   else:
      inbox = []

   message_send = json.dumps(inbox)
   client.send(message_send.encode("UTF-8"))


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
         callback(key.fileobj, mask)


main()
