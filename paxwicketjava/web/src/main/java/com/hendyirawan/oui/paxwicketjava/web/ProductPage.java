package com.hendyirawan.oui.paxwicketjava.web;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soluvas.json.JsonUtils;
import org.soluvas.push.data.SyncRepository;

import com.hendyirawan.oui.paxwicketjava.core.Category;
import com.hendyirawan.oui.paxwicketjava.core.Product;

/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class ProductPage extends WebPage {

	private transient Logger log = LoggerFactory.getLogger(ProductPage.class);
	@PaxWicketBean(name="navigation")
	private Navigation navigation;
	@PaxWicketBean(name="productRepo")
	private SyncRepository<String, Product> productRepo;
	
	public ProductPage(PageParameters params) {
		
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
		Product product = productRepo.findOne(params.get("slug").toString());
		
		// Construct the view
		add(new Label("pageTitle", product.getName()).setRenderBodyOnly(true));
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
		
		add(new Label("productName", product.getName()));
		add(new Label("productPrice", "IDR " + product.getPrice().toString()));
		add(new Label("productDescription", product.getDescription()));
		
		// Add the Backbone data
		add(new Label("categoriesData", "var categoriesData = " + JsonUtils.asJson(categories) + ";")
			.setEscapeModelStrings(false));
		add(new Label("productData", "var productData = " + JsonUtils.asJson(product) + ";")
			.setEscapeModelStrings(false));

		// Add the Backbone View classes
	}

}
