package com.control;

import com.service.EmpService;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2016/10/31.
 */
@Controller
public class StudentCtrl {
    @Autowired
    private StudentService studentService;

}
