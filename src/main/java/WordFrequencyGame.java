
import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String BLACK_SPACE = " ";
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String LINE_BREAK = "\n";

    public String getResult(String sentence) {


        if (sentence.split(SPACE_PATTERN).length==1) {
            return sentence + " 1";
        } else {

            try {

                List<Input> wordInfos = getAllWordInformation(sentence);

                return wordFrequencyResultGenerator(wordInfos);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private List<Input> getAllWordInformation(String sentence) {
        String[] words = sentence.split(SPACE_PATTERN);
        List<Input> wordInfos = new ArrayList<>();
        HashSet<String> uniqueWord = new HashSet<>(Arrays.asList(words));
        for(String word : uniqueWord) {
            wordInfos.add(new Input(word, (int) Arrays.stream(words).filter(item -> item.equals(word)).count()));
        }
        return wordInfos;
    }

    private String wordFrequencyResultGenerator(List<Input> repeatWordInfos) {
        repeatWordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
        StringJoiner joiner = new StringJoiner(LINE_BREAK);
        for (Input wordInfo : repeatWordInfos) {
            String s = wordInfo.getValue() + BLACK_SPACE +wordInfo.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

}
