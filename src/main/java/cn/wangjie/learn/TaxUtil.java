package cn.wangjie.learn;

import java.io.IOException;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2019-12-29 11:39
 **/
public class TaxUtil {
    public static void main(String[] args) throws IOException {
        TaxUtil taxUtil = new TaxUtil();
        taxUtil.calculateTax(60000,1000,1);
    }
    public void calculateTax(double annualsalary,double annualStep ,double xStep) throws IOException {
        System.out.println("年薪      最佳月薪");
        while (annualsalary < 2000000) {
            //起始计算薪资
            double x = 5000;
            //计算月薪个人所得税f(x)
            double fx = calculateIncomeTax(x);
            //计算年终奖扣税额个g(x)
            double gx = calculateBonusTax(annualsalary - 12 * x);
            //总缴税额
            double GX = 12*fx + gx;
            //当前年薪下最大月薪
            double maxX = annualsalary / 12;
            //最符合月薪，初始假定为5000
            double bestX = x;
            //最小总缴税额
            double minGX = GX;
            //递增月薪直到月薪大于最大月薪
            while (x + xStep <=maxX) {
                x += xStep;
                //新月薪对应的月薪个人所得税f(x)
                fx = calculateIncomeTax(x);
                //新月薪对应的年终奖扣税额g(x)
                gx = calculateBonusTax(annualsalary - 12 * x);
                GX = 12*fx + gx;
                //如果新月薪对应的总扣税额更小，那么更新最符合月薪和最小总缴税额的记录
                if (GX < minGX) {
                    bestX = x;
                    minGX = GX;
                }
            }
            System.out.println(annualsalary + "       " + Math.round(bestX));
            //步进年薪
            annualsalary += annualStep;
        }
    }
    //计算年终奖对应缴税额
    private double calculateBonusTax(double bonus) {
        double x = bonus / 12;
        if (x <= 3000) {
            return bonus * 0.03;
        }
        if (x <= 12000) {
            return bonus * 0.1 - 210;
        }
        if (x <= 25000) {
            return bonus * 0.2 - 1410;
        }
        if (x <= 35000) {
            return bonus * 0.25 - 2660;
        }
        if (x <= 55000) {
            return bonus * 0.3 - 4410;
        }
        if (x <= 80000) {
            return 0.35 * bonus - 7160;
        }
        return 0.45 * bonus - 15160;
    }
    //计算月薪对应缴税额
    private double calculateIncomeTax(double x) {
        double fx = 0;
        if (x <= 5000) {
            fx = 0;
        } else if (x <= 8000) {
            fx = (x - 5000) * 0.03;
        } else if (x <= 17000) {
            fx = (x - 5000) * 0.1-210;
        } else if (x <= 30000) {
            fx = (x - 5000) * 0.2-1440;
        } else if (x <= 40000) {
            fx = (x - 5000) * 0.25-2660;
        } else if (x <= 60000) {
            fx = (x - 5000) * 0.3-4410;
        } else if (x <= 85000) {
            fx = (x - 5000) * 0.35-7160;
        } else {
            fx = (x - 5000) * 0.45-15160;
        }
        return fx;
    }
}
