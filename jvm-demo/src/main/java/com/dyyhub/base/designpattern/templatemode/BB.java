package com.dyyhub.base.designpattern.templatemode;

/**
 * @author dyyhub
 * @date 2022年06月16日 10:29
 */
public class BB extends Template{
    public BB(String name) {
        super(name);
    }
    @Override
    void job() {
        long sum = 0;
        for (int i = 1; i < 500000; i++) {
            sum*=i;
        }
    }
}
