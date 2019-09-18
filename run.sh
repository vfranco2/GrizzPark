#!/bin/bash

cd grizzpark
npm run serve &
cd ..
cd grizzpark_backend
python3 manage.py runserver 0:4000
