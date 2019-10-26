/**
 * 
 */
package net.paragon.utility;

import java.util.HashMap;

/**
 * @author bqduc
 *
 */
public class BrillianceHashMap<K, V> extends HashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6641227453220620408L;

	public BrillianceHashMap<K, V> doPut(K key, V value){
		super.put(key, value);
		return this;
	}
}
