let socket = io.connect('http://localhost:4200');
socket.on('data', function(d) {
    data = d;
    divider.setM(data.m);
    divider.setB(data.b);
    predictedDivider.setM(-data.weights[0] / data.weights[1])
    predictedDivider.setB(-data.bias / data.weights[1]);
    c.points = data.points;
    console.log(d.msg);
});
socket.on('disconnect', () => window.close());
let data = { points: [] };

class Point {
    constructor(x, y, color = "black", bgColor = "transparent") {
        this.x = x;
        this.y = y;
        this.color = color;
        this.bgColor = bgColor;
    }
}

class Line {
    constructor(m, b) {
        this.m = m;
        this.b = b;
        this.f = (x) => this.m * x + this.b;
    }

    setM(m) {
        this.m = m;
        this.f = (x) => this.m * x + this.b;
    }

    setB(b) {
        this.b = b;
        this.f = (x) => this.m * x + this.b;
    }

}

class Graph {
    constructor(c) {
        this.c = c;
        this.ctx = c.getContext('2d');
        this.minX = -100;
        this.maxX = 100;
        this.minY = -100;
        this.maxY = 100;
        this.lines = [];
        this.points = []
        this.interval = setInterval(() => {
            this.ctx.clearRect(0, 0, this.c.width, this.c.height);
            this.ctx.beginPath();
            this.line(x => 0);
            this.lineWithYInput(y => 0);
            for (let line of this.lines)
                this.drawLine(line);
            for (let point of this.points)
                this.drawPoint(point);
        }, 100);
    }

    getPixelX(x) {
        return (x / this.maxX + 1) * this.c.width / 2;
    }

    getPixelY(y) {
        return (1 - y / this.maxY) * this.c.height / 2;
    }

    lineTo(x0, y0, x1, y1) {
        this.ctx.moveTo(this.getPixelX(x0), this.getPixelY(y0));
        this.ctx.lineTo(this.getPixelX(x1), this.getPixelY(y1));
        this.ctx.stroke();
    }

    line(f) {
        this.lineTo(this.minX, f(this.minX), this.maxX, f(this.maxX));
    }

    lineWithYInput(f) {
        this.lineTo(f(this.minY), this.minY, f(this.maxY), this.maxY);
    }

    point(x, y, color = 'black', size = "sm") {

        this.ctx.beginPath();
        this.ctx.arc(this.getPixelX(x), this.getPixelY(y), size == "lg" ? this.c.width / 100 : this.c.width / 200, 0, 2 * Math.PI);
        this.ctx.fillStyle = color;
        this.ctx.stroke();
        this.ctx.fill();
        this.ctx.closePath();
    }

    drawLine(l) {
        this.line(l.f);
    }

    drawPoint(p) {
        this.point(p.x, p.y, p.bgColor, "lg");
        this.point(p.x, p.y, p.color);
    }

    save() {
        let url = this.c.toDataURL("image/png");
        let element = document.createElement('a');
        element.setAttribute('href', url);
        element.setAttribute('download', "graph.png");
        element.style.display = 'none';
        document.body.appendChild(element);
        element.click();
        document.body.removeChild(element);
    }

}

let c = new Graph(document.getElementById("canvas"));
let divider = new Line(0, 0);
let predictedDivider = new Line(0, 0);
c.lines.push(divider);
c.lines.push(predictedDivider);