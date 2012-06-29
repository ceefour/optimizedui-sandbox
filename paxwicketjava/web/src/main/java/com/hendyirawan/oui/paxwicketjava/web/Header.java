package com.hendyirawan.oui.paxwicketjava.web;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class Header extends Panel {

	public Header() {
		super("header");
	}

	public Header(IModel<?> model) {
		super("header", model);
	}

}
