//package com.example.growith.supportservice;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//import java.util.regex.Pattern;
//
////현재 미사용
//public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
//    private String regexp;
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        boolean result = Pattern.matches(regexp, value);
//        return result;
//    }
//
//    @Override
//    public void initialize(PhoneNumber constraintAnnotation) {
//        this.regexp = constraintAnnotation.regexp();
//    }
//}
