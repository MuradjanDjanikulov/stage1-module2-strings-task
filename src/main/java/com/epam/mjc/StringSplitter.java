package com.epam.mjc;

import java.util.Collection;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collection;

public class StringSplitter {

    /**
     * Splits given string applying all delimiters to it. Keeps order of result substrings as in source string.
     *
     * @param source      source string
     * @param delimiters  collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<>();

        if (source == null || delimiters == null || delimiters.isEmpty()) {
            return result;
        }

        // Create a pattern with all delimiters, including whitespaces
        String regex = "";
        for (String delimiter : delimiters) {
            regex += Pattern.quote(delimiter.trim()) + "|";
        }
        regex = regex.substring(0, regex.length() - 1); // Remove the last "|"

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);

        int start = 0;
        while (matcher.find()) {
            result.add(source.substring(start, matcher.start()));
            start = matcher.end();
        }

        // Add the last part of the string if there's anything left
        if (start < source.length()) {
            result.add(source.substring(start));
        }

        // Remove empty strings from the result
        result.removeIf(String::isEmpty);

        return result;
    }
}
