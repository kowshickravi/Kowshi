package com.ford.fbms.foe.uscwers.controller;

import com.ford.fbms.foe.uscwers.transport.GenericResponse;
import com.ford.fbms.foe.uscwers.util.LoggerBuilder;
import com.ford.fbms.foe.uscwers.util.ResponseBuilder;
import com.ford.fbms.foe.uscwers.util.ResponseCodes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class for catching runtime exceptions from the application.
 *
 * @author PSENTHIK on 2/16/2021.
 */
@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

  private final String className = this.getClass().getSimpleName();

  @Autowired
  private ResponseBuilder responseBuilder;


  /**
   * Exception thrown internally when http method not found.
   */
  @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public GenericResponse handleHttpRequestMethodNotSupportedException(
      final HttpServletRequest request, final HttpServletResponse response,
      final Exception exception) {
    responseBuilder.storeRequestHeaderAttributes(request);
    responseBuilder.setResponseHeaderAttributes(response);

    final GenericResponse genericResponse =
        responseBuilder.generateResponse(ResponseCodes.UNSUPPORTED_METHOD_ERROR);

    LoggerBuilder.printError(log, logger -> logger.className(className)
        .methodName("handleHttpRequestMethodNotSupportedException")
        .exception(exception));
    return genericResponse;
  }

  /**
   * Exception thrown internally when access denied.
   */
  @ExceptionHandler(AccessDeniedException.class)
  public GenericResponse handleAccessDeniedException(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final Exception exception) {
    responseBuilder.storeRequestHeaderAttributes(request);
    responseBuilder.setResponseHeaderAttributes(response);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());

    final GenericResponse genericResponse =
        responseBuilder.generateResponse(ResponseCodes.UNAUTHORIZED_ERROR);

    LoggerBuilder.printError(log, logger -> logger.className(className)
        .methodName("handleAccessDeniedException").exception(exception));
    return genericResponse;
  }

  /**
   * Exception thrown internally when required parameter is missing.
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public GenericResponse handleMissingParams(final HttpServletRequest request,
                                             final HttpServletResponse response,
                                             final Exception exception) {
    responseBuilder.storeRequestHeaderAttributes(request);
    responseBuilder.setResponseHeaderAttributes(response);

    final GenericResponse genericResponse =
        responseBuilder.generateResponse(ResponseCodes.INVALID_REQUEST_URL);

    response.setStatus(genericResponse.getHttpStatus().value());

    LoggerBuilder.printError(log,
        logger -> logger.className(className).methodName("handleMissingParams")
            .exception(exception));
    return genericResponse;
  }

  /**
   * Exception thrown internally when general exception occurred.
   */
  @ExceptionHandler(Exception.class)
  public GenericResponse handleException(final HttpServletRequest request,
                                         final HttpServletResponse response,
                                         final Exception exception) {
    responseBuilder.storeRequestHeaderAttributes(request);
    responseBuilder.setResponseHeaderAttributes(response);

    final GenericResponse genericResponse =
        responseBuilder.generateResponse(ResponseCodes.INTERNAL_SERVER_ERROR);
    genericResponse.setMsgDesc(exception.getMessage());
    response.setStatus(genericResponse.getHttpStatus().value());
    LoggerBuilder.printError(log, logger -> logger.className(className)
        .methodName("handleException").exception(exception)
        .message(exception.getMessage()));
    return genericResponse;
  }

  /**
   * Exception thrown internally when ConstraintViolationexception occurred.
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public GenericResponse handleConstraintViolationException(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final ConstraintViolationException exception) {

    responseBuilder.storeRequestHeaderAttributes(request);
    responseBuilder.setResponseHeaderAttributes(response);
    final GenericResponse genericResponse =
        responseBuilder.generateResponse(ResponseCodes.BINDING_ERROR);
    final StringBuilder stringBuilder = new StringBuilder();
    for (ConstraintViolation<?> violation : exception
        .getConstraintViolations()) {
      final ConstraintViolationImpl<?> constraintViolation =
          (ConstraintViolationImpl<?>)
              violation;
      stringBuilder.append(constraintViolation.getMessage()).append(',');
    }
    genericResponse.setMsgDesc(stringBuilder.toString());
    LoggerBuilder.printError(log, logger -> logger.className(className)
        .methodName("handleConstraintViolationExceptions")
        .message(exception.getMessage()).exception(exception));
    response.setStatus(genericResponse.getHttpStatus().value());
    return genericResponse;
  }
}
