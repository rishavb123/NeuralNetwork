const express = require('express');
const app = express();
const server = require('http').createServer(app);
const io = require('socket.io')(server);
const java = require('java');
const childProcess = require('child_process');

java.classpath.push('bin');
java.classpath.push('target/classes');

const Perceptron = java.import('io.bhagat.artificialintelligence.Perceptron');

let data = {
    m: Math.random() * 10 - 5,
    b: Math.random() * 50 - 25,
    points: [],
    msg: "hello from the server",
    weights: [],
    bias: 0
};

app.use(express.static('public/perceptron'));

io.on('connection', (socket) => {
    socket.emit('data', data);
    socket.on('exit', () => process.exit());
});

server.listen(4200);
childProcess.exec("chrome http://localhost:4200/");

let perceptron = new Perceptron(2, 10, 0.99, 100);
for (let i = 0; i < 1000; i++) {
    x = Math.random() * 200 - 100;
    y = Math.random() * 200 - 100;
    data.points.push({
        x: x,
        y: y,
        color: ((y > data.m * x + data.b ? 1 : 0) == perceptron.guessSync(java.newArray("double", [x, y]))) ? "green" : "red",
        bgColor: y > data.m * x + data.b ? "white" : "black"
    });
}

io.sockets.emit("data", data);
let trainingIndex = 0;
let interval = setInterval(() => {
    if (trainingIndex >= data.points.length) {
        trainingIndex = 0;
        data.msg = "finished a cycle of training";
        io.sockets.emit('data', data);
        let lrRate = perceptron.getLearningRateFactorSync();
        if (lrRate == 0.99)
            perceptron.setLearningRate(1.01);
        else
            perceptron.setLearningRate(1.01);
        return;
    }
    const x = data.points[trainingIndex].x;
    const y = data.points[trainingIndex].y;
    perceptron.train(java.newArray("double", [x, y]), y > data.m * x + data.b ? 1 : 0);
    trainingIndex++;
    for (let i = 0; i < data.points.length; i++) {
        const x = data.points[i].x;
        const y = data.points[i].y;
        data.points[i].color = ((y > data.m * x + data.b ? 1 : 0) == perceptron.guessSync(java.newArray("double", [x, y]))) ? "green" : "red";
    }
    data.msg = "trained a point";
    data.weights = perceptron.getWeightsSync().getDataSync();
    data.bias = perceptron.getBiasSync()
    io.sockets.emit('data', data);
}, 100);