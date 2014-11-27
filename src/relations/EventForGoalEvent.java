package relations;

import concepts.Event;

public class EventForGoalEvent extends Relation {
	Event task, goal;
	public EventForGoalEvent(Event task, Event goal) {
		this.task =task;
		this.goal=goal;
	}
	
	public Event getTask() {
		return task;
	}

	public Event getGoal() {
		return goal;
	}
}
