
import numpy as np
def sigmoid(x):
    '''
    Fonction d'activation. Met la valeur d'entrée entre 0 et 1.
    '''
    return 1/(1+np.exp(-x))

def pente_sigmoid(x):
    '''
    Dérivée (pente) de la sigmoid
    '''
    return x*(1-x)

# Entrée
X = np.array([
    [1, 0, 0],
    [1, 1, 0],
    [0, 0, 1],
    [0, 1, 1]
 ])

# Résultats attendus
y = np.array([
    [1, 1, 0, 0]
]).T

# Initialisation des poids
 syn0 = np.random.random((3,1))

# Apprentissage
for i in range(1000):
    # l0 est la couche d'entrée. Elle contient les données de la matrice d'entrée X.
    l0 = X

    # l1 est la valeur de sortie : pour la calculer, on fait la somme pondérée de toutes ls valeurs d'entrée.
    # Exprimée en matrice, cela revient à faire le produit scalaire de l0 et de syn0.
    # On remet enfin cette somme entre 0 et 1, grace à la fonction sigmoid.
    l1 = sigmoid(np.dot(l0, syn0))

    # On doit ensuite calculer l'erreur. Cela va nous permettre d'ajuster correctement les poids (faut-il les réduire ?
    # les augmenter ?).
    # Cette erreur, c'est le résultat attendu (y) moins le résultat obtenu (l1)
    erreur = y - l1

    # Pour optimiser la façon dont on adapte les poids, on "regarde" où on se situe sur la pente de la sigmoid : est-ce
    # qu'on est sur une portion plutôt verticale ? Dans ce cas il faut beaucoup modifier les poids. Est-on au contraire
    # sur une portion plate ? Dans ce cas, le résultat est plus sur et il faut peu modifier les poids.
    delta = pente_sigmoid(l1) * erreur

    # Une fois le delta trouvé, on modifie les poids : on leur ajoute à chacun le résultat du produit scalaire de delta
    # par la donnée d'entrée.
    syn0 += np.dot(l0.T, delta)

print(y)
print(l1)
