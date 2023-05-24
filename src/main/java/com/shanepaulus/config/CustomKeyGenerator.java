package com.shanepaulus.config;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 24-May-2023.
 */

@Component("customKeyGenerator")
public class CustomKeyGenerator implements KeyGenerator {

  @Override
  public Object generate(Object target, Method method, Object... params) {
    return method.getName() + ":" + Arrays.hashCode(params);
  }
}
