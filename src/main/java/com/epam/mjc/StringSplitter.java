package com.epam.mjc;

import java.util.Collection;
import java.util.List;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSplitter {

    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<>();

        if (source == null || delimiters == null || delimiters.isEmpty()) {
            return result;
        }

        String regex = "";
        for (String delimiter : delimiters) {
            regex += Pattern.quote(delimiter.trim()) + "|";
        }
        regex = regex.substring(0, regex.length() - 1);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);

        int start = 0;
        while (matcher.find()) {
            result.add(source.substring(start, matcher.start()));
            start = matcher.end();
        }

        if (start < source.length()) {
            result.add(source.substring(start));
        }

        result.removeIf(String::isEmpty);

        return result;
    }
}
