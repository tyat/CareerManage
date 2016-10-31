package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/10/31.
 */
@Service("studentService")
public class StudentService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
}
