package cn.jerry.tools.tasks.action;

import java.util.Map;

import cn.jerry.tools.tasks.DaemonTask;
import cn.jerry.tools.tasks.TaskManager;
import cn.jerry.tools.tasks.TestTask;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TaskAction extends ActionSupport {
	private String taskName;
	private String taskID;
	private TaskManager taskManager = TaskManager.getInstance();

	public String addTask() {
		DaemonTask task = new TestTask();
		task.setTaskName(taskName);
		String taskID = taskManager.addTaskAndStart(task);
		ActionContext.getContext().put("taskid", taskID);
		return SUCCESS;
	}
	public String getTaskProgress() {
		int progress = taskManager.getProgress(taskID);
		ActionContext.getContext().put("task_progress", progress+"");
		return SUCCESS;
	}
	public String listTasks() {
		Map<String, DaemonTask> taskMap = taskManager.getTasksForView();
		ActionContext.getContext().put("taskMap", taskMap);
		return SUCCESS;
	}
	public String delTask() {
		taskManager.delFinishedTask(taskID);
		return SUCCESS;
	}
	public String stopTask() {
		taskManager.stopTask(taskID);
		return SUCCESS;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
}
