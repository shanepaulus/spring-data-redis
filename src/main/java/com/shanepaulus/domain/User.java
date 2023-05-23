package com.shanepaulus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@Entity
//@RedisHash("user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private String surname;
  private String nickName;

}
