package design.uranus.study.annotation.controller;

import design.uranus.study.annotation.ann.Controller;
import design.uranus.study.annotation.ann.RequestMapping;

/**
 * Created by zhanghua.luo on 12/30/16.
 */
@Controller
public class DogController {

    @RequestMapping(value = "/test3")
    public void test3() {
        System.out.println("DogController -> test3");
    }

    @RequestMapping(value = "/test4")
    public void test4() {
        System.out.println("DogController -> test4");
    }
}
