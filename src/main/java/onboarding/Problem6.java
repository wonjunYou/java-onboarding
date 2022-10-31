package onboarding;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Problem6 {
    public static final int MAX_NUMBER_OF_CREW = 10000;

    public static final int MIN_LENGTH_OF_EMAIL = 11;
    public static final int MAX_LENGTH_OF_FORM_LETTERS = 20;

    public static List<String> solution(List<List<String>> forms) {
        try {
            validate(forms);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static void validate(List<List<String>> forms) {
        validateFormSize(forms);
        validateEmail(forms);
        validateNickname(forms);
    }

    private static void validateFormSize(List<List<String>> forms) {
        if (forms.size() < 1 || forms.size() > MAX_NUMBER_OF_CREW) {
            throw new IllegalArgumentException("신청서가 존재하지 않거나, 너무 많습니다.");
        }
    }

    private static void validateEmail(List<List<String>> forms) {
        for (List<String> form : forms) {
            validateEmailLength(form);
            validateIsEmail(form);
        }
    }

    private static void validateNickname(List<List<String>> forms) {
        for (List<String> form : forms) {
            validateNameLength(form);
            validateIsKorean(form);
        }
    }

    private static void validateEmailLength(List<String> form) {
        String email = form.get(0);

        if (email.length() < MIN_LENGTH_OF_EMAIL || email.length() > MAX_LENGTH_OF_FORM_LETTERS) {
            throw new IllegalArgumentException("올바르지 않은 길이의 이메일입니다.");
        }
    }

    private static void validateIsEmail(List<String> form) {
        String email = form.get(0);

        if (!email.endsWith("@email.com")){
            throw new IllegalArgumentException("올바르지 않은 이메일 형식입니다.");
        }
     }

    private static void validateNameLength(List<String> form) {
        String nickname = form.get(1);

        if (nickname.length() < 1 || nickname.length() > MAX_LENGTH_OF_FORM_LETTERS) {
            throw new IllegalArgumentException("올바르지 않은 길이의 닉네임입니다.");
        }
    }

    private static void validateIsKorean(List<String> form) {
        final String regex = "^[가-힣]*$";
        String nickname = form.get(1);

        if (!nickname.matches(regex)) {
            throw new IllegalArgumentException("닉네임에 한글이 아닌 다른 문자가 포함되어 있습니다.");
        }
    }

}
