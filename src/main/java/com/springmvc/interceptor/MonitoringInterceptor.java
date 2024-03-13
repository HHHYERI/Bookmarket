package com.springmvc.interceptor;

import java.text.DateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.HandlerInterceptor;

public class MonitoringInterceptor implements HandlerInterceptor{
	
	ThreadLocal<StopWatch> stopWatchLocal = new ThreadLocal<StopWatch>();
	
	public Logger logger = LoggerFactory.getLogger(this.getClass()); //Logger객체부름
	
	//MonitoringInterceptor인터페이스 메서드 컨트롤러 호출 전 실행
	public boolean preHandle(HttpServletRequest request, 
							HttpServletResponse response,
							Object handler) throws Exception {
		StopWatch stopWatch = new StopWatch(handler.toString());
		stopWatch.start(handler.toString());
		stopWatchLocal.set(stopWatch);
		logger.info("접근 가능한 URL 경로: " + getURLPath(request));
		logger.info("요청 처리 시작 시각 " + getCurrentTime());
		return true;
	}

	public void postHandle(HttpServletRequest arg0,
						HttpServletResponse response,
						Object handler,
						ModelAndView modelAndView) throws Exception {
		logger.info("요청 처리 종료 시각: " + getCurrentTime());
	}
		
	//MonitoringInterceptor인터페이스 메서드. 컨트롤러 처리 후 실행
	public void afterCompletion(HttpServletRequest request, 
								HttpServletResponse response,
								Object handler, 
								
								//뷰에 최종결과 반환 후 실행
								Exception exception) throws Exception {
		StopWatch stopWatch = stopWatchLocal.get();
		stopWatch.stop();
		logger.info("요청 처리 소요 시간: " + stopWatch.getTotalTimeMillis() + "ms");
		stopWatchLocal.set(null);
		
		logger.info("============================");
	}
	
	//요청 url 쿼리문을 얻어오는 메서드 
	private String getURLPath(HttpServletRequest request) {
		String currentPath = request.getRequestURI();
		String queryString = request.getQueryString();
		queryString = queryString == null ? "" : "?" + queryString;
		return currentPath + queryString;
	}
	
	private String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}
}
