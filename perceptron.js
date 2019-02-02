const express = require('express');
const app = express();
const server = require('http').createServer(app);
const io = require('socket.io')(server);
const java = require('java');

java.classpath.push('bin');
java.classpath.push('target/classes');

const Perceptron = java.import('io.bhagat.artificialintelligence.Perceptron');

app.use(express.static('public'));

io.on('connection', (socket) => {
    socket.emit('data', data);
});


server.listen(4200);

let perceptron = new Perceptron();
perceptron.guess(java.newArray("double", [2, 4]), console.log);