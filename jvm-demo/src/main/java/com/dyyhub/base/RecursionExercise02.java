package com.dyyhub.base;

/**
 * @author dyyhub
 * @date 2022年06月06日 18:01
 * 小老鼠走迷宫
 */

public class RecursionExercise02 {
    public static void main(String[] args) {
        int[][] miGon =new int[][]{
                {1,1,1,1,1,1,1},
                {1,0,0,0,0,0,1},
                {1,0,1,0,0,0,1},
                {1,1,1,0,0,0,1},
                {1,0,0,0,0,0,1},
                {1,0,0,0,0,0,1},
                {1,0,0,0,0,0,1},
                {1,1,1,1,1,1,1}
        };
        System.out.println("============输入迷宫===============");
        for (int[] ints : miGon) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
        ZouMiGon(miGon, 1, 1);
        System.out.println("============路线图===============");
        for (int[] ints : miGon) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    public static boolean ZouMiGon(int[][] miGon,int i, int j) {
        if(miGon[6][5] == 2){
            return true;
        }else {
            if(miGon[i][j] == 0){
                //当前这个位置为0 可以走
                miGon[i][j] = 2;
                //使用找路的策略
                if(ZouMiGon(miGon,i + 1,j)){
                    return true;
                }else if (ZouMiGon(miGon,i,j + 1)){
                    return true;
                }else if (ZouMiGon(miGon,i - 1,j)){
                    return true;
                }else if(ZouMiGon(miGon,i - 1,j - 1)){
                    return true;
                }else {
                    miGon[i][j] = 3;
                    return false;
                }
            }else { //miGon[i][j] = 1,2,3
                return false;
            }
        }
    }
}
