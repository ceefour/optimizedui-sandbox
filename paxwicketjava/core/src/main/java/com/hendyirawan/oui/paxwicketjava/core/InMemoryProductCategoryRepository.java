package com.hendyirawan.oui.paxwicketjava.core;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.soluvas.push.data.SyncRepository;

import com.google.common.collect.ImmutableList;

/**
 * @author ceefour
 *
 */
public class InMemoryProductCategoryRepository implements CategoryRepository {

	private Map<String, Category> categories = new ConcurrentHashMap<String, Category>();
	
	public InMemoryProductCategoryRepository() {
		categories.put("bags", new Category("bags", "Bags"));
		categories.put("shawl", new Category("shawl", "Shawl"));
		categories.put("obi", new Category("obi", "Obi"));
		categories.put("apparel", new Category("apparel", "Apparel"));
		categories.put("accessories", new Category("accessories", "Accessories"));
	}
	
	@Override
	public Category findOne(String id) {
		return null;
	}

	@Override
	public List<Category> findAll() {
		return ImmutableList.copyOf(categories.values());
	}

	@Override
	public Category create(Category entry) {
		categories.put(entry.getId(), entry);
		return entry;
	}

	@Override
	public Category update(String id, Category entry) {
		categories.put(entry.getId(), entry);
		return entry;
	}

	@Override
	public void delete(String id) {
		categories.remove(id);
	}

}
