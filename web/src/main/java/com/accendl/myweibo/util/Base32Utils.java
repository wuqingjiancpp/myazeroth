package com.accendl.myweibo.util;

import org.apache.commons.codec.binary.Base32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Base32Utils {

    private static final char[] ENCODE_TABLE = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '2', '3', '4', '5', '6', '7',
    };

    public static String generateBase32key(){
        StringBuilder sb = new StringBuilder();
        List<Integer> shuffleList = new ArrayList<>(32);
        for (int i=0;i<32;i++){
            shuffleList.add(i);
        }
        Collections.shuffle(shuffleList);
        for (int i=0;i<32;i++){
            sb.append(ENCODE_TABLE[shuffleList.get(i)]);
        }
        return sb.toString();
    }
}
