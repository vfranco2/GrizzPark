while true
do
raspistill -w 1920 -h 1080 -q 100 -sh 100 -o /home/pi/cap/pics/testpic.png
sudo scp -v -i /home/pi/cap/test.pem  /home/pi/cap/pics/* ubuntu@3.133.144.189:/var/www/html/img 
done

