package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.responsibility职责;


public class GroupLeader extends Handler {

    public GroupLeader() {
        super(0, NUM_ONE);
    }

    protected void handleLeave(LeaveRequest leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天，" + leave.getContent() + "。");
        System.out.println("小组长审批：同意");
    }
}
