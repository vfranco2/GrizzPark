#!/bin/bash

while true;do
	  python3 image_processing.py;
	  python3 test.py --image firstrow.png  --yolo ./yolo;
	  python3 test.py --image secrow.png  --yolo ./yolo;
	  python3 test.py --image thirdRow.png  --yolo ./yolo;
	  python3 test.py --image 4.png  --yolo ./yolo;
	  python3 test.py --image 5.png  --yolo ./yolo;
	  sleep 1m;

done
