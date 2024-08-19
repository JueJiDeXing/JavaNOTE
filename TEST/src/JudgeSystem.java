import java.io.File;

public class JudgeSystem {
    public static void main(String[] args) throws Exception {
        // 1. 接收用户提交的Java文件
        String pre = "E:\\ideaProject\\NOTE\\TEST\\src\\";
        File userCodeFile = new File(pre + "Main.java");

        // 2. 编译Java文件，生成可执行文件
        ProcessBuilder compileProcessBuilder = new ProcessBuilder("javac", userCodeFile.getAbsolutePath());
        Process compileProcess = compileProcessBuilder.start();
        int compileResult = compileProcess.waitFor();
        if (compileResult == 0) {
            System.out.println("编译成功");
            // 3. 运行可执行文件，并输入测试数据
            ProcessBuilder runProcessBuilder = new ProcessBuilder("java", "-cp", userCodeFile.getParent(), "Main");
            runProcessBuilder.redirectInput(new File(pre + "in.txt"));
            runProcessBuilder.redirectOutput(new File(pre + "out.txt"));
            Process runProcess = runProcessBuilder.start();
            runProcess.waitFor();
        } else {
            System.out.println("编译失败");
        }
    }
}
