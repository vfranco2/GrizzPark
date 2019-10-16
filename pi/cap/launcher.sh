while true
do
sudo python /home/pi/cap/cam.py &
sudo scp -v -i /home/pi/cap/test.pem  /home/pi/cap/pics/* ubuntu@3.15.235.146:/var/www/html/img
sleep 3
done
