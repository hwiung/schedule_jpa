package com.example.schedule_jpa.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j      // log.info(), log.debug() 등을 사용하여 로그 기록 가능.
public class LoginFilter implements Filter {        // 로그인 여부를 검증하는 역할 수행.
    private static final String[] WHITE_LIST = {"/users", "/users/login"};  // 화이트 리스트 예외 -> 화이트 리스트에 포함된 url은 필터 로직을 생략(예외 처리)함.
    @Override
    // 로그인 여부를 검증하고, 화이트리스트에 포함되지 않은 요청만 필터링 처리.
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        log.info("로그인 필터 로직 실행");
                                                // 로그인이 돼 있는지 아닌지 검증하는 절차
        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);            // 세션이 없으면 null 반환.
            if (session == null || session.getAttribute("SESSION_KEY") == null) {   // 로그인되지 않은 걸로 간주.
                throw new RuntimeException("로그인을 하세요.");    // 로그인되지 않은 경우 RuntimeException을 던져 요청을 차단함.
            }
            log.info("로그인된 사용자 요청: {}", requestURI);        // 로그인된 사용자 요청 URI를 로그로 기록함.
        }
        filterChain.doFilter(servletRequest, servletResponse);      // 필터 검증을 통과한 요청만 다음 단계로 진행.
    }

    private boolean isWhiteList(String requestURI) {        // 요청 URI가 화이트리스트에 포함되었는지 확인하는 메서드(포함되면 true, 그렇지 않으면 false).
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);  // 요청 URI와 화이트리스트의 패턴을 비교.
    }
}