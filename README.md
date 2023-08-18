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

## Example

contents of sample :
```
Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris 
nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in 
reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla 
pariatur. Excepteur sint occaecat cupidatat non proident, sunt in 
culpa qui officia deserunt mollit anim id est laborum.

```

Running HuffmanCoding.java :
```

1. Encode file
2. Decode file
3. View tree
4. Exit
> 1

[Encode] Enter file path: sample
Encoded sample to sample.huff
in: 452 bytes, out: 238 bytes, change: -31.01 %

1. Encode file
2. Decode file
3. View tree
4. Exit
> 2

[Decode] Enter file path: sample.huff
Decoded sample.huff to sample
in: 238 bytes, out: 452 bytes, change: 31.01 %

1. Encode file
2. Decode file
3. View tree
4. Exit
> 3

452
┖-188
| ┖-85
| | ┖-[char:i, freq:42, code:000]
| | ┖-43
| | | ┖-[char:l, freq:21, code:0010]
| | | ┖-[char:r, freq:22, code:0011]
| ┖-103
| | ┖-46
| | | ┖-22
| | | | ┖-[char:p, freq:11, code:01000]
| | | | ┖-11
| | | | | ┖-5
| | | | | | ┖-2
| | | | | | | ┖-[char:D, freq:1, code:01001000]
| | | | | | | ┖-[char:h, freq:1, code:01001001]
| | | | | | ┖-[char:g, freq:3, code:0100101]
| | | | | ┖-6
| | | | | | ┖-[char:b, freq:3, code:0100110]
| | | | | | ┖-[char:v, freq:3, code:0100111]
| | | ┖-[char:n, freq:24, code:0101]
| | ┖-57
| | | ┖-[char:u, freq:28, code:0110]
| | | ┖-[char:o, freq:29, code:0111]
┖-264
| ┖-123
| | ┖-58
| | | ┖-[char:a, freq:29, code:1000]
| | | ┖-29
| | | | ┖-13
| | | | | ┖-6
| | | | | | ┖-[char:x, freq:3, code:1001000]
| | | | | | ┖-[char:f, freq:3, code:1001001]
| | | | | ┖-[char:\n, freq:7, code:100101]
| | | | ┖-[char:c, freq:16, code:10011]
| | ┖-65
| | | ┖-[char:t, freq:32, code:1010]
| | | ┖-33
| | | | ┖-16
| | | | | ┖-7
| | | | | | ┖-3
| | | | | | | ┖-[char:E, freq:1, code:10110000]
| | | | | | | ┖-2
| | | | | | | | ┖-[char:L, freq:1, code:101100010]
| | | | | | | | ┖-[char:U, freq:1, code:101100011]
| | | | | | ┖-[char:,, freq:4, code:1011001]
| | | | | ┖-9
| | | | | | ┖-[char:., freq:4, code:1011010]
| | | | | | ┖-[char:q, freq:5, code:1011011]
| | | | ┖-[char:m, freq:17, code:10111]
| ┖-141
| | ┖-[char: , freq:68, code:110]
| | ┖-73
| | | ┖-36
| | | | ┖-[char:s, freq:18, code:11100]
| | | | ┖-[char:d, freq:18, code:11101]
| | | ┖-[char:e, freq:37, code:1111]

1. Encode file
2. Decode file
3. View tree
4. Exit
> 4

Bye
```