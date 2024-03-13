package com.springmvc.service;


import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springmvc.domain.Book;
import com.springmvc.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	//도서 목록 가져오기. 저장된 도서 목록의 정보를 가져오는 getAllBookList()메서드 작성
	public List<Book> getAllBookList() {
		// TODO Auto-generated method stub
		return bookRepository.getAllBookList();
	}
	
	//도서 분야와 일치하는 도서 목록을 [저장소 객체]에서 가져오는 메서드
	public List<Book> getBookListByCategory(String category) {
		
		//매개변수 category와 일치하는 도서 목록을 가져와 booksByCategory에 저장
		List<Book> booksByCategory = bookRepository.getBookListByCategory(category);
		
		//반환
		return booksByCategory;
	}

	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		Set<Book> booksByFilter = bookRepository.getBookListByFilter(filter);
		
		return booksByFilter;
		}
	
	public Book getBookById(String bookId) {
		Book bookById = bookRepository.getBookById(bookId);
		return bookById;
	}
	public void setNewBook(Book book) {
		bookRepository.setNewBook(book);
	}
	public void setUpdateBook(Book book) {
		bookRepository.setUpdateBook(book);
	}
	
	public void setDeleteBook(String bookId) {
		bookRepository.setDeleteBook(bookId);
	}
}
