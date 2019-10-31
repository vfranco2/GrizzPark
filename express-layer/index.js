const express = require('express')
const app = express()
const port = 4444

const mysql = require('serverless-mysql')({
  config: {
    host     : "localhost",
    database : "project",
    user     : "root",
    password : "pass"
  }
})

app.get('/', async function (req, res) {
    try{
	let results = await mysql.query('SELECT * FROM data')
	await mysql.end()
	let rtn = {
	    error: false,
	    data: results
	}
	res.send(rtn)
    }
    catch(err){
	let rtn = {
	    error: true,
	    data: err.message
	}
	res.send(rtn)
    }
})



app.listen(port, () => console.log(`API running on port ${port}!`))
 


