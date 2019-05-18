import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
from matplotlib.lines import Line2D
import numpy as np

class Graph:

    def __init__(self, name, data, labels=['X Label', 'Y Label','Z Label'], colors=False):
        self.data = data
        self.dimensions = len(data[0])
        if not colors:
            self.colors = [0] * len(data)
        else:
            self.colors = colors
        self.name = name
        self.figure = plt.figure(self.name)
        if self.dimensions == 4 or self.dimensions == 3:
            self.ax = self.figure.add_subplot(111, projection='3d')
            self.ax.set_xlabel(labels[0])
            self.ax.set_ylabel(labels[1])
            self.ax.set_zlabel(labels[2])
        elif self.dimensions == 2:
            plt.xlabel(labels[0])
            plt.ylabel(labels[1])
        elif self.dimensions == 1:
            plt.xlabel(labels[0])

    def graph(self):
        if self.dimensions == 1:
            self.__graph1d()
        if self.dimensions == 2:
            self.__graph2d()
        elif self.dimensions == 3:
            self.__graph3d()
        elif self.dimensions == 4:
            self.__graph4d()

    def __graph1d(self):
        plt.figure(self.name)
        xs, ys = [], []
        for point in self.data:
            xs.append(point[0])
            ys.append(0)
        plt.scatter(xs, ys, c=self.colors)

    def __graph2d(self):
        plt.figure(self.name)
        xs, ys = [], []
        for point in self.data:
            xs.append(point[0])
            ys.append(point[1])
        plt.scatter(xs, ys, c=self.colors)

    def __graph3d(self):
        plt.figure(self.name)
        xs, ys, zs= [], [], []
        for point in self.data:
            xs.append(point[0])
            ys.append(point[1])
            zs.append(point[2])
        self.ax.scatter(xs, ys, zs, c=self.colors)

    def __graph4d(self):
        xs, ys, zs, cs = [], [], [], []
        plt.figure(self.name)
        for point in self.data:
            xs.append(point[0])
            ys.append(point[1])
            zs.append(point[2])
            cs.append(point[3])
        img = self.ax.scatter(xs, ys, zs, c=cs, cmap=plt.hot())
        self.figure.colorbar(img)

    @staticmethod
    def show():
        plt.show()
