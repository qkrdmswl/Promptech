package com.DBproject.DBproject.exception;

public class AlreadyRegisteredIdException extends Exception{
        public AlreadyRegisteredIdException() {
            super("중복된 로그인 ID입니다. 다른 아이디를 등록하세요 ");
        }
}
