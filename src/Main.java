import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;

public class Main {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        int wordCount = 0;
        int characterCount = 0;

        try
        {

            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String[] words;
                int line = 0;

                while (reader.ready())
                {
                    rec = reader.readLine();

                    characterCount += rec.length();
                    words = rec.split(" ");
                    wordCount += words.length;
                    line++;

                }

                reader.close();

                System.out.println("\nName of the file chosen: " + selectedFile.getName());
                System.out.println("Line Count: " + line);
                System.out.println("Word Count: " + wordCount);
                System.out.println("Character Count: " + characterCount);

            }

            else {
                System.out.println("Failed to choose a file.");
                System.out.println("Please try running the program again!");
                System.exit(0);
            }
        }

        catch (FileNotFoundException e)
        {
            System.out.println("Sorry that file is not found!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}