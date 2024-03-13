charset utf8;
USE springmvcDB;

CREATE TABLE IF NOT EXISTS book(
	b_bookId VARCHAR(10) NOT NULL,
    b_name VARCHAR(30),
    b_unitPrice INTEGER,
    b_author VARCHAR(50),
    b_description TEXT,
    b_publisher VARCHAR(20),
    b_category VARCHAR(20),
    b_unitInStock LONG,
    b_releaseDate VARCHAR(20),
    b_lifeName VARCHAR(20),
    PRIMARY KEY (b_bookId)
)DEFAULT CHARSET=utf8;

DELETE FROM book;

INSERT INTO book VALUES('ISBN1234', 'C#교과서', '박용준', '이 책은 C# 프로그래밍 언어를 처음 배우는 분들을 위한 책입니다. 독자분들이 생애 첫 프로그래밍 언어로 C#을 배운다고 가정하고 집필했습니다.', '길벗', 'IT전문서', '1000', '2020/05/29','','ISBN1234.jpg');

INSERT INTO book VALUES('ISBN1235', 'java교과서', '조현영', '이 책은 JAVA JAVA JAVA 위한 책입니다. 독자분들이 생애 첫 프로그래밍 언어로 JAVA 을 배운다고 가정하고 집필했습니다.', '길벗', 'IT전문서', '1000', '2021/07/03','','ISBN1235.jpg');

INSERT INTO book VALUES('ISBN1236', 'sts교육', '송경화', '스프링 MVC는 웹 애플리케이션 개발 전용 프레임워크로, 스프링에서 제공하는 많은 기능을 자유롭게 확장해 사용할 수 있으며 영역별로 개발할 수 있다는 장점이 있다. 책에서는 이러한 스프링 MVC를 이용해 도서 쇼핑몰을 처음부터 끝까지 직접 만들어 볼 수 있게 구성했다. 또한, MultipartFile, RESTful 웹 서비스, 스프링 웹 플로우, 스프링 시큐리티, Log4j 등을 사용하기 때문에 다양한 스프링 기능도 함께 익힐 수 있다. 책을 따라 실습하다 보면 스프링 MVC의 개념과 원리를 자연스레 익힐 수 있을 것이다. 스프링 MVC가 처음이거나 스프링 MVC로 직접 웹 애플리케이션을 만들어 보고 싶은 분에게 추천한다.', '송송', 'IT활용서', '1000', '2023/01/01','','ISBN1236.jpg');
