package com.shanepaulus.exception;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }
}
