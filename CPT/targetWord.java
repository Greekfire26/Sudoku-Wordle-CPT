package CPT;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class targetWord {
    public static String[] wordlist = {
            "about", "above", "award", "alarm", "after", "abuse", "anger", "agent", "adult", "apple",
            "below", "blame", "buyer", "board", "beach", "break", "basis", "birth", "block", "brain", "bread", "brown",
            "cause", "chair", "cover", "crowd", "cycle", "chain", "chest", "chief", "child", "claim", "class", "clock", "coach", "coast", "court", "cream", "crime", "cross", "crown",
            "dance", "doubt", "dream", "drive", "death", "depth", "doubt", "draft", "drama", "dress", "drink",
            "error", "event", "enemy", "earth", "entry",
            "field", "fault", "fight", "floor", "fruit", "front", "faith", "focus", "frame", "fruit",
            "grass", "green", "group", "guide", "glass", "grant",
            "heart", "horse", "hotel", "house",
            "image", "input", "issue", "index",
            "judge",
            "knife",
            "layer", "level", "light", "limit", "lunch",
            "match", "metal", "money", "month", "mouth", "music", "major", "march", "model", "motor",
            "night", "noise", "north", "novel", "nurse",
            "offer", "order", "other", "owner",
            "paper", "party", "peace", "phone", "plane", "price", "proof", "plant", "panel", "phase", "piece", "pilot", "place", "point", "pound", "power", "press", "pride", "prize",
            "queen",
            "radio", "range", "ratio", "range", "reply", "river", "round", "right", "route",
            "scale", "scene", "sense", "shape", "shift", "shirt", "skill", "smoke", "smile", "sound", "space", "scope", "score", "share", "sheep", "shock", "south", "speed", "sport", "squad", "staff", "stage", "start", "steam", "stock", "stone", "sugar", "store", "stuff", "soare", "scent",
            "table", "taste", "thing", "total", "touch", "train", "trend", "trust", "tower", "theme", "title", "track", "trade", "truth",
            "uncle", "union", "unity",
            "value", "video", "visit", "voice",
            "waste", "watch", "water", "while", "white", "whole", "world", "woman",
            "youth", "young",
            "quick", "quite", "quiet",
    };

    public String word;
    public int attempts;

    public targetWord(){ // define target word constructor, this function is run automatically whenever an instance of this class is created
        word = wordlist[new Random().nextInt(wordlist.length)]; // pick a random word from the word pool
        attempts = 6; // set the attempts to 6
    }

    public int[] checkGuess(ArrayList<Character> guessArrayList){

        Character[] correctArray = new Character[5]; // create a new empty array of characters of length 5
        for (int i = 0; i < correctArray.length; i++){correctArray[i] = word.charAt(i);} // loop through correctArray and set the current index to the value of the same index in the target word

        int[] comparison = {1, 1, 1, 1, 1}; // create our comparison array and set all values to start as 1

        for (int i = 0; i < guessArrayList.size(); i++) { // loop through each letter in guessArrayList
            if (guessArrayList.get(i).equals(correctArray[i])) { // if the letter is in the same spot in both arrays
                comparison[i] = 3; // set the corresponding index of the comparison array to 3

            } else if (Arrays.asList(correctArray).contains(guessArrayList.get(i))) { // if the letter is in the target word but not the same spot
                comparison[i] = 2; // set the corresponding index of the comparison array to 2
            }
        }
        return comparison; // return the comparison array
    }
}