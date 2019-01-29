const java = require("java");
const path = require("path");

java.classpath.push("src");
java.classpath.push('target/classes');

var javaLangSystem = java.import('java.lang.System');

javaLangSystem.out.printlnSync('Hello World');

java.classpath.push(path.resolve("."));
let Test = java.import("io.bhagat.Vector");

let list = java.newInstanceSync("java.util.ArrayList");
let v = java.newInstanceSync("io.bhagat.math.Vector");
list.addSync("item1");
console.log(list.getSync(0));