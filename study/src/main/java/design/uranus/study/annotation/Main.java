package design.uranus.study.annotation;

import design.uranus.study.annotation.ann.Controller;
import design.uranus.study.annotation.util.HandlerAnnotationFactoryBean;

import java.util.List;

/**
 * Created by zhanghua.luo on 12/30/16.
 */
public class Main {
    public static void main(String[] args) {
        List<Class<?>> classesList = null;
        classesList = HandlerAnnotationFactoryBean.getPackageController("design.uranus.study.annotation", Controller.class);

    }
}
