package com.example.registrationlogindemo.exception;


import com.example.registrationlogindemo.dto.PasswordIncorrectExceptionDto;
import com.example.registrationlogindemo.dto.SessionNotFoundExceptionDto;
import com.example.registrationlogindemo.dto.UserNotFoundExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvicers {

   @ExceptionHandler(UserNotFoundException.class)
   public ResponseEntity<UserNotFoundExceptionDto> handleException(UserNotFoundException e){
      return  new ResponseEntity<>(new UserNotFoundExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()),HttpStatus.NOT_FOUND);

   }

   @ExceptionHandler(PasswordIncorrectException.class)
   public ResponseEntity<PasswordIncorrectExceptionDto> handleException(PasswordIncorrectException e){
      return  new ResponseEntity<>(new PasswordIncorrectExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()),HttpStatus.NOT_FOUND);

   }

   @ExceptionHandler(SessionNotFoundException.class)
   public ResponseEntity<SessionNotFoundExceptionDto> handleException(SessionNotFoundException e){
      return  new ResponseEntity<>(new SessionNotFoundExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()),HttpStatus.NOT_FOUND);
   }


}
