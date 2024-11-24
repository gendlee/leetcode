package entrance;

import common.linkedlist.LinkedList;
import common.linkedlist.ListNode;
import org.junit.Test;
import utils.CompareUtil;
import utils.GeneralTestUtils;
import utils.Parsor;
import utils.Printer;

public class m1721Test {
    // 测试案例文件路径
    private final static String FILE_PATH = "testCases\\m1721.xlsx";

    @Test
    public void swapNodes() throws Exception {


        GeneralTestUtils.executeTests(FILE_PATH, testCase -> {
            // 提取测试用例数据
            int[] nums = Parsor.parseString2IntArray((String) testCase.get("nums"));
            System.out.print("nums: ");
            Printer.printIntArray(nums);

            int k = ((Double) testCase.get("k")).intValue();

            System.out.println("k : " + k);
            int[] expected = Parsor.parseString2IntArray((String) testCase.get("expected"));

            System.out.print("expected: ");
            Printer.printIntArray(expected);

            // 创建链表
            LinkedList linkedList = new LinkedList(nums);

            // 执行测试逻辑
            m1721 solution = new m1721();
            ListNode head = solution.swapNodes(linkedList.head, k);

            // 返回测试结果
            return CompareUtil.linkedListEqualArraysValue(head, expected) ? "PASS" : "FAIL";
        });


    }
}