package com.hendyirawan.oui.paxwicketjava.web;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soluvas.push.data.SyncRepository;

import akka.actor.ActorSystem;

import com.hendyirawan.oui.paxwicketjava.core.Category;

/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class Navigation implements Serializable {

	private transient Logger log = LoggerFactory.getLogger(Navigation.class);
	private ActorSystem actorSystem;
	private ExecutorService executor;
	private SyncRepository<String, Category> categoryRepository;
	
//	public Navigation(ActorSystem actorSystem) {
//		super();
//		this.actorSystem = actorSystem;
	public Navigation() {
//		categoryRepo = new InMemoryProductCategoryRepository();
//		this.executor = executor;
//		this.executor = Executors.newCachedThreadPool();
	}
	
	public void init() {
	}

	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	/**
	 * @todo Should be using (typed) Actors  I guess, with fire-and-forget mechanism,
	 * rather than Future. The fire-and-forget will have a parameter to pass some kind
	 * of Routing Slip EIP pattern to send the result to the next actor in "chain". 
	 * @return
	 */
//	public Future<List<Category>> getCategoriesAsync() {
//		return Futures.successful(categories, actorSystem.dispatcher());
//	}
	public Future<List<Category>> getCategoriesAsync() {
		return executor.submit(new Callable<List<Category>>() {
			@Override
			public List<Category> call() throws Exception {
				log.info("Returning categories data from fake database");
				return categoryRepository.findAll();
			}
		});
//		return Futures.successful(categories, actorSystem.dispatcher());
	}

	public ActorSystem getActorSystem() {
		return actorSystem;
	}

	public void setActorSystem(ActorSystem actorSystem) {
		this.actorSystem = actorSystem;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	public SyncRepository<String, Category> getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(SyncRepository<String, Category> categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

}
