package jyp.cooksite.api.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.http.HttpStatus;
import jyp.cooksite.api.response.CommonResult;
import jyp.cooksite.api.service.ResponseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    private final MessageSource messageSource;
    //private final MessageSource messageSource;

   
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        // 예외 처리의 메시지를 MessageSource에서 가져오도록 수정
        return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.msg"));
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, UserNotFoundException e) {
        // 예외 처리의 메시지를 MessageSource에서 가져오도록 수정
//    	System.out.println("3333");
//    	System.out.println(messageSource);
//    	System.out.println(getMessage("userNotFound.code"));
//    	System.out.println("-------");
//    	System.out.println(messageSource.getMessage("userNotFound.code", null, LocaleContextHolder.getLocale()));
//    	System.out.println("-------");
        return responseService.getFailResult(Integer.valueOf(getMessage("userNotFound.code")), getMessage("userNotFound.msg"));
       // return responseService.getFailResult(-1000, "존재하지 사용자 입니다.");
    }

    // code정보에 해당하는 메시지를 조회합니다.
    private String getMessage(String code) {
    	System.out.println("1");
        return getMessage(code, null);
    }
    // code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회합니다.
    private String getMessage(String code, Object[] args) {
    	System.out.println("2");
    	
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
