package com.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * Created by TianYu on 2016/10/18.
 */
public class Config extends ObjectMapper {
   public Config() {
       registerModule(new Hibernate4Module());
   }
   }


