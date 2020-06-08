import java.util.*;
import java.util.stream.Collectors;

/**
 * 126. 单词接龙 II
 * https://leetcode-cn.com/problems/word-ladder-ii/
 */
public class FindLadders {

    public boolean isAvailable(String word, String listWord) {
        int num = 0;
        for (int i = 0; i < listWord.length() && num <= 1; i++) {
            if (word.charAt(i) != listWord.charAt(i)) {
                num++;
            }

        }

        return num == 1;
    }

    public List<String> getWords(String word, List<String> wordList) {
        return wordList.stream().filter(t -> isAvailable(word, t)).collect(Collectors.toList());
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return Collections.emptyList();
        }

        List<String> list = new ArrayList<>();
        list.add(beginWord);
        Map<String, List<String>> mapper = new LinkedHashMap<>(16);
        mapper.put(beginWord, getWords(beginWord, wordList));

        for (int i = 0; i < list.size(); i++) {
            for (String word : mapper.get(list.get(i))) {
                if (!list.contains(word)) {
                    List<String> words = getWords(word, wordList);
                    words.removeAll(mapper.keySet());
                    list.add(word);
                    mapper.put(word, words);
                }
            }
        }

        System.out.println(mapper);

        List<List<String>> result = new ArrayList<>();
        list.clear();
        list.add(beginWord);


        List<String> tempList = new ArrayList<>();
        while (!list.isEmpty()) {
            String word = list.remove(0);
            tempList.add(word);
            if(word.equals(endWord)){
                result.add(new ArrayList<>(tempList));
            }
            list.addAll(0, mapper.get(word));
        }

        return result;
    }

    /**
     * {hit=[hot], hot=[dot, lot], dot=[dog, lot], lot=[log], dog=[log, cog], log=[cog]}
     * hit=[hot=[dot=[dog=[log=[cog], cog], lot=[log=[cog]]], lot=[log=[cog]]]]
     * hit, hot, dot, dog, cog
     * hit, hot, dot, dog, log, cog
     * hit, hot, dot, lot, log, cog
     * hit, hot, lot, log, cog
     * @param args
     */
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "cog", "dog", "dot", "lot", "log");
        List<List<String>> ladders = new FindLadders().findLadders(beginWord, endWord, wordList);
        System.out.println(ladders);
    }

}