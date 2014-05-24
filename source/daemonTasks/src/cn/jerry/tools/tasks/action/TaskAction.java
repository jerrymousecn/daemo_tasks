package cn.jerry.tools.tasks.action;

import cn.jerry.tools.tasks.DaemonTask;
import cn.jerry.tools.tasks.TaskManager;
import cn.jerry.tools.tasks.TestTask;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TaskAction extends ActionSupport {
	private String taskName;

	public String addTask() {
		TaskManager taskManager = TaskManager.getInstance();
		DaemonTask task = new TestTask();
		task.setTaskName(taskName);
		String taskID = taskManager.addTaskAndStart(task);
		ActionContext.getContext().put("taskid", taskID);
		return SUCCESS;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
