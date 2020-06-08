import java.util.*;

/**
 * 128. 最长连续序列
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int result = 0;
        for (Integer num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currNum = num;
                int temp = 1;

                while (numSet.contains(currNum + 1)) {
                    currNum = currNum + 1;
                    temp = temp + 1;
                }

                result = Math.max(temp, result);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,0,1};
        System.out.println(new LongestConsecutive().longestConsecutive(nums));
    }
}
