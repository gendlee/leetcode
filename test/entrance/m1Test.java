package entrance;

import org.junit.Test;
import utils.CompareUtil;
import utils.GeneralTestUtils;
import utils.Parsor;

import java.util.Arrays;

public class m1Test {

    // 测试案例文件路径
    private final  static String  FILE_PATH = "testCases\\m1.xlsx";

    @Test
    public void testTwoSumFromExcel() throws Exception {


        GeneralTestUtils.executeTests(FILE_PATH, testCase -> {
            // 提取测试用例数据
            int[] nums = Parsor.parseString2IntArray((String) testCase.get("nums"));
            int target = ((Double) testCase.get("target")).intValue();
            int[] expected = Parsor.parseString2IntArray((String) testCase.get("expected"));

            // 执行测试逻辑
            m1 solution = new m1();
            int[] actual = solution.twoSum(nums, target);

            // 返回测试结果
            return CompareUtil.arraysEqualIgnoreOrder(expected, actual) ? "PASS" : "FAIL";
        });

    }


}