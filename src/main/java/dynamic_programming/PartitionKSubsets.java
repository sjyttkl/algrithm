package dynamic_programming;

/**
 * Create with: dynamic_programming
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2021/3/6 09:13
 * version: 1.0
 * description:给你输入一个数组nums和一个正整数k，请你判断nums是否能够被平分为元素和相同的k个子集
 * 把装有n个数字的数组nums分成k个和相同的集合，你可以想象将n个数字分配到k个「桶」里，最后这k个「桶」里的数字之和要相同。
 * https://mp.weixin.qq.com/s/fsLKaWBvSWtM0jA-CfOxyA
 * 关键是要知道怎么「做选择」，这样才能利用递归函数进行穷举。
 * 那么回想我们这个问题，将n个数字分配到k个桶里，我们可以有两种视角：
 * 视角一，如果我们切换到这n个数字的视角，每个数字都要选择进入到k个桶中的某一个。
 * 视角二，如果我们切换到这k个桶的视角，对于每个桶，都要遍历nums中的n个数字，然后选择是否将当前遍历到的数字装进自己这个桶里。
 * 你可能问，这两种视角有什么不同？
 * 用不同的视角进行穷举，虽然结果相同，但是解法代码的逻辑完全不同；对比不同的穷举视角，可以帮你更深刻地理解回溯算法，我们慢慢道来。
 */
public class PartitionKSubsets {
    public static void main(String[] args) {

    }

    // 主函数
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        // 排除一些基本情况
        if (k > nums.length) return false;
        int sum = 0;
        for (int v : nums) sum += v;
        if (sum % k != 0) return false;

        // k 个桶（集合），记录每个桶装的数字之和
        int[] bucket = new int[k];
        // 理论上每个桶（集合）中数字的和
        int target = sum / k;
        // 穷举，看看 nums 是否能划分成 k 个和为 target 的子集
        return backtrack(nums, 0, bucket, target);
    }

    // 递归穷举 nums 中的每个数字
    public static boolean backtrack(
            int[] nums, int index, int[] bucket, int target) {

        if (index == nums.length) {
            // 检查所有桶的数字之和是否都是 target
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != target) {
                    return false;
                }
            }
            // nums 成功平分成 k 个子集
            return true;
        }

        // 穷举 nums[index] 可能装入的桶
        for (int i = 0; i < bucket.length; i++) {
            // 剪枝，桶装装满了
            if (bucket[i] + nums[index] > target) {
                continue;
            }
            // 将 nums[index] 装入 bucket[i]
            bucket[i] += nums[index];
            // 递归穷举下一个数字的选择
            if (backtrack(nums, index + 1, bucket, target)) {
                return true;
            }
            // 撤销选择
            bucket[i] -= nums[index];
        }

        // nums[index] 装入哪个桶都不行
        return false;
    }

    public static boolean canPartitionKSubsets_buck(int[] nums, int k) {
        // 排除一些基本情况
        if (k > nums.length) return false;
        int sum = 0;
        for (int v : nums) sum += v;
        if (sum % k != 0) return false;

        boolean[] used = new boolean[nums.length];
        int target = sum / k;
        // k 号桶初始什么都没装，从 nums[0] 开始做选择
        return backtrack_buck(k, 0, nums, 0, used, target);
    }

    public static boolean backtrack_buck(int k, int bucket,
                                         int[] nums, int start, boolean[] used, int target) {
        // base case
        if (k == 0) {
            // 所有桶都被装满了，而且 nums 一定全部用完了
            // 因为 target == sum / k
            return true;
        }
        if (bucket == target) {
            // 装满了当前桶，递归穷举下一个桶的选择
            // 让下一个桶从 nums[0] 开始选数字
            return backtrack_buck(k - 1, 0, nums, 0, used, target);
        }

        // 从 start 开始向后探查有效的 nums[i] 装入当前桶
        for (int i = start; i < nums.length; i++) {
            // 剪枝
            if (used[i]) {
                // nums[i] 已经被装入别的桶中
                continue;
            }
            if (nums[i] + bucket > target) {
                // 当前桶装不下 nums[i]
                continue;
            }
            // 做选择，将 nums[i] 装入当前桶中
            used[i] = true;
            bucket += nums[i];
            // 递归穷举下一个数字是否装入当前桶
            if (backtrack_buck(k, bucket, nums, i + 1, used, target)) {
                return true;
            }
            // 撤销选择
            used[i] = false;
            bucket -= nums[i];
        }
        // 穷举了所有数字，都无法装满当前桶
        return false;
    }

}
