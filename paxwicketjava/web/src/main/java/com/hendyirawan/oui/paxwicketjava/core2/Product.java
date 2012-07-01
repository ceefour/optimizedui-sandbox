package com.hendyirawan.oui.paxwicketjava.core2;

import java.io.Serializable;

/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class Product implements Serializable {

	private String id;
	private String slug;
	private String name;
	
	public Product(String id, String slug, String name) {
		super();
		this.id = id;
		this.slug = slug;
		this.name = name;
	}
	
	public Product(String name) {
		super();
		this.id = name.toLowerCase();
		this.slug = name.toLowerCase();
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", slug=" + slug + ", name=" + name + "]";
	}
	
}
