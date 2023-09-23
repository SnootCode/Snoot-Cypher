import numpy as np

rng = np.random.default_rng()

def interleave(a:str, b:str) -> str:
    """
    Return the random interleaving of strings a and b. Permutations, order can change.
    """
    joined = a+b
    idx_arr = range(len(joined))
    interleaved_arr = rng.choice(idx_arr, len(idx_arr), replace=False)
    interleaving = ''
    for i in interleaved_arr:
        interleaving += joined[i]
    return interleaving