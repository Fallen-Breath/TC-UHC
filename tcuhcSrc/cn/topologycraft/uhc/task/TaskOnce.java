package cn.topologycraft.uhc.task;

public class TaskOnce extends Task {
	
	private Task parent;
	
	public TaskOnce(Task parent) {
		this.parent = parent;
	}
	
	@Override
	public void onUpdate() {
		parent.onUpdate();
	}
	
	@Override
	public boolean hasFinished() {
		return true;
	}
	
	@Override
	public void onFinish() {
		parent.onFinish();
	}

}
