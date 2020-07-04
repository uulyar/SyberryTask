package main.java;

import java.util.*;

public class SchedulerImpl implements IScheduler {

  @Override
  public List<Task> schedule(List<Task> tasks) {
    if (tasks == null || tasks.isEmpty()) {
      return tasks;
    }

    Set<String> processedTasks = new LinkedHashSet<>(tasks.size());
    List<Task> sortedTasks = new ArrayList<>(tasks.size());

    tasks.forEach(task -> {
      if (!processedTasks.contains(task.getName())) {
        recursiveSort(task, tasks, processedTasks, sortedTasks);
      }
    });

    return sortedTasks;
  }

  /**
   * Recursive topological sorting a list of tasks according to the information about their dependencies.
   * @param tasks - initial list of tasks
   * @param processedTasks - list of processed tasks
   * @param sortedTasks - result sorted task list
   */
  private void recursiveSort(Task task, List<Task> tasks, Collection<String> processedTasks, List<Task> sortedTasks) {
    if (task == null) {
      return;
    }

    processedTasks.add(task.getName());

    for (String adjTaskName : task.getPredecessors()) {
      if (!processedTasks.contains(adjTaskName)) {
        Task adjTask = tasks.stream()
            .filter(currentTask -> adjTaskName.equals(currentTask.getName())).findAny().orElse(null);
        recursiveSort(adjTask, tasks, processedTasks, sortedTasks);
      }
    }

    sortedTasks.add(task);
  }
}
