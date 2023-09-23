import cipherspace

alphabet = 'abc'

C = cipherspace.CipherSpace(alphabet)

key = 'ba'
message = 'abc'
encoded = C.encode(key, message) # Expect bba
decoded = C.decode(key, encoded) # Expect abc

print(encoded)
print(decoded)

import interleave
interleaving = interleave.interleave(message, message)
print(interleaving)

alphabet = 'abcdefghijklmnopqrstuvwxyz .,!?ABCDEFGHIJKLMNOPQRSTUVWXYZ'
print(interleave.interleave(alphabet, ''))
