package dynamic_programming;

/**
 * Create with: dynamic_programming
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2020/7/27 11:14
 * version: 1.0
 * description: https://blog.csdn.net/fjssharpsword/article/details/72285210?locationNum=7&fps=1
 * https://blog.csdn.net/qq_44766883/article/details/113355126?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1-113355126-blog-123123660.pc_relevant_landingrelevant&spm=1001.2101.3001.4242.2&utm_relevant_index=4
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
 * https://www.zhihu.com/question/20136144
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
 * <p> 1. 概率计算问题
 * <p> 2.学习问题
 * <p> 3.预测问题，也称为解码问题。
 * <p>
 * 4、Viterbi算法简单实现：
 * 吗                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        http://www.hankcs.com/nlp/hmm-and-segmentation-tagging-named-entity-recognition.html
 *    https://github.com/hankcs/Viterbi/tree/master/src/com/hankcs/algorithm
 *    算法本身理解是一回事，着手代码又是一回事，所以对于能够代码动手实现的，是很值得学习的，至少表明其深入理解了。
 * <p>
 *   算法中的思路是从观测的序列得到最大概率的隐状态，关键就是这一时刻的隐状态能得出下一个时刻隐状态的概率以及这一时刻显状态的概率。
 */
public class HMM {
    //向前算法
    public static double forward(int[] observation, int[] states, double[] start_p, double[][] trans_p, double[][] emit_p) {
        double[][] V = new double[observation.length][states.length]; //obs * states 记录表示 当前 观察序列情况下，最优概率是多少？路径保存在下面
        for (int state1 : states) {
            V[0][state1] = start_p[state1] * emit_p[state1][observation[0]];
        }
        //依次每个观察序列，并计算当前观察序列值 下，每个隐藏层的最大概率，最终记录最优路径
        for (int obs = 1; obs < observation.length; ++obs) {
            //遍历每个隐状态，计算状态转移到当前状态的概率,得到最大概率状态
            for (int targetState : states) {
                double nProbSum = 0.0;
                for (int preState : states) { //这里表示的是前一个状态
                    double nProb = V[obs - 1][preState] * trans_p[preState][targetState]; //* emit_p[targetState][observation[obs]];
                    nProbSum = nProb + nProbSum;
                }
                nProbSum = nProbSum * emit_p[targetState][observation[obs]];
                V[obs][targetState] = nProbSum;
            }
        }
        //最后一步，
        double result = 0.0;
        for (int i = 0; i < V[V.length - 1].length; i++) {
            result += V[V.length-1][i];
        }
        return result;
    }

    //向后算法
    public static double backward(int[] observation, int[] states, double[] start_p, double[][] trans_p, double[][] emit_p) {
        double[][] V = new double[observation.length][states.length]; //obs * states 记录表示 当前 观察序列情况下，最优概率是多少？路径保存在下面
        for (int state1 : states) {
            //向后算法，从最后一行开始计算，从后向前,并且都设置为 1
            V[V.length-1][state1] = 1 ;//start_p[state1] * emit_p[state1][observation[0]];
        }
        //依次每个观察序列，并计算当前观察序列值 下，每个隐藏层的最大概率，最终记录最优路径
        for (int obs = observation.length-2; obs >= 0; --obs) {
            //遍历每个隐状态，计算状态转移到当前状态的概率,得到最大概率状态
            for (int targetState : states) {
                double nProbSum = 0.0;
                for (int preState : states) { //这里表示的是前一个状态
                    double nProb = V[obs +1][preState] * trans_p[targetState][preState] * emit_p[preState][observation[obs+1]]; //* emit_p[targetState][observation[obs]];
                    nProbSum = nProb + nProbSum;
                }
                V[obs][targetState] = nProbSum;
            }
        }
        //最后一步，
        double result = 0.0;
        for (int i = 0; i < V[0].length; i++) {

            result += V[0][i] * start_p[i] * emit_p[i][observation[0]];
        }
        return result;
    }

    /**
     * 求解HMM模型 中的 第三个问题，（预测问题）解码，给定模型和观察序列，求最又有可能的状态序列
     *
     * @param observation 观测序列
     * @param states      隐状态
     * @param start_p     初始概率（隐状态）
     * @param trans_p     转移概率（隐状态）
     * @param emit_p      发射概率 （隐状态表现为显状态的概率）
     * @return 最可能的序列
     * 计算得到最大概率的隐状态，然后保存最佳状态转移位置。对于每个观察值，先计算对应的可能的隐状态
     * 在当前 观察序列 的情况下，最优的隐藏序列是多少？
     */
    public static int[] viterbi(int[] observation, int[] states, double[] start_p, double[][] trans_p, double[][] emit_p) {
        double[][] V = new double[observation.length][states.length]; //obs * states 记录表示 当前 观察序列情况下，最优概率是多少？路径保存在下面
        int[][] path = new int[states.length][observation.length]; // states * obs  ,由上个概率值，计算最优路径，表示当前状态下，最优的观测序列。

        for (int state1 : states) {
            V[0][state1] = start_p[state1] * emit_p[state1][observation[0]]; //初始概率 * 发射概率，当前表示的是 在  0位置的观测点
            path[state1][0] = state1;
        }
        //依次每个观察序列，并计算当前观察序列值 下，每个隐藏层的最大概率，最终记录最优路径
        for (int obs = 1; obs < observation.length; ++obs) {
            int[][] newpath = new int[states.length][observation.length];
            //遍历每个隐状态，计算状态转移到当前状态的概率,得到最大概率状态
            for (int targetState : states) {
                double prob = -1;
                int state;
                for (int preState : states) { //这里表示的是前一个状态
                    double nProb = V[obs - 1][preState] * trans_p[preState][targetState] * emit_p[targetState][observation[obs]];
                    if (nProb > prob) {
                        prob = nProb;
                        state = preState; //前一个状态
                        // 记录最大概率
                        V[obs][targetState] = nProb;
                        // 记录路径
                        System.arraycopy(path[state], 0, newpath[targetState], 0, obs);//这是为了记录 曾经的初始化数据 ,//这里的 targetState 一定会小于等于 observation 的长度
                        newpath[targetState][obs] = targetState;////最大概率状态转移记录
                    }
                }
            }
            //不需要保留旧路径
            path = newpath;
        }
        //回溯路径，找到最后状态
        double prob = -1;
        int state = 0;
        for (int state2 : states) {
            if (V[observation.length - 1][state2] > prob) {//最后一步v值决定最大可能隐状态序列
                prob = V[observation.length - 1][state2];
                state = state2;
            }
        }

        return path[state];
    }

    public static void main(String[] args) {
        System.out.println("=====HMM 模型 第一个问题，求当前观察序列的 概率 ，使用向前算法 =====");
        double  forwardResult = HMM.forward(Example.observations, Example.states, Example.start_probability, Example.transititon_probability, Example.emission_probability);
        System.out.println(forwardResult);
        System.out.println("=====HMM 模型 第一个问题，求当前观察序列的 概率 ，使用向后算法 =====");
        double  backResult = HMM.backward(Example.observations, Example.states, Example.start_probability, Example.transititon_probability, Example.emission_probability);
        System.out.println(backResult);

        System.out.println("=====HMM 模型 第二个问题，求当前观察序列的 概率 Baum-Welch算法   EM算法=====");



        System.out.println("=====HMM 模型 第三个问题，预测状态序列，解码器，viterbi算法=====");
        //DoctorExample
        int[] result = HMM.viterbi(DoctorExample.observations, DoctorExample.states, DoctorExample.start_probability, DoctorExample.transititon_probability, DoctorExample.emission_probability);
        for (int r : result) {
            System.out.print(Example.Status.values()[r] + " ");
        }
        System.out.println();


        // WeatherExample
        int[] result_weather = HMM.viterbi(WeatherExample.observations, WeatherExample.states, WeatherExample.start_probability, WeatherExample.transititon_probability, WeatherExample.emission_probability);
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



//这是  李航 书上的案例
class Example {
    enum Status//隐状态
    {
        Rainy,//下雨
        Sunny,//天晴
        Humdity//湿度
    }

    enum Activity //显状态
    {
        Red,//红
        white,//白
    }

    static int[] states = new int[]{Example.Status.Rainy.ordinal(), Example.Status.Sunny.ordinal(),Example.Status.Humdity.ordinal()};
    static int[] observations = new int[]{Example.Activity.Red.ordinal(), Example.Activity.white.ordinal(), Example.Activity.Red.ordinal()};
    static double[] start_probability = new double[]{0.2,0.4, 0.4};//初始状态
    static double[][] transititon_probability = new double[][]{//转移矩阵
            {0.5,0.2,0.3},
            {0.3,0.5,0.2},
            {0.2,0.3,0.5}
    };
    static double[][] emission_probability = new double[][]{//观测矩阵
            {0.5, 0.5},
            {0.4, 0.6},
            {0.7, 0.3},
    };


}

