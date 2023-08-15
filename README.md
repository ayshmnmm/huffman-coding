# Huffman Coding

Huffman coding is a lossless data compression algorithm. The algorithm assigns variable length codes for characters based on their frequency of occurrence.

## Huffman

The Huffman class exposes three methods that can be called by a Huffman object : 
- `public void encodeFile(String filePath)` - Takes in path to plain text file `path` and writes the huffman code to `path.huff` and also stores the huffman tree in `path.hufftree`

- `public void decodeFile(String filePath)` - Takes the path of file containing huffman code `path.huff` and writes the decoded string to `path` (This method assumes that `path.hufftree` is also present in the same directory)

- `public void displayTree()` - Displays the huffman tree refreshed either by `encodeFile()` or `decodeFile()`

## Usage

The usage of Huffman is given below, a complete example can be found in [HuffmanCoding.java](HuffmanCoding.java)
```java
Huffman huffman = new Huffman();

huffman.encodeFile("sample.txt"); // generates sample.txt.huff and sample.txt.hufftree in the same directory

huffman.decodeFile("sample.txt.huff"); // generates sample.txt with decoded text (requires valid .hufftree file)

huffman.displayTree(); // prints the huffman tree
```
