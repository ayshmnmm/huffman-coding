import java.io.Serializable;

class HuffmanTree implements Serializable {
    int frequency;
    char character;
    HuffmanTree left, right;

    HuffmanTree(int frequency) {
        this.frequency = frequency;
    }

    HuffmanTree(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }
}

public class Huffman {
    int printDepth = 0;
    HuffmanTree rootNode;

    public void encodeFile(String filePath) {
        String text;
        try {
            text = FileHandler.readString(filePath);
            buildHuffmanTree(getFrequencyDistribution(text));
            FileHandler.saveTree(rootNode, filePath + ".hufftree");
            String huffmanCode = "";
            for (int i = 0; i < text.length(); i++)
                huffmanCode += traverseEncode(rootNode, text.charAt(i), "");
            FileHandler.writeString(huffmanCode, filePath + ".huff");
            System.out.println(huffmanCode);
            System.out.println("Encoded "+filePath+" to "+filePath+".huff");
            FileHandler.displayFileChange(filePath,filePath+".huff");
        } catch (Exception e) {
            System.out.println("[ERROR] could not encode: " + e.getMessage());
            return;
        }
    }

    public void decodeFile(String filePath) {
        try {
            if (filePath.length()<5 || filePath.substring(filePath.length()-5)==".huff") {
                System.out.println("[ERROR] "+filePath+" must be a .huff file");
                return;
            }
            rootNode = FileHandler.loadTree(filePath + "tree");
            String huffmanCode = FileHandler.readString(filePath);
            System.out.println(huffmanCode);
            String text = traverseDecode(rootNode, huffmanCode);
            System.out.println(text);
            FileHandler.writeString(text, filePath.substring(0, filePath.length() - 5));
            System.out.println("Decoded "+filePath+" to "+filePath.substring(0, filePath.length() - 5));
        } catch (Exception e) {
            // System.out.println("[ERROR] could not decode: " + e.getMessage());
        }
    }

    public void displayTree() {
        if (rootNode == null) {
            System.out.println("[INFO] huffman tree appears to be empty");
            return;
        }
        printTree(rootNode, "");
    }

    private static HuffmanTree[] getFrequencyDistribution(String text) {
        int nunique = 0, k;
        char characters[] = new char[text.length()];
        int frequencies[] = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            for (k = 0; k < text.length() && characters[k] != '\0'; k++)
                if (text.charAt(i) == characters[k]) {
                    frequencies[k]++;
                    break;
                }
            if (characters[k] == '\0') {
                characters[k] = text.charAt(i);
                frequencies[k] = 1;
                nunique++;
            }
        }

        HuffmanTree[] huffmanForest = new HuffmanTree[nunique];
        for (int i = 0; i < nunique; i++)
            huffmanForest[i] = new HuffmanTree(characters[i], frequencies[i]);

        return huffmanForest;
    }

    private void buildHuffmanTree(HuffmanTree[] huffmanForest) {
        HuffmanTree newNode = null;
        for (int k = 0; k < huffmanForest.length - 1; k++) {
            int min1Index = -1, min2Index = -1;
            for (int i = 0; i < huffmanForest.length; i++)
                if (huffmanForest[i] != null && (min1Index == -1
                        || huffmanForest[min1Index].frequency > huffmanForest[i].frequency))
                    min1Index = i;
            for (int i = 0; i < huffmanForest.length; i++)
                if (huffmanForest[i] != null && ((min2Index == -1
                        || huffmanForest[min2Index].frequency > huffmanForest[i].frequency) && i != min1Index))
                    min2Index = i;

            int combinedFrequency = huffmanForest[min1Index].frequency + huffmanForest[min2Index].frequency;
            newNode = new HuffmanTree(combinedFrequency);
            newNode.left = huffmanForest[min1Index];
            newNode.right = huffmanForest[min2Index];
            huffmanForest[min1Index] = newNode;
            huffmanForest[min2Index] = null;
        }
        for (int i = 0; i < huffmanForest.length; i++)
            if (huffmanForest[i] != null)
                rootNode = huffmanForest[i];
    }

    private void printTree(HuffmanTree rootNode, String huffmanCode) {
        if (rootNode == null)
            return;
        for (int i = 0; i < printDepth - 1; i++)
            System.out.print("| ");

        if (rootNode.character != '\0')
            System.out.println((printDepth != 0 ? "\u2516-" : "") + "[char:" + Character.toString(rootNode.character).replace("\n", "\\n") + ", freq:"
                    + rootNode.frequency + ", code:" + huffmanCode + "]");
        else
            System.out.println((printDepth != 0 ? "\u2516-" : "") + rootNode.frequency);

        printDepth++;
        printTree(rootNode.left, huffmanCode + "0");
        printTree(rootNode.right, huffmanCode + "1");
        printDepth--;
    }

    private String traverseEncode(HuffmanTree rootNode, char character, String huffmanCode) {
        if (rootNode != null) {
            if (rootNode.character == character)
                return huffmanCode;

            String left, right;
            left = traverseEncode(rootNode.left, character, huffmanCode + "0");
            right = traverseEncode(rootNode.right, character, huffmanCode + "1");
            return (left == null) ? right : left;
        }
        return null;
    }

    private String traverseDecode(HuffmanTree rootNode, String remainingHuffmanCode) {
        if (rootNode.character != '\0') {
            if (remainingHuffmanCode.length() > 0)
                return rootNode.character + traverseDecode(this.rootNode, remainingHuffmanCode);
            else
                return Character.toString(rootNode.character);
        } else if (remainingHuffmanCode.length() > 0) {
            char next = remainingHuffmanCode.charAt(0);
            return traverseDecode(next == '0' ? rootNode.left : rootNode.right, remainingHuffmanCode.substring(1));
        }
        return null;
    }
}
