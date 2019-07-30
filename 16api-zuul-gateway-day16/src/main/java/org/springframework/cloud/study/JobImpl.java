package org.springframework.cloud.study;

/**
 * @Classname JobImpl
 * @Description TODO
 * @Author xiexiaobiao
 * @Date 2019-05-29 18:00
 * @Version 1.0
 **/

public class JobImpl implements IJob {

    @Override
    public void pringStr() {
        System.out.println("hello java");
    }
}
