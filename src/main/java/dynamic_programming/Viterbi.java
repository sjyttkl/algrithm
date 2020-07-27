package dynamic_programming;

/**
 * Create with: dynamic_programming
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2020/7/27 11:14
 * version: 1.0
 * description: https://blog.csdn.net/fjssharpsword/article/details/72285210?locationNum=7&fps=1
 * <p>
 * 1、隐马尔可夫模型HMM
 *    学习算法，看中文不如看英文，中文喜欢描述的很高深。
 *    http://www.comp.leeds.ac.uk/roger/HiddenMarkovModels/html_dev/main.html
 *    里面有HMM定义、前向算法、维特比算法、后向算法。
 * <p>
 * <p>
 * 2、Viterbi是隐马尔科夫模型中用于确定（搜索）已知观察序列在HMM下最可能的隐藏序列。
 *    Viterb采用了动态规划的思想，利用后向指针递归地计算到达当前状态路径中的最可能（局部最优）路径。
 *    要理解，看wiki的一个动态图。
 *    https://en.wikipedia.org/wiki/File:Viterbi_animated_demo.gif
 *    求解最可能的隐状态序列是HMM的三个典型问题之一，通常用维特比算法解决。
 *    维特比算法就是求解HMM上的最短路径（-log(prob)，也即是最大概率）的算法。
 * <p>
 * <p>
 * 3、HMM五元组
 *    1)obs:观测序列，m个；
 *    2)states:隐状态,n个；
 *    3)start_p:初始概率（隐状态）
 *    4)trans_p:转移概率（隐状态），n*n矩阵，描述时间序列上的隐状态概率；
 *    5)emit_p: 发射概率 （隐状态表现为显状态的概率），n*m矩阵；
 *    要理解HMM模型的基本定义和三个基本问题。
 * <p>
 * <p>
 * <p>
 * <p>
 * 4、Viterbi算法简单实现：
 *    https://github.com/hankcs/Viterbi/tree/master/src/com/hankcs/algorithm
 *    算法本身理解是一回事，着手代码又是一回事，所以对于能够代码动手实现的，是很值得学习的，至少表明其深入理解了。
 * <p>
 *   算法中的思路是从观测的序列得到最大概率的隐状态，关键就是这一时刻的隐状态能得出下一个时刻隐状态的概率以及这一时刻显状态的概率。
 */
public class Viterbi {

    /**
     * 求解HMM模型
     *
     * @param obs     观测序列
     * @param states  隐状态
     * @param start_p 初始概率（隐状态）
     * @param trans_p 转移概率（隐状态）
     * @param emit_p  发射概率 （隐状态表现为显状态的概率）
     * @return 最可能的序列
     */
    public static int[] compute(int[] obs, int[] states, double[] start_p, double[][] trans_p, double[][] emit_p) {
        double[][] V = new double[obs.length][states.length];
        int[][] path = new int[states.length][obs.length];

        for (int y : states) {
            V[0][y] = start_p[y] * emit_p[y][obs[0]];
            path[y][0] = y;
        }

        for (int t = 1; t < obs.length; ++t) {
            int[][] newpath = new int[states.length][obs.length];

            for (int y : states) {
                double prob = -1;
                int state;
                for (int y0 : states) {
                    double nprob = V[t - 1][y0] * trans_p[y0][y] * emit_p[y][obs[t]];
                    if (nprob > prob) {
                        prob = nprob;
                        state = y0;
                        // 记录最大概率
                        V[t][y] = prob;
                        // 记录路径
                        System.arraycopy(path[state], 0, newpath[y], 0, t);
                        newpath[y][t] = y;
                    }
                }
            }

            path = newpath;
        }

        double prob = -1;
        int state = 0;
        for (int y : states) {
            if (V[obs.length - 1][y] > prob) {
                prob = V[obs.length - 1][y];
                state = y;
            }
        }

        return path[state];
    }


    public static void main(String[] args) {
        //DoctorExample
        int[] result = Viterbi.compute(DoctorExample.observations, DoctorExample.states, DoctorExample.start_probability, DoctorExample.transititon_probability, DoctorExample.emission_probability);
        for (int r : result) {
            System.out.print(DoctorExample.Status.values()[r] + " ");
        }
        System.out.println();


        // WeatherExample
        int[] result_weather = Viterbi.compute(WeatherExample.observations, WeatherExample.states, WeatherExample.start_probability, WeatherExample.transititon_probability, WeatherExample.emission_probability);
        for (int r : result_weather) {
            System.out.print(WeatherExample.Weather.values()[r] + " ");
        }

    }

}


class DoctorExample {
    enum Status {
        Healthy,//健康
        Fever,//感冒
    }

    enum Feel {
        normal,//舒服
        cold,//冷
        dizzy,//头晕
    }

    static int[] states = new int[]{DoctorExample.Status.Healthy.ordinal(), DoctorExample.Status.Fever.ordinal()};
    static int[] observations = new int[]{DoctorExample.Feel.normal.ordinal(), DoctorExample.Feel.cold.ordinal(), DoctorExample.Feel.dizzy.ordinal()};
    static double[] start_probability = new double[]{0.6, 0.4};
    static double[][] transititon_probability = new double[][]{
            {0.7, 0.3},
            {0.4, 0.6},
    };
    static double[][] emission_probability = new double[][]{
            {0.5, 0.4, 0.1},
            {0.1, 0.3, 0.6},
    };


}

class WeatherExample {
    enum Weather//隐状态
    {
        Rainy,//下雨
        Sunny,//天晴
    }

    enum Activity //显状态
    {
        walk,//散步
        shop,//购物
        clean,//清洁
    }

    static int[] states = new int[]{WeatherExample.Weather.Rainy.ordinal(), WeatherExample.Weather.Sunny.ordinal()};
    static int[] observations = new int[]{WeatherExample.Activity.walk.ordinal(), WeatherExample.Activity.shop.ordinal(), WeatherExample.Activity.clean.ordinal()};
    static double[] start_probability = new double[]{0.6, 0.4};//初始状态
    static double[][] transititon_probability = new double[][]{//转移矩阵
            {0.7, 0.3},
            {0.4, 0.6},
    };
    static double[][] emission_probability = new double[][]{//观测矩阵
            {0.1, 0.4, 0.5},
            {0.6, 0.3, 0.1},
    };


}


