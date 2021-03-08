package cn.topologycraft.uhc.options;

import cn.topologycraft.uhc.task.Task;
import cn.topologycraft.uhc.task.Taskable;

public class Option extends Taskable {
	
	private String optionId;
	private String optionName, optionDescript;
	
	private OptionType type;
	private boolean saveToFile;
	
	public Option(String id, String name, OptionType type, Object defaultValue) {
		optionId = id;
		optionName = name;
		this.type = type;
		type.setValue(defaultValue);
	}
	
	public Object getValue() { return type.getValue(); }
	public int getIntegerValue() { return (int) type.getValue(); }
	public float getFloatValue() { return (float) type.getValue(); }
	public String getStringValue() { return type.getStringValue(); }
	public void incValue() { type.applyInc(); this.updateTasks(); }
	public void decValue() { type.applyDec(); this.updateTasks(); }
	public void setValue(Object value) { type.setValue(value); this.updateTasks(); }
	public void setStringValue(String value) { type.setStringValue(value); this.updateTasks(); }
	public void setInitialValue(String value) { type.setStringValue(value); }
	
	public String getName() { return optionName; }
	public String getDescription() { return optionDescript; }
	public String getIncString() { return type.getIncString(); }
	public String getDecString() { return type.getDecString(); }
	
	@Override
	public String toString() {
		return getStringValue();
	}
	
	@Override
	public Option addTask(Task task) {
		return (Option) super.addTask(task);
	}
	
	public Option setDescription(String des) {
		optionDescript = des;
		return this;
	}
	
	public Option setNeedToSave() {
		saveToFile = true;
		this.addTask(Options.instance.taskSaveProperties);
		return this;
	}
	
	public boolean needToSave() { return saveToFile; }
	public String getId() { return optionId; }

}
