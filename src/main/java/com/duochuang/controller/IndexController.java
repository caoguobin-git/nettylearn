/***********************************************
 * File Name: IndexController
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 06 11 2019 上午 9:39
 ***********************************************/

package com.duochuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }
}
