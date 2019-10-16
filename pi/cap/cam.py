from picamera import PiCamera
from time import sleep
import calendar
import datetime


camera = PiCamera()
#date = datetime.datetime.now().strftime("%m_%d_%Y_%H_%M_%S")
camera.resolution = (1920, 1080)
camera.capture("/home/pi/cap/pics/testpic.jpg")

#camera.capture("/home/pi/cap/pics/"+date+".jpg")
#while True:
#    date = datetime.datetime.now().strftime("%m_%d_%Y_%H_%M_%S")
#    camera.capture("/home/pi/cap/pics/"+date+".jpg")
#    sleep(10)
