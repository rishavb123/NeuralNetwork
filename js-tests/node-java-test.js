const java = require('java');
const path = require('path');

java.classpath.push('bin');
java.classpath.push('target/classes');

let Test = java.import('io.bhagat.math.Vector');
let t = new Test(2);
var charArray = java.newArray("char", "hello world\n".split(''));
t.set(0, 2);
t.set(1, 8);
console.log(t.toString());
console.log(t.getMagnitudeSync());
t.getMagnitude(console.log);