package com.springmvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import com.springmvc.domain.Book;

@Component
public class UnitInStockValidator implements Validator {
	public boolean supports(Class<?> clazz) {//Book클래스의 유효성 검사 여부를 위한 메서드
		return Book.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {//Book클래스의 유효성 검사 메서드
		Book book = (Book) target;
		if (book.getUnitPrice() >= 10000 && book.getunitInStock()>99) {//오류 객체의 속성과 메시지 저장
			errors.rejectValue("unitInStock", "UnitInStockValidator.message");
		}
	}
}
