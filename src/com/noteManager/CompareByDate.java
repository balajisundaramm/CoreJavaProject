package com.noteManager;

import java.nio.file.attribute.FileTime;
import java.util.*;

/**
 * Created by Balaji on 29/11/17.
 */
public class CompareByDate{

    public static SortedSet<Map.Entry<String,FileTime>> entriesSortedByValues(Map<String,FileTime> map) {
        SortedSet<Map.Entry<String,FileTime>> sortedEntries = new TreeSet<Map.Entry<String,FileTime>>(
                new Comparator<Map.Entry<String,FileTime>>() {
                    @Override
                    public int compare(Map.Entry<String,FileTime> e1, Map.Entry<String,FileTime> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1;
                    }
                });
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }


}
