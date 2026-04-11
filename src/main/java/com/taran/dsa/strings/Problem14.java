package com.taran.dsa.strings;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Problem 14: Find the first non-repeating character.
 * TODO: Implement solution.
 */
public class Problem14 {

    public static void main(String[] args) {
        String text = "siss";
        char[] words = text.toCharArray();
        Map<Character, Integer> valuemap = new LinkedHashMap<>();
        for(char word: words){
            int count = 1;
            if(!valuemap.containsKey(word)){
                valuemap.put(word, count);
            }else{
                count = valuemap.get(word);
                count++;
                valuemap.put(word, count);
            }
        }

        for(Map.Entry<Character, Integer> entry: valuemap.entrySet()){
            if(entry.getValue() == 1){
                System.out.println("Is it non-repeating character? " + entry.getKey());
                break;
            }
        }



        System.out.println("Sample input: " + text);
        // Expected output example: w
    }
}
