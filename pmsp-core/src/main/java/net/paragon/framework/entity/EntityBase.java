/**
 * 
 */
package net.paragon.framework.entity;

import java.io.Serializable;

/**
 * @author bqduc
 *
 */
public interface EntityBase extends Comparable<Object>, Serializable {
	Long getId();
	void setId(Long id);
}
