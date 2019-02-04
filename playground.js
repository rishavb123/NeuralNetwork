const java = require('java');

java.classpath.push('bin');
java.classpath.push('target/classes');


const NeuralNetwork = java.import('io.bhagat.artificialintelligence.NeuralNetwork');
const Perceptron = java.import('io.bhagat.artificialintelligence.Perceptron');
const Matrix = java.import('io.bhagat.math.Matrix');

// let m1 = new Matrix(2, 3);
// console.log(m1.toString());
// let vectors = m1.getVectorColumnsSync();
// for (let v of vectors)
//     console.log(v.toString());

// let p = new Perceptron(2, 10, 0.99, 100);

let nn = new NeuralNetwork(java.newArray("int", [3, 8, 102, 2, 8, 1]));
console.log(nn.feedForwardSync(java.newArray("double", [2, 2, 2])));