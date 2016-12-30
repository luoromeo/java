package design.uranus.study.annotation.controller;

import design.uranus.study.annotation.ann.Controller;
import design.uranus.study.annotation.ann.RequestMapping;

/**
 * Created by zhanghua.luo on 12/30/16.
 */
@Controller
public class CatController {

    @RequestMapping(value = "/test1")
    public void test1() {
        System.out.println("catController -> test1()");
    }

    @RequestMapping(value = "/test2")
    public void test2() {
        System.out.println("catController -> test2()");
    }
}
