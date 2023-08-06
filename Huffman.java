import java.util.Scanner;

public class Huffman {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("");
        HuffmanTree huffman = new HuffmanTree();

        while (true) {
            System.out.print("1. Encode file\n2. Decode file\n3. View code\n4. Exit\n> ");
            int choice = s.nextInt();
            switch (choice) {
                case 4:
                    System.out.println("Bye");
                    System.exit(0);
                case 1:
                    System.out.print("[Encode] Enter file path: ");
                    huffman.encodeFile(s.next());
                    break;
                case 2:
                    System.out.print("[Decode] Enter file path: ");
                    huffman.decodeFile(s.next());
                    break;
                default:
                    System.out.println("Not an option!");
            }
        }
    }
}