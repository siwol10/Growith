//package com.example.growith.supportservice;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
////현재 미사용
//@Constraint(validatedBy = {PhoneNumberValidator.class})
//@Target(ElementType.FIELD)
//@Retention(RetentionPolicy.RUNTIME)
//public @interface PhoneNumber {
//    String message() default "전화번호 양식을 맞춰 주세요(ex: 000-0000-0000.)";
//    String regexp() default "^\\d{2,3}-\\d{3,4}-\\d{4}$";
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//}
