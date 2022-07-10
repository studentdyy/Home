package com.dyyhub.base.designpattern.templatemode;

/**
 * @author dyyhub
 * @date 2022年06月16日 10:27
 */
public class AA extends Template{
    public AA(String name) {
        super(name);
    }
    @Override
    void job() {
        long sum = 0;
        for (int i = 0; i < 100000; i++) {
            sum+=i;
        }
    }
}
