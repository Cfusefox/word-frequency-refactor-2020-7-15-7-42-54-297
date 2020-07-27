import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

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

                String[] words = sentence.split(SPACE_PATTERN);

                List<Input> repeatWordInfos = new ArrayList<>();
                for (String word : words) {
                    Input input = new Input(word, 1);
                    repeatWordInfos.add(input);
                }

                Map<String, List<Input>> wordInfosMap =getListMap(repeatWordInfos);

                List<Input> wordInfos = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : wordInfosMap.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    wordInfos.add(input);
                }
                repeatWordInfos = wordInfos;

                repeatWordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

                StringJoiner joiner = new StringJoiner(LINE_BREAK);
                for (Input wordInfo : repeatWordInfos) {
                    String s = wordInfo.getValue() + BLACK_SPACE +wordInfo.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> wordInfosMap = new HashMap<>();
        for (Input input : inputList){
            if (!wordInfosMap.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                wordInfosMap.put(input.getValue(), arr);
            }
            else {
                wordInfosMap.get(input.getValue()).add(input);
            }
        }
        return wordInfosMap;
    }
}
