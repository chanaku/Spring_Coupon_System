package com.chana.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import com.chana.beans.ResponseDto;
import com.chana.exceptions.AddCompanyException;
import com.chana.exceptions.AddCouponException;
import com.chana.exceptions.AddCustomerException;
import com.chana.exceptions.AuthorizationException;
import com.chana.exceptions.LoginException;
import com.chana.exceptions.PurchaseCouponException;
import com.chana.exceptions.ServiceException;
import com.chana.exceptions.UpdateCompanyException;

@RestController
@ControllerAdvice
public class ExceptionHandler {
	/**
	 *
	 * @param e
	 * @return
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(AddCompanyException.class)
	public ResponseEntity<?> handleAddCompanyException(AddCompanyException e){
		ResponseDto dto = new ResponseDto(false, e.getMessage());
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.FORBIDDEN);
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<?> handleAuthorizationException(AuthorizationException e){
		ResponseDto dto = new ResponseDto(false, e.getMessage());
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.FORBIDDEN);
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(AddCouponException.class)
	public ResponseEntity<?> handleAddCouponException(AddCouponException e){
		ResponseDto dto = new ResponseDto(false, e.getMessage());
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.FORBIDDEN);
		
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(AddCustomerException.class)
	public ResponseEntity<?> handleAddCustomerException(AddCustomerException e){
		ResponseDto dto = new ResponseDto(false, e.getMessage());
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.FORBIDDEN);
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(LoginException.class)
	public ResponseEntity<?> handleLoginException(LoginException e){
		ResponseDto dto = new ResponseDto(false, e.getMessage());
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.FORBIDDEN);
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(PurchaseCouponException.class)
	public ResponseEntity<?> handlePurchaseCouponException(PurchaseCouponException e){
		ResponseDto dto = new ResponseDto(false, e.getMessage());
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.FORBIDDEN);
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(ServiceException.class)
	public ResponseEntity<?> handleServiceException(ServiceException e){
		ResponseDto dto = new ResponseDto(false, e.getMessage());
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.FORBIDDEN);
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(UpdateCompanyException.class)
	public ResponseEntity<?> handleUpdateCompanyException(UpdateCompanyException e){
		ResponseDto dto = new ResponseDto(false, e.getMessage());
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.FORBIDDEN);
	}
	
}
