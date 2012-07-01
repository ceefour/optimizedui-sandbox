package com.hendyirawan.oui.paxwicketjava.core;


/**
 * Just because Blueprint can't construct classes with generics.
 * @author ceefour
 */
public class InMemoryProductRepository extends InMemoryRepository<String, Product> {

	public InMemoryProductRepository() {
		super();
	}
	
}
