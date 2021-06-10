import ethan.code.challenge.WordFilterFromFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WordFilterFromFileTest {

    @Test
    public void testFilterOut(){
        WordFilterFromFile wordFilter = new WordFilterFromFile("./article");
        wordFilter.filterOut();
        Assertions.assertEquals(5, wordFilter.count);
        Assertions.assertTrue(wordFilter.wordsChosen.contains("manually"));
    }
}
