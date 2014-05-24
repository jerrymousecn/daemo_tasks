package cn.jerry.tools.tasks;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskManager {
	private static TaskManager taskManager = new TaskManager();
	private ExecutorService executorService = Executors.newCachedThreadPool();
	private ConcurrentMap<String, DaemonTask> taskMap = new ConcurrentHashMap<String, DaemonTask>();
	private final int TASK_NOT_FOUND = -1;
	private TaskCleaner taskCleaner = new TaskCleaner(taskMap);
	private Timer timer = new Timer();
	private final int CLEAN_PERIOD = 2000;  //in milliseconds

	private String getUniqueID() {
		return UUID.randomUUID().toString();
	}

	private TaskManager() {
		startTerminatedTaskCleanTimer();
	}

	private void startTerminatedTaskCleanTimer() {
		timer.schedule(taskCleaner, 0, CLEAN_PERIOD);
	}

	private void stopTerminatedTaskCleanTimer() {
		timer.cancel();
	}

	public static TaskManager getInstance() {
		return taskManager;
	}

	public synchronized String addTaskAndStart(DaemonTask task) {
		String taskID = getUniqueID();
		task.setTaskID(taskID);
		taskMap.put(taskID, task);
		executorService.execute(task);
		return taskID;
	}

	public synchronized void cancelTask(String taskID) {
		DaemonTask task = getTask(taskID);
		if (task != null) {
			task.setToStopFlag(true);
			if (task.isTerminated()) {
				taskMap.remove(task);
			}
		}
	}

	public DaemonTask getTask(String taskID) {
		return taskMap.get(taskID);
	}

	public int getProgress(String taskID) {
		int progress = TASK_NOT_FOUND;
		DaemonTask task = getTask(taskID);
		if (task != null) {
			progress = task.getProgress();
		}
		return progress;
	}

}

class TaskCleaner extends TimerTask {
	private ConcurrentMap<String, DaemonTask> taskMap;

	public TaskCleaner(ConcurrentMap<String, DaemonTask> taskMap) {
		this.taskMap = taskMap;
	}

	@Override
	public void run() {
		for (Entry<String, DaemonTask> entry : taskMap.entrySet()) {
			String taskID = entry.getKey();
			DaemonTask task = entry.getValue();
			if (task.isTerminated()) {
				Date terminatedTime = task.getTerminatedTime();
				Date now = new Date();

				long timeOffset = getTimeOffset(terminatedTime, now);
				if (timeOffset > 10000) {
					taskMap.remove(taskID);
					System.out.println("task: " + taskID + " is removed");
				}
			}
		}
	}

	private long getTimeOffset(Date date1, Date date2) {
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		long offset = time2 - time1;
		if (offset < 0) {
			offset = -offset;
		}
		return offset;
	}
}
