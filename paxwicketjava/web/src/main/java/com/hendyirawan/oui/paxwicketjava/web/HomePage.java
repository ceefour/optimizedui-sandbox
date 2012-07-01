package com.hendyirawan.oui.paxwicketjava.web;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soluvas.json.JsonUtils;

import com.hendyirawan.oui.paxwicketjava.core.Category;

/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class HomePage extends WebPage {

	private transient Logger log = LoggerFactory.getLogger(HomePage.class);
	@PaxWicketBean(name="navigation")
	private Navigation navigation;
	
	public HomePage() {
		// Request data
		log.info("Requesting categories data...");
		Future<List<Category>> categoriesFuture = navigation.getCategoriesAsync();
		
		// Wait for data to come
		log.info("Waiting for categories data...");
		List<Category> categories = null;
		try {
			categories = categoriesFuture.get(250, TimeUnit.MILLISECONDS);
			log.info("Categories data returned!");
		} catch (Exception e) {
			log.error("Cannot get categories", e);
		}
		
		// Construct the view
		add(new Label("pageTitle", "Buy Amazing Fashion").setRenderBodyOnly(true));
		add(new Header(categories));
		add(new ListView<Category>("categories", navigation.getCategories()) {
			@Override
			protected void populateItem(ListItem<Category> item) {
				item.setRenderBodyOnly(true);
				Category category = item.getModelObject();
				item.add(new ExternalLink("name", "#" + category.getName(), category.getName()));
			}
		});
		add(new Label("message", "Hello world!"));
		add(new Footer());
		
		// Add the Backbone data
		add(new Label("categoriesData", "var categoriesData = " + JsonUtils.asJson(categories) + ";")
			.setEscapeModelStrings(false));

		// Add the Backbone View classes
	}

}
