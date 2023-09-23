"""
A fun challenge to rewrite SnootCypherv4 in Python.
Practice using dataframes.
"""

from dataclasses import dataclass
import pandas as pd

# @dataclass(frozen=True)
# class Table:
    



class CipherSpace:
    """
    Initializes with a codetable, provides encode and decode methods.
    """
    def __init__(self, characters_supported:str) -> None:
        # HEADER_SIZE = 1
        
        assert len(characters_supported) == len(set(characters_supported)), f'No duplicate characters supported allowed.'
        self._alphabet = list(characters_supported)
        

        # table = [[] for _ in range(len(characters_supported) + HEADER_SIZE)]
        
        # # Annotate the first row and column
        # for i,c in enumerate(characters_supported):
        #     print(table)

        #     table[0][i+HEADER_SIZE] = c # Header Row
        #     table[HEADER_SIZE+i][0] = c # Header Column
        #     table[i+HEADER_SIZE][0:] = alphabet[i:] + alphabet[:i] # Table interior

        # self.table = table

        # print(table)

    def encode(self, key:str, message:str) -> str:
        assert len(key) > 0, f'key must be non-empty string, recieved {key}.'
        out = ''
        key_idx = 0
        for character_in in message:
            x_coord = self._alphabet.index(character_in)
            y_coord = self._alphabet.index(key[key_idx])
            character_out_idx = (x_coord + y_coord) % len(self._alphabet)
            character_out = self._alphabet[character_out_idx]
            out += character_out
            key_idx = (key_idx + 1) % len(key)
        return out
    
    def decode(self, key:str, message:str) -> str:
        assert len(key) > 0, f'key must be non-empty string, recieved {key}.'
        out = ''
        key_idx = 0
        for character_in in message:
            x_coord = self._alphabet.index(character_in)
            y_coord = self._alphabet.index(key[key_idx])
            character_out_idx = (x_coord - y_coord) % len(self._alphabet)
            character_out = self._alphabet[character_out_idx]
            out += character_out
            key_idx = (key_idx + 1) % len(key)
        return out