let socket = io.connect('http://localhost:4200');
socket.on('data', function(d) {
    data = d;
});

let data;

class Point {
    constructor(x, y, color = "black") {
        this.x = x;
        this.y = y;
        this.color = color;
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
        this.minX = -this.c.width / 2;
        this.maxX = this.c.width / 2;
        this.minY = -this.c.height / 2;
        this.maxY = this.c.height / 2;
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
        return x + this.c.width / 2;
    }

    getPixelY(y) {
        return this.c.height / 2 - y;
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

    point(x, y, color = 'black') {

        this.ctx.beginPath();
        this.ctx.arc(this.getPixelX(x), this.getPixelY(y), this.c.width / 200, 0, 2 * Math.PI);
        this.ctx.fillStyle = color;
        this.ctx.stroke();
        this.ctx.fill();
        this.ctx.closePath();
    }

    drawLine(l) {
        this.line(l.f);
    }

    drawPoint(p) {
        this.point(p.x, p.y, p.color);
    }

}

let c = new Graph(document.getElementById("canvas"));