import java.util.Scanner;

public class HuffmanCoding {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("");
        Huffman huffman = new Huffman();

        while (true) {
            System.out.print("1. Encode file\n2. Decode file\n3. View tree\n4. Exit\n> ");
            int choice = s.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.print("[Encode] Enter file path: ");
                    huffman.encodeFile(s.next());
                    break;
                case 2:
                    System.out.print("[Decode] Enter file path: ");
                    huffman.decodeFile(s.next());
                    break;
                case 3:
                    huffman.displayTree();
                    break;
                case 4:
                    System.out.println("Bye");
                    s.close();
                    System.exit(0);
                default:
                    System.out.println("Not an option!");
            }
            System.out.println();
        }
    }
}
