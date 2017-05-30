package com.spring.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ValidExistsConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidExists {

	
	String columnNames() default "";

	String tableNames() default "";

	String message() default "Hạng mục đã tồn tại trong database";

	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	 
}