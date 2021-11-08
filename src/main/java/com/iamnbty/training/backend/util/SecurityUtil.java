package com.iamnbty.training.backend.util;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SecurityUtil {
    private SecurityUtil() {

    }

    public static Optional<String> getCurrentUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return Optional.empty();
        }

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();
        if (principal == null) {
            return Optional.empty();
        }

        String userId = (String) principal;
        return Optional.of(userId);
    }

    public  static String generateToken(){
        //can make password in our style
        List<CharacterRule> rules = Arrays.asList(
                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 10),

                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 10),

                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 10)
//                ,
//                //at least 5 special character
//                new CharacterRule(EnglishCharacterData.Special,5)
               );

        PasswordGenerator generator = new PasswordGenerator();

// Generated password is 12 characters long, which complies with policy
        String password = generator.generatePassword(30, rules);
        return  password;
    }
}
