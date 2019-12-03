#!/bin/bash

while true;do
	  python3 image_processing.py;
	  python3 test.py --image firstrow.jpg  --yolo ./yolo;
	  python3 test.py --image secrow.jpg  --yolo ./yolo;
	  python3 test.py --image thirdRow.jpg  --yolo ./yolo;
	  sleep 10;
done
