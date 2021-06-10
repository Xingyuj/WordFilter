package ethan.code.challenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WordFilterFromFile implements WordFilter {
    public long count;
    public final List<String> wordsChosen;
    public String fileName;
    private static final String GET_M_WORD = "\\b[m|M]+[a-zA-Z]*\\b";

    public WordFilterFromFile(String fileName) {
        this.wordsChosen = new ArrayList<>();
        this.count = 0;
        this.fileName = fileName;
    }

    public static void main(String[] args) {
        String fileName = "./article";
        if (args.length > 0 && args[0].length() > 0) {
            fileName = args[0];
        }
        WordFilter wordFilter = new WordFilterFromFile(fileName);
        wordFilter.filterOut();
    }

    private List<String> findTarget(String data) {
        List<String> words = new ArrayList<>();
        Pattern p = Pattern.compile(GET_M_WORD);
        Matcher m = p.matcher(data);
        while (m.find()) {
            if (m.group(0).length() > 5) {
                words.add(m.group(0));
            }
        }
        return words;
    }

    @Override
    public void filterOut() {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> wordsChosen.addAll(findTarget(line)));
            count = wordsChosen.size();
            String output = "m words longer than 5 char are: " + wordsChosen + "\ntotal: " + count;
            System.out.println(output);
            Files.write(Paths.get("./output.txt"), output.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
