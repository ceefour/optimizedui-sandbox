package com.hendyirawan.oui.paxwicketjava.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class HomePage extends WebPage {

	public HomePage() {
		add(new Header());
		add(new Label("message", "Hello world!"));
		add(new Footer());
	}

}
