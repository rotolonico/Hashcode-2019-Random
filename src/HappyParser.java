import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class HappyParser {

    static List<String> LoadInput(String path)
            throws IOException
    {
        return Files.readAllLines(Paths.get(path));
    }

    static void CreateOutput(String filePath, List<String> content) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, content, Charset.forName("UTF-8"));
    }
}

