/**
 * 
 */
package net.paragon.framework.model;

import java.io.Serializable;
import java.util.Map;

import lombok.Builder;
import net.paragon.utility.ListUtility;

/**
 * @author bqduc
 *
 */
@Builder
public class ExecutionContext implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3206765121751074978L;

	private String executionStage;

	@Builder.Default
	private Map<String, Object> context = ListUtility.createMap();

	public Map<String, Object> getContext() {
		return context;
	}

	/*
	 * public void setContext(Map<String, Object> context) { this.context = context;
	 * }
	 * 
	 * public void setContext(String key, Object value) { this.context.put(key,
	 * value); }
	 */

	public boolean containKey(String key){
		return this.context.containsKey(key);
	}

	public Object getContextData(String key){
		return this.context.get(key);
	}

	public Object getDefaultContext(){
		return (this.context.size()<1)?null:this.context.values().iterator().next();
	}

	public void putContextData(String key, Object contextData){
		this.context.put(key, contextData);
	}

	public String getExecutionStage() {
		return executionStage;
	}

	public void setExecutionStage(String executionStage) {
		this.executionStage = executionStage;
	}

	public boolean isEmpty(){
		return context.isEmpty();
	}

	public ExecutionContext buildFromOther(ExecutionContext executionContext){
		this.context.putAll(executionContext.getContext());
		return this;
	}

	public ExecutionContext context(String key, Object value){
		this.context.put(key, value);
		return this;
	}
}
