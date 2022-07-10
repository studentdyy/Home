package com.dyyhub.base.designpattern.singlemod;

/**
 * @author dyyhub
 * @date 2022年06月15日 17:39
 */
public class SingleTon {
}

class GriFriend{
    private static GriFriend griFriend = new GriFriend();
    private GriFriend(){}
    public static GriFriend getInstance(){return griFriend;}
}
class BoyFriend{
    private static BoyFriend boyFriend;
    private BoyFriend(){}
    public static BoyFriend getInstance(){
        if (boyFriend == null){
            boyFriend = new BoyFriend();
        }
        return boyFriend;
    }
}