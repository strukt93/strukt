#!/bin/bash
if [ "$#" -ne 1 ]; then
	echo "Usage - sh arping.sh [iplist]"
	echo "Example - sh arping.sh iplist.txt"
	exit
fi

file=$1
for addr in $(cat $file); do
arping -c 1 $addr | grep "bytes from" | cut -d " " -f 5 | cut -d "(" -f 2 | cut -d ")" -f 1 &
done
exit