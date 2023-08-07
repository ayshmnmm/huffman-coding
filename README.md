# Huffman Coding

Huffman coding is a lossless data compression algorithm. The algorithm assigns variable length codes for characters based on their frequency of occurrence.

## HuffmanTree

The HuffmanTree class exposes two methods that can be called by a HuffmanTree object : 
- `public void encodeFile(String path)` - Takes in path to plain text file `path` and writes the huffman code to `path.huff` and also stores the huffman tree in `path.hufftree` .

- `public void decodeFile(String path)` -
Takes the path of file containing huffman code `path.huff` and writes the decoded string to `path` . (This method assumes that `path.hufftree` is also present in the same directory)

## Usage

The usage of HuffmanTree is given below, a complete example can be found in [Huffman.java](Huffman.java)
```java
HuffmanTree huffman = new HuffmanTree();

huffman.encodeFile("sample.txt"); // generates sample.txt.huff and sample.txt.hufftree in the same directory

huffman.decodeFile("sample.txt.huff"); // generates sample.txt with decoded text (requires valid .hufftree file)

```