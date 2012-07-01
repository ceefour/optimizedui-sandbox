package com.hendyirawan.oui.paxwicketjava.web;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soluvas.json.JsonUtils;
import org.soluvas.push.data.SyncRepository;

import com.google.common.collect.ImmutableList;
import com.hendyirawan.oui.paxwicketjava.core.Category;
import com.hendyirawan.oui.paxwicketjava.core.Product;

/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class HomePage extends WebPage {

	private transient Logger log = LoggerFactory.getLogger(HomePage.class);
	
	@PaxWicketBean(name="navigation")
	private Navigation navigation;
	@PaxWicketBean(name="productRepo")
	private SyncRepository<String, Product> productRepo;
	
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
		List<Product> products = productRepo.findAll();
		
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
		add(new Footer());
		add(new ListView<Product>("featuredProducts", products) {

			@Override
			protected void populateItem(ListItem<Product> item) {
				item.setRenderBodyOnly(true);
				Product product = item.getModelObject();
				PageParameters pars = new PageParameters();
				pars.add("slug", product.getSlug());
				item.add(new BookmarkablePageLink<ProductPage>("link", ProductPage.class, pars)
						.setBody(new Model<Serializable>(product.getName())));
			}
			
		});
		
		// Add the Backbone data
		add(new Label("categoriesData", "var categoriesData = " + JsonUtils.asJson(categories) + ";")
			.setEscapeModelStrings(false));
		// TODO: for pushes, the featuredProducts in homepage is filtered, so there are 2 approaches
		// 1. filter it on server side, and only send pushes that match the filter.
		//    most efficient for client, but can be taxing on the server if there are a lot of filters to track
		// 2. filter it on server side for predefined filters (featured, recent, category pages, etc.). ignore the rest.
		// 3. filter it on the client side. this will "reduce" the load on server because server
		//    doesn't need to save the states of filters. but it will waste bandwith if there
		//    are a lot (hundreds of thousands) of records and they change a lot
		add(new Label("featuredProductsData", "var featuredProductsData = " + JsonUtils.asJson(products) + ";")
			.setEscapeModelStrings(false));

		// Add the Backbone View classes
	}

}
