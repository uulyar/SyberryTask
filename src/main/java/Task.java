package main.java;

import java.util.List;

/**
 * The task class represents a certain activities that must be done as the part of the project planning
 */
class Task {

  /**
   * Unique name of the activity
   */
  private String name;

  /**
   * A list of names of the activitiest that must be compelte in order to be able to start the current activity
   */
  private List<String> predecessors;

  Task(String name, List<String> predecessors) {
    this.name = name;
    this.predecessors = predecessors;
  }

  String getName() {
    return name;
  }

  List<String> getPredecessors() {
    return predecessors;
  }
}