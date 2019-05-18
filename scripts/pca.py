from py4j.java_gateway import JavaGateway
import numpy as np
import array
import sys
from graph import Graph

gateway = JavaGateway()
pca = gateway.entry_point

def np_create_matrix(numpy_matrix):
   header = array.array('i', list(numpy_matrix.shape))
   body = array.array('d', numpy_matrix.flatten().tolist()[0])
   if sys.byteorder != 'big':
      header.byteswap()
      body.byteswap()
   buf = bytearray(header.tostring() + body.tostring())
   return pca.createMatrix(buf)

def create_matrix(data):
    return np_create_matrix(np.matrix(data))

def create_pca_labels(data):
   labels = []
   for i in range(len(data[0])):
      labels.append('PC' + str(i + 1))
   return labels

colors = ['blue', 'red', 'green', 'purple', 'black', 'yellow', 'orange', 'magenta', 'gray']

original_data = [
    [2, -4, 12],
    [3, -1, 13],
    [-4, 18, 10],
    [-10, 5, 11],
    [9, 12, 12],
    [9, -19, 12],
    [-11, 30, 15],
    [-15, 45, 13],
    [7, 38, 14]
]
labels = ['X', 'Y', 'Z']

original_graph = Graph('Original Data', original_data, labels=labels, colors=colors)
pca.setDataMatrix(create_matrix(original_data))

result = pca.dimensionReduction().getData()
final_graph = Graph('Final Data', result, labels=create_pca_labels(result), colors=colors)

original_graph.graph()
final_graph.graph()

Graph.show()