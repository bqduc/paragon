/**
 * 
 */
package net.paragon.framework.component;

import java.io.Serializable;

import javax.inject.Inject;

import net.paragon.framework.logging.LogService;

/**
 * @author bqduc
 *
 */
public abstract class RootComponent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4315018091652981743L;

	@Inject
	protected LogService log;
}
