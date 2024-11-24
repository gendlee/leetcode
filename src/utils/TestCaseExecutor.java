package utils;

import java.util.Map;

@FunctionalInterface
public interface TestCaseExecutor {
    /**
     * 执行单条测试逻辑
     *
     * @param testCase 测试用例，包含从 Excel 读取的键值对
     * @return 测试结果，用于写入 Excel
     */
    String execute(Map<String, Object> testCase);
}

