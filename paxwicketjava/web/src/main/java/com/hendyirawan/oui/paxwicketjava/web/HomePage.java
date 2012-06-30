package com.hendyirawan.oui.paxwicketjava.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.ops4j.pax.wicket.api.PaxWicketBean;

/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class HomePage extends WebPage {

	@PaxWicketBean(name="navigation")
	private Navigation navigation;
	
	public HomePage() {
		add(new Label("pageTitle", "Buy Amazing Fashion"));
		add(new Header());
		add(new ListView<Category>("categories", navigation.getCategories()) {
			@Override
			protected void populateItem(ListItem<Category> item) {
				Category category = item.getModelObject();
				item.add(new ExternalLink("name", "#" + category.getName(), category.getName()));
			}
		});
		add(new Label("message", "Hello world!"));
		add(new Footer());
	}

}
