import java.io.* ;

public class FileHandler {
    public static String readString(String filePath) throws Exception {
        File inFile = new File(filePath);
        char ch[] = new char[(int) inFile.length()];
        FileReader inFileReader = new FileReader(inFile);
        inFileReader.read(ch);
        inFileReader.close();
        return new String(ch);
    }

    public static void writeString(String text, String filePath) throws Exception {
        File outFile = new File(filePath);
        FileWriter outFileWriter = new FileWriter(outFile);
        outFileWriter.write(text);
        outFileWriter.close();
    }

    public static HuffmanTree loadTree(String filePath) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        HuffmanTree rootNode = (HuffmanTree) ois.readObject();
        ois.close();
        return rootNode;
    }

    public static void saveTree(HuffmanTree rootNode, String filePath) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(rootNode);
        oos.close();
    }

    public static void saveHuff(String huffmanCode, String filePath) throws Exception {
        FileOutputStream out = new FileOutputStream(filePath);
        BufferedOutputStream buffOut = new BufferedOutputStream(out);
        int currentByte;

        for (int i = 0; i < huffmanCode.length(); i += 8) {
            if (i + 8 < huffmanCode.length())
                currentByte = Integer.parseInt(huffmanCode.substring(i, i + 8), 2);
            else
                currentByte = Integer.parseInt("1"+huffmanCode.substring(i), 2);
            buffOut.write(currentByte);
        }
        buffOut.close();
    }

    public static String loadHuff(String filePath) throws Exception {
        File huffFile = new File(filePath);
        FileInputStream in = new FileInputStream(huffFile);
        BufferedInputStream buffIn = new BufferedInputStream(in);
        String text = "";
        for (int i = 0; i < huffFile.length() - 1; i++)
            text += String.format("%08d", Integer.parseInt(Integer.toString(buffIn.read(), 2)));
        text += String.format("%d", Integer.parseInt(Integer.toString(buffIn.read(), 2))).substring(1);
        in.close();
        return text; 
    }

    public static void displayFileChange(String file1Path, String file2Path) {
        File file1 = new File(file1Path);
        File file2 = new File(file2Path);

        System.out.print("in: "+file1.length()+" bytes, ");
        System.out.print("out: "+file2.length()+" bytes, ");
        double change = (file2.length()-file1.length()) / (double) (file1.length()+file2.length()) * 100;
        System.out.println("change: "+String.format("%.2f",change)+" %");
    }
}
