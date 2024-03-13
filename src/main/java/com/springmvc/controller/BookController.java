package com.springmvc.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.Book;
import com.springmvc.service.BookService;
import com.springmvc.validator.UnitInStockValidator;
import com.springmvc.exception.CategoryException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.springmvc.exception.BookIdException;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import com.springmvc.validator.BookValidator;

@Controller
@RequestMapping("/books") //클래스 수준의 @RequestMapping및 웹 요청 url매핑할 기본 경로 선언
public class BookController {
	@Autowired
	private BookService bookService; //유연선 확장성 up위해 서비스객체 저장소 객체에 접근
	
	//@Autowired
	//private UnitInStockValidator unitInStockValidator;
	
	@Autowired
	private BookValidator bookValidator; 
	
	//사용자의 요청이 /book인경우 requestBookList()메서드가 처리하도록 매핑 
	//@RequestMapping과 같음
	@GetMapping
	public String requestBookList(Model model) {
		List<Book> list = bookService.getAllBookList(); 
		model.addAttribute("bookList", list); //모델 데이터: 모뎅 속성 이름 bookList에 저장된 도서 목록 저장
		return "books"; //뷰 이름: books로 반환하므오 jsp파일은 books.jsp가 됨
	}
	
	
	//사용자의 요청이 /books/all인경우 requestAllBooks()메서드가 처리하도록 매핑 
	//@RequestMapping(vaule="/all", method=RequestMethod.GET) or @RequestMapping("all")과 같음
	@GetMapping("/all") 
	public ModelAndView requestAllBooks() {
		ModelAndView modelAndView = new ModelAndView(); //ModelAndView 클래스의 modelAndView 인스턴스 생성
		List<Book> list = bookService.getAllBookList(); 
		modelAndView.addObject("bookList", list); //도서 목록을 가져와 저장된 list값을 모델 속성 bookList저장
		modelAndView.setViewName("books"); //뷰 이름을 books로 설정하여 books.jsp파일 출력
		return modelAndView; //ModelAndView클래스의 modelAndView 인스턴스를 반환
	}
	
	//요청경로는 url패턴, category는 경로 변수. == @RequestMapping("/category")
	@GetMapping("/{category}")
	
	//@PathVariable("category")선언, 경로 변수 category에 대해 매개변수 이름을 bookCategory로 재정의 
	//웹 URL이 http://.../books/IT전문서 라면, bookcategory값은 IT전문서가 됨
	public String requestBooksByCategory(@PathVariable("category") String bookCategory, Model model) {
		
		//bookService.getBookListByCategory메서드를 호출, 매개변수 bookCategory와 일치하는 도서 목록을 서비스객체에서 가져와 
		//booksBycategory에 저장
		List<Book> booksBycategory = bookService.getBookListByCategory(bookCategory);
		
		if(booksBycategory == null || booksBycategory.isEmpty()) {
			throw new CategoryException();
		}
		
		//booksBycategory값을, 모델 속성 bookList에 저장
		model.addAttribute("bookList", booksBycategory);
		
		//뷰 이름인 books로 반환
		return "books";
	}
	
	@GetMapping("/filter/{bookFilter}")
	public String requestBooksByFilet(
			@MatrixVariable(pathVar="bookFilter") Map<String, List<String>> bookFilter, Model model) {
		Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
		model.addAttribute("bookList", booksByFilter);
		return "books";
	}
	
	@GetMapping("/book")
	public String requestBookById(@RequestParam("id") String bookId, Model model) {
		Book bookById = bookService.getBookById(bookId);
		model.addAttribute("book", bookById);
		return "book";
	}
	
	@GetMapping("/add")
	public String requestAddBookForm(@ModelAttribute("NewBook")Book book) {
		return "addBook";
	}
	
	@PostMapping("/add")
	public String submitAddNewBook(@Valid 
			@ModelAttribute("NewBook")Book book, BindingResult result, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			return "addBook";
		}
		
		MultipartFile bookImage = book.getBookImage();
		String saveName = bookImage.getOriginalFilename(); //이미지 파일 이름을 얻음
		File saveFile = new File("D:\\Project\\STS\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\BookMarket\\resources\\images\\", saveName); 
		if(bookImage != null && !bookImage.isEmpty()) {
			try {
				bookImage.transferTo(saveFile);
				book.setFileName(saveName);
			} catch(Exception e) {
				throw new RuntimeException("도서 이미지 업로드가 실패하였습니다.", e);
			}
		}
		
		bookService.setNewBook(book);
		return "redirect:/books";
	}
	
	@ModelAttribute
	public void addAttributes(Model model){
		model.addAttribute("addTitle","신규 도서 등록");
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(bookValidator);
		//binder.setValidator(unitInStockValidator);
		binder.setAllowedFields("bookId","name","unitPrice","author","description","publisher","category","unitInStock","totalPages","releaseDate","condition", "bookImage");
	}
	 
	@ExceptionHandler(value= {BookIdException.class})  //예외클래스 설정
	public ModelAndView handleError(HttpServletRequest req, BookIdException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidBookId", exception.getBookId()); //요청한 도서 id값 저장
		
		mav.addObject("exception", exception); //모델속성 exception에서 예외 처리 클래스 BookIdException저장
		mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		mav.setViewName("errorBook"); //뷰이름 errorBook
		return mav;
	}
	
	@GetMapping("/update")
	public String getUpdateBookForm(@ModelAttribute("updateBook")Book book, @RequestParam("id") String bookId, Model model) {
		Book bookById = bookService.getBookById(bookId);
		model.addAttribute("book", bookById);
		return "updateForm";
	}
	
	@PostMapping("/update")
	public String submitUpdateBookForm(@ModelAttribute("updateBook")Book book) {
		MultipartFile bookImage = book.getBookImage();
		String rootDirectory = "D:\\Project\\STS\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\BookMarket\\resources\\images\\";
		if (bookImage != null && !bookImage.isEmpty()) {
			try {
				String fname = bookImage.getOriginalFilename();
				bookImage.transferTo(new File ("D:\\Project\\STS\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\BookMarket\\resources\\images\\"+ fname));
				book.setFileName(fname);
			}catch(Exception e) {
				throw new RuntimeException("Book Image saving failed", e);
			}
		}
		bookService.setUpdateBook(book);
		return "redirect:/books";
	}
	
	@RequestMapping(value = "/delete")
	public String getDeleteBookForm(Model model, @RequestParam("id") String bookId) {
		bookService.setDeleteBook(bookId);
		return "redirect:/books";
	}
	
}
