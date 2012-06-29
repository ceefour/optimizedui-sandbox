package com.hendyirawan.oui.paxwicketjava.web;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class Navigation implements Serializable {

	private List<Category> categories;
	
	public Navigation() {
		super();
		categories = ImmutableList.of(
				new Category("Bags"),
				new Category("Shawl"),
				new Category("Obi"),
				new Category("Apparel"),
				new Category("Accessories") );
	}

	public List<Category> getCategories() {
		return categories;
	}

}
