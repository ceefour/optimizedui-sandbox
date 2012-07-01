package com.hendyirawan.oui.paxwicketjava.core;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Random;


/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class Product implements Serializable, Identifiable<String> {

	private String id;
	private String slug;
	private String name;
	private BigDecimal price;
	private String description;
	
	public Product(String id, String slug, String name) {
		super();
		this.id = id;
		this.slug = slug;
		this.name = name;
		this.description = "Keren dan nyaman sekali";
		this.price = new BigDecimal(Math.round(new Random().nextDouble() * 10000) * 500);
	}
	
	public Product(String name) {
		super();
		this.id = name.toLowerCase();
		this.slug = name.toLowerCase();
		this.name = name;
		this.description = "Keren dan nyaman sekali";
		this.price = new BigDecimal(Math.round(new Random().nextDouble() * 10000) * 500);
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
