package com.medeirosdaniel.XSSM.Util;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class Formats {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public String generateCode() {
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom;
    }

    public String generateCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }

    public String convertCamelCase(String texto) {
        StringTokenizer tk = new StringTokenizer(texto, " ");
        String camelCase ="";

        while (tk.hasMoreElements()) {

            camelCase = camelCase + StringUtils.capitalize(tk.nextToken());
            if(tk.hasMoreElements() == Boolean.TRUE){
                camelCase = camelCase + " ";
            }
        }
        return camelCase;
    }

}
