package com.jaagdeesh.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class UserApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<UserApiError> handleUserNotFoundException(UserNotFoundException unfe) {
		UserApiError userApiError = new UserApiError(HttpStatus.NOT_FOUND, unfe);
		ResponseEntity<UserApiError> rep = new ResponseEntity(userApiError, HttpStatus.NOT_FOUND);
		return rep;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<UserApiError> handleAllException(Exception ex) {
		UserApiError userApiError = new UserApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
	//	ResponseEntity<UserApiError> rep = new ResponseEntity(userApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	//	return rep;
		return new ResponseEntity(userApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<UserApiError> handleUserCreationException(UserCreationException uce) {
		UserApiError userApiError = new UserApiError(HttpStatus.BAD_REQUEST, uce);
		ResponseEntity<UserApiError> rep = new ResponseEntity(userApiError, HttpStatus.BAD_REQUEST);
		return rep;
	}

//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		UserBeanValidationErrorList validationErrorList = new UserBeanValidationErrorList();
//		List<UserApiError> errorsList=new ArrayList<UserApiError>();
//		
//		ex.getBindingResult().getAllErrors().stream().forEach(objectError->{
//			UserApiError userApiError=new UserApiError(objectError.getDefaultMessage(), objectError.getCode(), LocalDateTime.now());
//			errorsList.add(userApiError);
//		});
//		validationErrorList.setErrorList(errorsList);
//
//		return new ResponseEntity<Object>(validationErrorList,HttpStatus.BAD_REQUEST);
//	}
}
