package drones.models.scheduler.messages.from;

/**
 * Created by Ronald on 17/04/2015.
 */
public class SchedulerCompletedMessage implements SchedulerEvent {

    private long assignmentId;

    public SchedulerCompletedMessage(long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public long getAssignmentId() {
        return assignmentId;
    }
}
