package com.hendyirawan.oui.paxwicketjava.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
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
		RepeatingView rv = new RepeatingView("rv");
		add(rv);
		for (Category cat : navigation.getCategories()) {
			rv.add(new Label(rv.newChildId(), cat.getName()));
		}
	}

	public Header(IModel<?> model) {
		super("header", model);
	}
	
	

}
