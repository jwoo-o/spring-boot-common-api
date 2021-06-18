package com.gen.api.global.contant;

import lombok.Getter;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-11-02
 * Time: 오후 2:57
 */
@Getter
public class ErrorContant {


    private ErrorContant() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 성공
     */
    public static final String SUCCESS = "0001";
    /**
     * 웹소켓 성공
     */
    public static final String WS_SUCCESS = "1001";
    /**
     * 일반적인 실패
     */
    public static final String FAIL = "E001";
    /**
     * 일반적인 웹소켓 실패
     */
    public static final String WS_FAIL = "E101";
    /**
     * 로그인 아이디 및 패스워드 오류
     */
    public static final String WRNG_LOGIN = "E002";
    /**
     * 요청권한 부족
     */
    public static final String UNAUTHORIZED = "E003";
    /**
     * 처리대상 미존재
     */
    public static final String NOT_FOUND = "E004";
    /**
     * 이미 등록된 경우
     */
    public static final String REGISTERED = "E005";
    /**
     * 필수항목 오류
     */
    public static final String EMPTY_REQUIRED = "E006";
    /**
     * 시스템오류
     */
    public static final String SYSTEM_ERROR = "E008";
    /**
     * 토큰 만료
     */
    public static final String EXF_TOKEN = "E009";
    /**
     * 접속권한 오류
     */
    public static final String NOT_ACCESS = "E010";
    /**
     * 삭제 불가
     */
    public static final String INTEGRITY_VIOLATION = "E011";

    /**
     * 라이센스 만료
     */
    public static final String NOT_FOUND_LICENSE = "E012";

    /**
     * 라이센스 pc수 초과
     */
    public static final String LICENSE_USER_OVER = "E013";
    /**
     * 부서 삭제 불과
     */
    public static final String NOT_DELETE = "E014";


    public static String getCode(String errC) {
        String msg = "";

        switch (errC) {
            case SUCCESS:
                msg = "SUCCESS";
                break;
            case WS_SUCCESS:
                msg = "WS_SUCCESS";
                break;
            case FAIL:
                msg = "FAIL";
                break;
            case WRNG_LOGIN:
                msg = "WRNG_LOGIN";
                break;
            case UNAUTHORIZED:
                msg = "UNAUTHORIZED";
                break;
            case NOT_FOUND:
                msg = "NOT_FOUND";
                break;
            case REGISTERED:
                msg = "REGISTERED";
                break;
            case EMPTY_REQUIRED:
                msg = "EMPTY_REQUIRED";
                break;
            case SYSTEM_ERROR:
                msg = "SYSTEM_ERROR";
                break;
            case EXF_TOKEN:
                msg = "EXF_TOKEN";
                break;
            case NOT_ACCESS:
                msg = "NOT_ACCESS";
                break;
            case INTEGRITY_VIOLATION:
                msg = "INTEGRITY_VIOLATION";
                break;
            case LICENSE_USER_OVER:
                msg = "LICENSE_USER_OVER";
                break;
            case NOT_DELETE:
                msg = "NOT_DELETE";
                break;
            default:
                break;
        }
        return msg;
    }

    public static String getMessage(String errC) {
        String msg = "";

        switch (errC) {
            case SUCCESS:
                msg = "성공적으로 실행되었습니다";
                break;
            case FAIL:
                msg = "요청 처리에 실패하였습니다";
                break;
            case WRNG_LOGIN:
                msg = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다";
                break;
            case UNAUTHORIZED:
                msg = "요청 권한이 없어서 처리되지 않았습니다";
                break;
            case NOT_FOUND:
                msg = "요청 대상이 존재하지 않아 처리되지 않았습니다";
                break;
            case REGISTERED:
                msg = "이미 등록되어 있습니다";
                break;
            case EMPTY_REQUIRED:
                msg = "필수 항목의 값이 올바르지 않습니다";
                break;
            case SYSTEM_ERROR:
                msg = "비정상 요청이 발생하였습니다";
                break;
            case EXF_TOKEN:
                msg = "토큰이 만료 되었습니다";
                break;
            case NOT_ACCESS:
                msg = "접속 권한이 없어서 처리되지 않았습니다";
                break;
            case INTEGRITY_VIOLATION:
                msg = "무결성 제약조건이 위배되었습니다";
                break;
            case LICENSE_USER_OVER:
                msg = "등록 가능한 PC 수를 초과하였습니다";
                break;
            case NOT_DELETE:
                msg = "해당 요청은 처리되지 않았습니다";
                break;
            default:
                break;
        }
        return msg;
    }
}
