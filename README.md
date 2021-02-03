# Snoot-Cypher
A Modified Vigenere Cypher utility (Java)

## About

This Java program encrypts/decrypts messages with my modified Vigenere Cypher (which can encrypt the spaces along with the letters in a message). I built a supporting program to create generate code tables; the traditional Vigenere Cypher uses only the standard code table, but can work under different code tables. If it is unclear how to use these, start a discussion and I will explain/improve the documentation!

The Vigenere Cypher is a cypher that is resistant to frequency analysis, because the frequency of a given encrypted letter depends on the encoding string. In this way, it is harder to perform frequency analysis to decode it. It is nowhere near as rigorous as modern methods such as elliptic cryptography, but is enough for friends to feel cool communicating in a way that their friends will likely not be able to break. 

## Files

The repository contains a helper program to build code tables, and the program that does the encoding/decoding. Both are documented with comments within them, and have prompts to the console for user accessibility. The repository also contains a standard code table in a .txt format. 

## Future Directions

I am taking advanced linear algebra classes, which inspired me to think of the code table as a matrix. I would like to investigate what Strings act as "eigenStrings" (what string of text, when encoded, results in the same string of text?). It would be even cooler to describe the relationship between message Strings and encoding Strings; for example, what set of strings can be used as a message and as the encoding/decoding string?

I would also like to create a browser extension based on my code, to make it much faster to encrypt email messages.

