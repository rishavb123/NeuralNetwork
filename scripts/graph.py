import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
import numpy as np

fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')

colors = ['blue', 'red', 'green', 'purple', 'black', 'yellow']

data = [
    [1, 2, 0.5111],
    [2, 4, 1],
    [3, 6.02, 1.51],
    [6, 12.52, 2],
    [2, 4.01, 2.5],
    [1, 1.15, 3.01]
]

xs, ys, zs = [], [], []

for point in data:
    xs.append(point[0])
    ys.append(point[1])
    zs.append(point[2])

ax.set_xlabel('X Label')
ax.set_ylabel('Y Label')
ax.set_zlabel('Z Label')

ax.scatter(xs, ys, zs, c=colors)

data2 = [
    [ 0.8691316263946899, -0.06410628527011399],
    [ 0.2881212273737651, -1.0141310978851068],
    [ -0.29276656930092587, -0.2683846180450577],
    [ -2.0399148491690626, 1.2447625961045494],
    [ 0.2952942428131986, 0.0865676102367683],
    [ 0.880134321888335, 0.015291794858961651],
]

xs2, ys2 = [], []

for point in data2:
    xs2.append(point[0])
    ys2.append(point[1])

plt.figure()
plt.scatter(xs2, ys2, c=colors)
plt.show()