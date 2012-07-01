package com.hendyirawan.oui.paxwicketjava.web;

import java.util.List;

import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import com.hendyirawan.oui.paxwicketjava.core.Category;

/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class Header extends Panel {
	
	/**
	 * @todo Should accept an Either<Throwable, List<Categories>> for example
	 * if TimeoutException.
	 * @param categories
	 */
	public Header(List<Category> categories) {
		super("header");
		setRenderBodyOnly(true);
		add(new ListView<Category>("navbar", categories) {
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

}
