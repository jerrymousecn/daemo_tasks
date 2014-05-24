package cn.jerry.tools.tasks;

import java.util.Date;

public abstract class DaemonTask implements Runnable {
	private String taskID;
	private String taskName;
	private String taskDesc;
	private boolean isStartedFlag = false;;
	private boolean isTerminatedFlag = false;;
	private int progress;
	private Date startTime;
	private Date terminatedTime;
	protected boolean toStopFlag = false;

	protected abstract void execute();

	protected void setProgress(int progress) {
		this.progress = progress;
	}

	public void run() {
		this.isStartedFlag = true;
		this.execute();
		this.isTerminatedFlag = true;
		this.terminatedTime = new Date();
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public boolean isStarted() {
		return isStartedFlag;
	}

	public boolean isTerminated() {
		return isTerminatedFlag;
	}

	public void setToStopFlag(boolean toStopFlag) {
		this.toStopFlag = toStopFlag;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getTerminatedTime() {
		return terminatedTime;
	}

	public int getProgress() {
		return progress;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

}
