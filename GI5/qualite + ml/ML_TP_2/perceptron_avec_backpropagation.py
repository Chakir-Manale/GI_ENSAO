import numpy as np

class Perceptron():
    def __init__(self):
        self.syn_weights = np.random.rand(4,1)

    def sigmoid(self, x):
        return (1 / (1 + np.exp(-x)))

    def sigmoid_deriv(self, x):
        return np.exp(-x)/((1 + np.exp(-x))**2)

    def train(self, inputs, real_outputs, its, lr):

        delta_weights = np.zeros((4,7))

        for iteration in (range(its)):

            # forward pass
            z = np.dot(inputs, self.syn_weights)
            activation = self.sigmoid(z)

            # back pass
            for i in range(7):
                cost = (activation[i] - real_outputs[i])**2
                cost_prime = 2*(activation[i] - real_outputs[i])
                for n in range(4):
                    delta_weights[n][i] = cost_prime * inputs[i][n] * self.sigmoid_deriv(z[i])

            delta_avg = np.array([np.average(delta_weights, axis=1)]).T
            self.syn_weights = self.syn_weights - delta_avg*lr

    def results(self, inputs):
        return self.sigmoid(np.dot(inputs, self.syn_weights))


if __name__ == "__main__":

    ts_input = np.array([[0,0,1,0],
                         [1,1,1,0],
                         [1,0,1,1],
                         [0,1,1,1],
                         [0,1,0,1],
                         [1,1,1,1],
                         [0,0,0,0]])

    ts_output = np.array([[0,1,1,0,0,1,0]]).T # First Value of Input = output

    testing_data = np.array([[0,1,1,0],
                             [0,0,0,1],
                             [0,1,0,0],
                             [1,0,0,1],
                             [1,0,0,0],
                             [1,1,0,0],
                             [1,0,1,0]])

    lr = 10 # Learning Rate
    steps = 10000
    perceptron = Perceptron() # Initialize a perceptron
    perceptron.train(ts_input, ts_output, steps, lr) # Train the perceptron

    results = []
    for x in (range(len(testing_data))):
        run = testing_data[x]
        trial = perceptron.results(run)
        results.append(trial.tolist())
    print("results")
    print(results)
    print(np.ravel(np.rint(results))) # View rounded results
    print(perceptron.syn_weights)