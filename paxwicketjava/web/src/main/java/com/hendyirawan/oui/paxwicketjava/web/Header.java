package com.hendyirawan.oui.paxwicketjava.web;

import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class Header extends Panel {
	
	@PaxWicketBean(name="navigation")
	private Navigation navigation;
	
	public Header() {
		this(null);
		setRenderBodyOnly(true);
		add(new ListView<Category>("navbar", navigation.getCategories()) {
			@Override
			protected void populateItem(ListItem<Category> item) {
				Category category = item.getModelObject();
				item.add(new ExternalLink("item", "#" + category.getName(), category.getName()));
			}
		});
//		RepeatingView rv = new RepeatingView("rv");
//		add(rv);
//		for (Category cat : navigation.getCategories()) {
//			rv.add(new ExternalLink(rv.newChildId(), "#" + cat.getName(), cat.getName()));
//		}
	}

	public Header(IModel<?> model) {
		super("header", model);
	}
	
	

}
