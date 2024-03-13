package com.springmvc.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.springmvc.domain.Book;
import com.springmvc.exception.BookIdException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {

	private JdbcTemplate template;
	
	@Autowired
	public void setJdbctemplate(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	private List<Book> listOfBooks = new ArrayList<Book>();
	
	//기본 생성자: 도메인 객체에서 정의된 모든 필드 값을 설정
	public BookRepositoryImpl() {
		Book book1 = new Book("ISBN1234", "#C 교과서", 30000);
		book1.setAuthor("박영준");
		book1.setDescription("이 책은 C언어의 전반적인 내용을 다루면서도 프로젝트를 기반으로 구성되어 있다. 각 장의 서두에 프로젝트의 내용을 설명하고, 이 프로젝트를 완성하기 위한 각 단계별 기술들을 하나씩 설명하고 있다.");
		book1.setPublisher("길벗");
		book1.setCategory("IT전문서");
		book1.setunitInStock(1000);
		book1.setReleaseDate("2020/05/29");
	
		Book book2 = new Book("ISBN1235", "java교과서", 36000);
		book2.setAuthor("조현영");
		book2.setDescription("PART 1. 자바 언어의 이해\r\n" + 
				"1. 자바언어의개요\r\n" + 
				"2. 자바언어의기본문법\r\n" + 
				"\r\n" + 
				" \r\n" + 
				"\r\n" + 
				"PART 2. 객체지향과 클래스\r\n" + 
				"1. 객체지향의개요\r\n" + 
				"2. 클래스\r\n" + 
				"3. 상속\r\n" + 
				"4. 추상클래스와인터페이스");
		book2.setPublisher("길벗");
		book2.setCategory("IT전문서");
		book2.setunitInStock(1000);
		book2.setReleaseDate("2021/07/03");
	
		Book book3 = new Book("ISBN1236", "sts교육서", 45000);
		book3.setAuthor("송경화");
		book3.setDescription("스프링 MVC는 웹 애플리케이션 개발 전용 프레임워크로, 스프링에서 제공하는 많은 기능을 자유롭게 확장해 사용할 수 있으며 영역별로 개발할 수 있다는 장점이 있다. 책에서는 이러한 스프링 MVC를 이용해 도서 쇼핑몰을 처음부터 끝까지 직접 만들어 볼 수 있게 구성했다. 또한, MultipartFile, RESTful 웹 서비스, 스프링 웹 플로우, 스프링 시큐리티, Log4j 등을 사용하기 때문에 다양한 스프링 기능도 함께 익힐 수 있다. 책을 따라 실습하다 보면 스프링 MVC의 개념과 원리를 자연스레 익힐 수 있을 것이다. 스프링 MVC가 처음이거나 스프링 MVC로 직접 웹 애플리케이션을 만들어 보고 싶은 분에게 추천한다.");
		book3.setPublisher("송송");
		book3.setCategory("IT활용서");
		book3.setunitInStock(1000);
		book3.setReleaseDate("2023/01/01");
	
		listOfBooks.add(book1);
		listOfBooks.add(book2);
		listOfBooks.add(book3);
		
	}
	
	
	//도서 목록 가져오기. 저장된 도서 목록의 정보를 가져오는 getAllBookList()메서드 작성
	@Override
	public List<Book> getAllBookList() {
		String SQL = "SELECT * FROM book";
		List<Book> listOfBooks = template.query(SQL, new BookRowMapper());
		return listOfBooks;
	}

	//도서 분야와 일치하는 도서 목록을 가져오는 메서드
	public List<Book> getBookListByCategory(String category){
		
		List<Book> booksByCategory = new ArrayList<Book>(); //일치하는 도서 분야를 저장하는 객체변수 booksByCategory선언

		//		for (int i = 0; i < listOfBooks.size(); i++ ) {
//			Book book = listOfBooks.get(i); //book에 도서 목록의 i번째 저장
//			
//			//대소문자 관계없이 매개변수 category와 도서 분야가 일치하는 도서 목록 i번째 정보 저장
//			if (category.equalsIgnoreCase(book.getCategory()))
//				booksByCategory.add(book);
//		}
		
		String SQL = "SELECT * FROM book where b_category LIKE '%" + category + "%'";
		booksByCategory = template.query(SQL, new BookRowMapper());
		
		return booksByCategory; //매개변수 category와 일치하는 도서 목록 반환
 	}
	
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter){
		Set<Book> booksByPublisher = new HashSet<Book>();
		Set<Book> booksByCategory = new HashSet<Book>();
		
//		Set<String> booksByFliter = filter.keySet();
//		
//		if (booksByFliter.contains("publisher")) {
//			for(int j = 0; j < filter.get("publisher").size(); j++) {
//				String publisherName = filter.get("publisher").get(j);
//				for(int i = 0; i < listOfBooks.size(); i++) {
//					Book book = listOfBooks.get(i);
//					
//					if(publisherName.equalsIgnoreCase(book.getPublisher()))
//						booksByPublisher.add(book);
//				}
//			}
//		}
//		
//		if (booksByFliter.contains("category")) {
//			for(int i = 0; i < filter.get("category").size(); i++) {
//				String category = filter.get("category").get(i);
//				List <Book> list = getBookListByCategory(category);
//				booksByCategory.addAll(list);
//			}
//		}
		
		Set<String> criterias = filter.keySet();
		if(criterias.contains("publisher")) {
			for(int j = 0; j < filter.get("publisher").size(); j++) {
				String publisherName = filter.get("publisher").get(j);
				String SQL =  "SELECT * FROM book where b_publisher LIKE '%" + publisherName + "%'";
				booksByPublisher.addAll(template.query(SQL, new BookRowMapper()));
			}
		}
		if(criterias.contains("category")) {
			for(int i = 0; i < filter.get("category").size(); i++) {
				String category = filter.get("category").get(i);
				String SQL =  "SELECT * FROM book where b_category LIKE '%" + category + "%'";
				booksByPublisher.addAll(template.query(SQL, new BookRowMapper()));
			}
		}
	
		booksByCategory.retainAll(booksByPublisher);
		return booksByCategory;
	
	}
	
	//book목록 중 검색조건이 id와 일치하는 도서 조회
	public Book getBookById(String bookId) {
		Book bookInfo = null;
		String SQL = "SELECT count(*) FROM book where b_bookId=?";
		int rowCount = template.queryForObject(SQL, Integer.class, bookId);
		
//		for (int i = 0; i < listOfBooks.size(); i++ ) {
//			Book book = listOfBooks.get(i);
//			if(book != null && book.getBookId() != null && book.getBookId().equals(bookId)) {
//				bookInfo = book;
//				break;
//			}
//		}
		
		if(rowCount !=0 ) {
			SQL = "SELECT * FROM book where b_bookId=?";
			bookInfo = template.queryForObject(SQL, new Object[] {bookId}, new BookRowMapper());
		}
		
		if (bookInfo == null)
			throw new BookIdException(bookId);
		return bookInfo;
	}
	
	public void setNewBook(Book book) {
		String SQL = "INSERT INTO book (b_bookId, b_name, b_unitPrice, b_author, b_description, b_publisher, b_category, b_unitInStock, b_releaseDate, b_condition, b_fileName)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		template.update(SQL, book.getBookId(), book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getunitInStock(), book.getReleaseDate(), book.getCondition(), book.getFileName());
	}
	
	public void setUpdateBook(Book book) {
		if(book.getFileName() != null) {
			String SQL ="UPDATE Book SET b_name = ?, b_unitPrice = ?, b_author = ?, b_description = ?, b_publisher = ?, b_category = ?, b_unitInStock = ?, b_releaseDate = ?, b_condition = ?, b_fileName = ? where b_bookId = ? ";
			template.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getunitInStock(), book.getReleaseDate(), book.getCondition(), book.getFileName(), book.getBookId());
		}else if(book.getFileName() == null) {
			String SQL ="UPDATE Book SET b_name = ?, b_unitPrice = ?, b_author = ?, b_description = ?, b_publisher = ?, b_category = ?, b_unitInStock = ?, b_releaseDate = ?, b_condition = ? where b_bookId = ? ";
			template.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getunitInStock(), book.getReleaseDate(), book.getCondition(), book.getBookId());
		}
	}
	
	public void setDeleteBook(String bookId) {
		String SQL = "DELETE from Book where b_bookId = ? ";
		this.template.update(SQL, bookId);
	}
	
}
