import socket
import selectors
import json
import sys

HOST = "localhost"
PORT = int(sys.argv[1])
SIZE = 1024


sel = selectors.DefaultSelector()


def sendMessage(client, message_to_send):
   client.send(json.dumps(message_to_send).encode("UTF-8"))


def recvMessage(client):
   return json.loads(client.recv(SIZE).decode("UTF-8"))

def register(sock, sel):
   client, client_address = sock.accept()
   sel.register(client, selectors.EVENT_READ, init)

def init(client):
   message_received = recvMessage(client)
   sendMessage(client, message_received.upper())


def main():
   sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
   sock.bind((HOST, PORT))
   sock.listen(10)
   sock.setblocking(False)

   sel.register(sock, selectors.EVENT_READ, register)

   while True:
      event = sel.select()
      for key, mask in event:
         print(key)
         function = key.data
         if function == register:
            function(key.fileobj, sel)
         else:
            function(key.fileobj)


main()
