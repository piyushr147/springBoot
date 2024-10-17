package com.springBoot.springBootWeb;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @ModelAttribute("course")
    public String courseName(){
        return "Java";
    }

    @RequestMapping("/home")
    public String home(){
        return "index";
    }

    @RequestMapping("/add")
    public ModelAndView add(@RequestParam("num1") int num1,@RequestParam("num2") int num2, ModelAndView mav){
        mav.addObject("result",num1+num2);
        mav.setViewName("result");
        return mav;
    }

    @RequestMapping("/addAlien")
    public ModelAndView add(@ModelAttribute("alien1") Alien alien, ModelAndView mav){
        mav.addObject("alien",alien);
        mav.setViewName("result");
        return mav;
    }
}
