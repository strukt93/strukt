#!/usr/bin/python
import socket
import sys
if len(sys.argv) != 5:
	print "Usage - ./ftp_fuzz.py [Target-IP] [Port Number] [Interval] [Maximum]"
	print "Example - ./ftp_fuzz.py 10.0.0.5 21 100 1000"
	print "Example will fuzz the defined FTP service with a series of line break "
	print "characters to include 100 '\\n's, 200 '\\n's, etc... up to the maximum of 1000"
	sys.exit()
target = str(sys.argv[1])
port = int(sys.argv[2])
i = int(sys.argv[3])
interval = int(sys.argv[3])
max = int(sys.argv[4])
user = raw_input(str("Enter ftp username: "))
passwd = raw_input(str("Enter ftp password: "))
command = raw_input(str("Enter FTP command to fuzz: "))
while i <= max:
	try:
		payload = command + " " + ('\n' * i)
		print "Sending " + str(i) + " line break characters to target"
		s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		connect=s.connect((target,port))
		s.recv(1024)
		s.send('USER ' + user + '\r\n')
		s.recv(1024)
		s.send('PASS ' + passwd + '\r\n')
		s.recv(1024)
		s.send(payload + '\r\n')
		s.send('QUIT\r\n')
		s.recv(1024)
		s.close()
		i = i + interval
	except:
		print "\nUnable to send...Server may have crashed"
		sys.exit()
print "\nThere is no indication that the server has crashed"
