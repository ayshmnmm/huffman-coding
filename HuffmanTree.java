import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class HuffmanTree {
    private Node root;
    private String decodedString, encodedString, remainingString;

    public void encodeFile(String path) {
        try {
            this.decodedString = Files.readString(Path.of(path), StandardCharsets.UTF_8);
            this.root = buildTree(decodedString);
            // save object to file
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + ".hufftree"));
            oos.writeObject(this.root);
            oos.close();
            this.encodedString = "";
            this.remainingString = this.decodedString;
            while (this.remainingString.length() >= 1)
                this.traverseEncode(root, "");
            Files.writeString(Path.of(path + ".huff"), this.encodedString);
            System.out.println("Encoded to " + path + ".huff");
        } catch (Exception e) {
            System.out.println("[ERROR] Could not encode " + path + " : " + e);
        }
    }

    public void decodeFile(String path) {
        try {
            this.encodedString = Files.readString(Path.of(path), StandardCharsets.UTF_8);
            // load object from file
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + "tree"));
            this.root = (Node) ois.readObject();
            ois.close();
            this.decodedString = "";
            this.remainingString = this.encodedString;
            while (this.remainingString.length() >= 1)
                this.traverseDecode(this.root);
            Files.writeString(Path.of(path.substring(0, path.length() - 5)), this.decodedString);
            System.out.println("Decoded to " + path.substring(0, path.length() - 5));
        } catch (Exception e) {
            System.out.println("[ERROR] Could not decode " + path + " : " + e);
        }
    }

    private static Node buildTree(String str) {
        // find frequencies of characters in str
        int nunique = 0, k;
        char characters[] = new char[str.length()];
        int frequencies[] = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            for (k = 0; k < str.length() && characters[k] != '\0'; k++)
                if (str.charAt(i) == characters[k]) {
                    frequencies[k]++;
                    break;
                }
            if (characters[k] == '\0') {
                characters[k] = str.charAt(i);
                frequencies[k] = 1;
                nunique++;
            }
        }

        // create node array
        Node arr[] = new Node[nunique];
        for (int i = 0; i < arr.length; i++)
            arr[i] = new Node(frequencies[i], characters[i]);

        // combine 2 nodes with least frequencies n-1 times
        for (int i = 0; i < arr.length - 1; i++)
            combine(arr);

        // return root node
        for (int i = 0; i < arr.length; i++)
            if (arr[i] != null)
                return arr[i];

        return null;
    }

    private void traverseEncode(Node root, String path) {
        if (root != null) {
            if (this.remainingString.length() >= 1 && root.character == this.remainingString.charAt(0)) {
                this.encodedString = this.encodedString + path;
                this.remainingString = this.remainingString.substring(1);
            }
            traverseEncode(root.left, path + "0");
            traverseEncode(root.right, path + "1");
        }
    }

    private void traverseDecode(Node root) {
        if (root != null) {
            if (root.left == null && root.right == null)
                this.decodedString += root.character;
            else if (remainingString.length() >= 1) {
                // if 0 move to left child else right child
                char next = this.remainingString.charAt(0);
                this.remainingString = this.remainingString.substring(1);
                traverseDecode(next == '0' ? root.left : root.right);
            }
        }
    }

    private static void combine(Node arr[]) {
        int min1 = -1, min2 = -1;
        // find indexes of least and 2nd least frequency nodes
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) // ditch null values
                continue;

            if (min1 == -1 || arr[min1].frequency > arr[i].frequency) {
                min2 = min1;
                min1 = i;
            } else if (min2 == -1 || ((arr[min2].frequency > arr[i].frequency) && i != min1))
                min2 = i;
        }
        // add the 2 frequencies and create new node
        double combinedFrequency = arr[min1].frequency + arr[min2].frequency;
        Node newNode = new Node(combinedFrequency);

        newNode.left = arr[min1];
        newNode.right = arr[min2];
        arr[min1] = newNode;
        arr[min2] = null;
    }
}

class Node implements Serializable {
    double frequency;
    char character;
    Node left, right;

    Node(double frequency) {
        this.frequency = frequency;
    }

    Node(double frequency, char character) {
        this.frequency = frequency;
        this.character = character;
    }
}
