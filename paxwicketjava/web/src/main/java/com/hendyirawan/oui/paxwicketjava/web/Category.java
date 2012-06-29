package com.hendyirawan.oui.paxwicketjava.web;

import java.io.Serializable;

/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class Category implements Serializable {

	private String name;
	
	public Category(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
