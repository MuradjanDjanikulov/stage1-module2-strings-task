package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodParser {
    private static final Pattern METHOD_PATTERN = Pattern.compile(
            "(\\w+\\s+)?([\\w<>]+\\s+)(\\w+)\\s*\\(([^)]*)\\)"
    );

    /**
     * Parses string that represents a method signature and stores all its members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        Matcher matcher = METHOD_PATTERN.matcher(signatureString);
        if (matcher.matches()) {
            String accessModifier = matcher.group(1);
            if (accessModifier != null) {
                accessModifier = accessModifier.trim();
            }
            String returnType = matcher.group(2).trim();
            String methodName = matcher.group(3);
            String argumentsString = matcher.group(4);

            List<MethodSignature.Argument> arguments = parseArguments(argumentsString);

            MethodSignature methodSignature = new MethodSignature(methodName, arguments);
            methodSignature.setAccessModifier(accessModifier);
            methodSignature.setReturnType(returnType);

            return methodSignature;
        } else {
            throw new IllegalArgumentException("Invalid method signature: " + signatureString);
        }
    }

    private List<MethodSignature.Argument> parseArguments(String argumentsString) {
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if (!argumentsString.trim().isEmpty()) {
            String[] argumentArray = argumentsString.split(",");
            for (String argument : argumentArray) {
                String[] parts = argument.trim().split("\\s");
                if (parts.length == 2) {
                    arguments.add(new MethodSignature.Argument(parts[0], parts[1]));
                } else {
                    throw new IllegalArgumentException("Invalid argument format: " + argument);
                }
            }
        }
        return arguments;
    }
}
