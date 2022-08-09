package com.sparta.finalprojectback.statuscode;

public class ResponseMessage {
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String READ_FIND_USER = "회원 정보 중복입니다.";
    public static final String READ_FIND_EMAIL = "회원 이메일 중복입니다.";
    public static final String READ_FIND_NICKNAME = "회원 닉네임 중복입니다.";
    public static final String READ_FIND_USERNAME = "회원 유저네임 중복입니다.";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 삭제 성공";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
    public static final String DELETE_POST = "게시물 삭제 성공";
    public static final String CREATED_POST = "게시물 생성 성공";
    public static final String UPDATE_POST = "게시물 수정 성공";
    public static final String UPDATE_VIEW = "게시물 조회수 증가 성공";
    public static final String DELETE_POST_COMMENT = "게시물 댓글 삭제 성공";
    public static final String DELETE_SCHEDULE = "스케줄 삭제 성공";
    public static final String DELETE_ALL_SCHEDULE = "모든 게시물 삭제 성공";
    public static final String READ_SCHEDULE = "게시물 조회 성공";
}
