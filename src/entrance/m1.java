package entrance;

import java.util.HashMap;
import java.util.Map;

public class m1 {
    /**
     * 示例 1：
     *
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 示例 2：
     *
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     *
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     */

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> value2Index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            int second = target - first;
            if (value2Index.containsKey(second)) {
                return new int[]{i, value2Index.get(second)};
            }

            value2Index.put(first, i);
        }

        return new int[]{-1, -1};
    }


}
