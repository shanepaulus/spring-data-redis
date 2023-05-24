package com.shanepaulus.model;

import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 24-May-2023.
 */

@Data
public class UserRequest {

  private Integer id;
  private String name;
  private String surname;
  private String nickName;

}
