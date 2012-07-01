package com.hendyirawan.oui.paxwicketjava.core;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Nullable;

import org.soluvas.push.data.SyncRepository;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * @author ceefour
 *
 */
public class InMemoryRepository<ID, T extends Identifiable<ID>> implements SyncRepository<ID, T> {

	private List<T> entries = new CopyOnWriteArrayList<T>();
	
	public InMemoryRepository() {
	}
	
	@Override
	public T findOne(final ID id) {
		return Iterables.find(entries, new Predicate<T>() {
			@Override
			public boolean apply(@Nullable T entry) {
				return id.equals(entry.getId());
			}
		});
	}

	@Override
	public List<T> findAll() {
		return entries;
	}

	@Override
	public T create(T entry) {
		entries.add(entry);
		return entry;
	}

	@Override
	public T update(final ID id, T entry) {
		T found = Iterables.find(entries, new Predicate<T>() {
			@Override
			public boolean apply(@Nullable T entry) {
				return id.equals(entry.getId());
			}
		}, null);
		if (found != null)
			entries.remove(found);
		entries.add(entry);
		return entry;
	}

	@Override
	public void delete(final ID id) {
		T found = Iterables.find(entries, new Predicate<T>() {
			@Override
			public boolean apply(@Nullable T entry) {
				return id.equals(entry.getId());
			}
		});
		entries.remove(found);
	}

}
