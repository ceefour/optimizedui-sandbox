package com.hendyirawan.oui.paxwicketjava.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

import com.google.common.base.Optional;

/**
 * @author ceefour
 *
 */
@Command(scope="oui", name="hello", description="Says hello")
public class HelloCommand extends OsgiCommandSupport {

	@Argument(index=0, name="NAME", required=false)
	private String name;
	
	@Override
	protected Object doExecute() throws Exception {
		System.out.println("Hello " + Optional.fromNullable(name).or("YOU") + "!");
		return "Hello " + Optional.fromNullable(name).or("YOU") + "!";
	}

}
