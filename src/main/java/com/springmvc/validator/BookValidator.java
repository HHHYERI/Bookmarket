package com.springmvc.validator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.springmvc.domain.Book;

public class BookValidator implements Validator {

	@Autowired
	private javax.validation.Validator beanValidator;
	
	private Set<Validator> springValidators;
	
	//BookValidator클래스의 생성자
	public BookValidator() {
		springValidators = new HashSet<Validator>();
		
	}
	//springValidators의 Setter()메서드
	public void setSpringValidators(Set<Validator> springValidators) {
		this.springValidators = springValidators;
	}
	//Book클래스 유효성 검사 메서드
	public boolean supports(Class<?> clazz) {
		return Book.class.isAssignableFrom(clazz);
	}
	//Book클래스의 유효성 검사 메서드
	public void validate(Object target, Errors errors) {
		//Bean Validation 설정
		Set<ConstraintViolation<Object>> violations = beanValidator.validate(target); 
		
		//bean validation오류 저장
		for (ConstraintViolation<Object> violation : violations) {
			//오류필드저장
			
			String propertyPath = violation.getPropertyPath().toString();
			
			String message = violation.getMessage();//오류 발생 메세지 저장
			errors.rejectValue(propertyPath,"",message);
		}
		
		//spring validation 오류 저장
		for (Validator validator: springValidators) {
			validator.validate(target, errors); //발생된 오류 정보를 전달
		}
	
	}
}
