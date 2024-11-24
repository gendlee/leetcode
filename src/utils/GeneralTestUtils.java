package utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GeneralTestUtils {

    // 写入结果列名
    private final static String RESULT_COLUMN = "Result";

    /**
     * 通用测试方法
     * @param filePath        Excel 文件路径
     * @param testCaseExecutor 执行测试逻辑的回调接口
     * @throws IOException 如果文件操作失败
     */
    public static void executeTests(String filePath, TestCaseExecutor testCaseExecutor) throws IOException {
        // 读取测试用例
        List<Map<String, Object>> testCases = ExcelTestUtils.readTestCases(filePath);

        // 遍历执行测试逻辑
        for (Map<String, Object> testCase : testCases) {
            try {
                String result = testCaseExecutor.execute(testCase);
                testCase.put(RESULT_COLUMN, result); // 存储测试结果
                System.out.printf("Case %s 执行结果：%s%n", testCase.getOrDefault("Case ID", "0"), result);
            } catch (Exception e) {
                testCase.put(RESULT_COLUMN, "ERROR: " + e.getMessage()); // 异常结果记录
                System.out.println("结果写入异常: " + e.getMessage());
            }
        }

        // 写回测试结果
        ExcelTestUtils.writeTestResults(filePath, testCases, RESULT_COLUMN);

        System.out.printf("%n===>测试案例执行完成，结果在：%s 文件中查看%n", filePath);
    }
}

