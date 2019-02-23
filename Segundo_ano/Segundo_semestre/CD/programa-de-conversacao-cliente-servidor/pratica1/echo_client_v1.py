import socket
import json
import sys


HOST = "localhost"
PORT = int(sys.argv[1])
SIZE = 1024




def sendMessage(sock, message_to_send):
   sock.send(json.dumps(message_to_send).encode("UTF-8"))


def recvMessage(sock):
   return json.loads(sock.recv(SIZE).decode("UTF-8"))


def main():
   sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
   sock.connect((HOST, PORT))

   while True:
      message_to_send = input("New message? > ")
      sendMessage(sock, message_to_send)

      message_received = recvMessage(sock)
      print(message_received + "\n\n\n")


main()
