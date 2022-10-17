package com.treeleaf.authentication;

public class test {
    public static void main(String[] args) {

        System.out.println(multiplication(-2147483648,-1));
    }

    static int multiplication(int dividend, int divisor){
        if(dividend>2147483647){
            dividend=2147483647;
        }

        if(dividend<-2147483647){
            dividend=-2147483647;
        }
        if(divisor>2147483647){
            divisor=2147483647;
        }
        if(divisor<-2147483647){
            divisor=-2147483647;
        }


        int tempDividend=Math.abs(dividend);
        int x=Math.abs(dividend);
        int y=Math.abs(divisor);
        int temp=0;
        boolean flag=true;
        boolean dividendFlag=true;
        boolean divisorFlag=true;


        while(flag){
            if(x-y>=0){
                x=x-y;
                temp++;
            }
            else{
                flag=false;
            }
        }

        if(dividend+tempDividend==0){
            dividendFlag=false;
        }

        if(divisor+y==0){
            divisorFlag=false;
        }

        if(dividendFlag==false&&divisorFlag==true){
            return temp-2*temp;
        }
        else if(dividendFlag==true&&divisorFlag==false){
            return temp-2*temp;
        }
        else
            return temp;
    }

}
