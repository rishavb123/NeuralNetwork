const express = require('express');
const app = express();
const server = require('http').createServer(app);
const io = require('socket.io')(server);
const java = require('java');

java.classpath.push('bin');
java.classpath.push('target/classes');

const Perceptron = java.import('io.bhagat.artificialintelligence.Perceptron');

app.use(express.static('public'));
let data = [
    { id: 1, text: 'item 1', date: new Date(2013, 6, 20), group: 1, first: true },
    { id: 2, text: 'item 2', date: '2013-06-23', group: 2 },
    { id: 3, text: 'item 3', date: '2013-06-25', group: 2 },
    { id: 4, text: 'item 4' }
];

io.on('connection', (socket) => {
    socket.emit('data', data);
});


server.listen(4200);

let perceptron = new Perceptron();
perceptron.guess(java.newArray("double", [2, 4]), console.log);