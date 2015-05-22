#!/usr/bin/python
import logging
logging.getLogger("scapy.runtime").setLevel(logging.ERROR)
from scapy.all import *
ip = subprocess.check_output("ifconfig wlan0" + " | grep 'inet addr' | cut -d ':' -f 2 | cut -d ' ' -f 1", shell=True).strip()
def rules(pkt):
	try:
		if (pkt[IP].dst== ip) and (pkt[ICMP]):
			print str(pkt[IP].src) + " is exploitable"
	except:
		pass
print "Listening for Incoming ICMP Traffic. Use Ctrl+C to stop listening"
sniff(lfilter=rules,store=0)
