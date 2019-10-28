/**
 * 
 */
package net.paragon.config.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import net.paragon.framework.entity.BizObjectBase;
import net.paragon.utility.CommonUtility;

/**
 * @author bqduc
 *
 */
@Builder
@Entity
@Table(name = "configuration")
public class Configuration extends BizObjectBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7122460417089959400L;

	@Column(name = "name", length=50, nullable=false, unique=true)
	private String name;

	@Column(name = "value", length=200, nullable=false)
	private String value;

	@Column(name = "value_extended", length=500)
	private String valueExtended;

	@Column(name = "grouped", length=30)
	private String group;

	@Column(name = "info", columnDefinition="TEXT")
	private String info;

	@Builder.Default
	@OneToMany(mappedBy="configuration", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ConfigurationDetail> configurationDetails = new HashSet<>();

	public String getName() {
		return name;
	}

	public Configuration setName(String name) {
		this.name = name;
		return this;
	}

	public String getValue() {
		return value;
	}

	public Configuration setValue(String value) {
		this.value = value;
		return this;
	}

	public String getGroup() {
		return group;
	}

	public Configuration setGroup(String group) {
		this.group = group;
		return this;
	}

	public String getInfo() {
		return info;
	}

	public Configuration setInfo(String info) {
		this.info = info;
		return this;
	}

	public String getValueExtended() {
		return valueExtended;
	}

	public Configuration setValueExtended(String valueExtended) {
		this.valueExtended = valueExtended;
		return this;
	}

	public Set<ConfigurationDetail> getConfigurationDetails() {
		return configurationDetails;
	}

	public Configuration setConfigurationDetails(Set<ConfigurationDetail> configurationDetails) {
		this.configurationDetails = configurationDetails;
		return this;
	}

	public Configuration addConfigurationDetails(List<ConfigurationDetail> configurationDetails) {
		if (CommonUtility.isEmpty(configurationDetails))
			return this;

		this.configurationDetails.addAll(configurationDetails);
		return this;
	}

	public Configuration addConfigurationDetail(ConfigurationDetail configurationDetail) {
		if (null==configurationDetail.getConfiguration()) {
			configurationDetail.setConfiguration(this);
		}

		this.configurationDetails.add(configurationDetail);
		return this;
	}

	public Configuration() {
	}

	public Configuration(String name, String value, String valueExtended, String group, String info,
			Set<ConfigurationDetail> configurationDetails) {
		super();
		this.name = name;
		this.value = value;
		this.valueExtended = valueExtended;
		this.group = group;
		this.info = info;
		this.configurationDetails = configurationDetails;
	}
}
