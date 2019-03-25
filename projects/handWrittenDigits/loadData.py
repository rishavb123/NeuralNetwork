from mlxtend.data import loadlocal_mnist
import numpy as np

train_images, train_labels = loadlocal_mnist(images_path='./data/bytes/train/images.idx3-ubyte', labels_path='./data/bytes/train/labels.idx1-ubyte')
test_images, test_labels = loadlocal_mnist(images_path='./data/bytes/test/images.idx3-ubyte', labels_path='./data/bytes/test/labels.idx1-ubyte')

print('Dimensions: %s x %s' % (train_images.shape[0], train_images.shape[1]))

# np.savetxt(fname='./data/csv/train/images.csv', X=train_images, delimiter=',', fmt='%d')
# np.savetxt(fname='./data/csv/train/labels.csv', X=train_labels, delimiter=',', fmt='%d')

# np.savetxt(fname='./data/csv/test/images.csv', X=test_images, delimiter=',', fmt='%d')
# np.savetxt(fname='./data/csv/test/labels.csv', X=test_labels, delimiter=',', fmt='%d')