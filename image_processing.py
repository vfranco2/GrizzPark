from PIL import Image

image = Image.open('/var/www/html/img/testpic.png')
box = (300, 0, 900, 700)
image1 = image.crop(box)
first_row_ = image1.rotate(-200)
box4 = (100, 0, 500, 900)
first_row = first_row_.crop(box4)
first_row.save("firstrow.png")

box2 = (900, -50, 1600, 800)
image2 = image.crop(box2)
second_row_ = image2.rotate(200)
box3 = (175, 50, 600, 900)
second_row = second_row_.crop(box3)
second_row.save("secrow.png")
