from PIL import Image

# image1 = Image.open('/home/jon/Pictures/testpic.png')
image1 = Image.open('/var/www/html/img/testpic.png')
image = image1.rotate(180)
box = (450, 200, 1100, 1100)
image = image.crop(box)
image.save("firstrow.png")


image1 = image1.rotate(180)
box = (1200, 150, 2000, 1100)
image = image1.crop(box)
image.save("secrow.png")

image3 = Image.open('/var/www/html/img/testpichhb.png')
#image3 = Image.open('/home/jon/Pictures/testpichhb.png')
image3 = image3.rotate(180)
box = (0, 500, 2000, 1800)
image3 = image3.crop(box)
image3.save("thirdRow.png")

#image4 = Image.open('/home/jon/Pictures/testpicmsc.png')
image4 = Image.open('/var/www/html/img/testpicmsc.png')
box = (450, 200, 1000, 2000)
image = image4.crop(box)
image.save("4.png")

box1 = (900, 200, 1500, 2000)
image = image4.crop(box1)
image.save("5.png")
