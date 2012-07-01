package com.hendyirawan.oui.paxwicketjava.shell;

import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.soluvas.push.data.SyncRepository;

import com.hendyirawan.oui.paxwicketjava.core.Product;

/**
 * @author ceefour
 *
 */
@Command(scope="prodcat", name="ls", description="List product categories.")
public class ProductLsCommand extends OsgiCommandSupport {

	private SyncRepository<String, Product> productRepository;
	
	@Override
	protected Object doExecute() throws Exception {
		for (Product cat : productRepository.findAll()) {
			System.out.println(String.format("%-20s%s", cat.getId(), cat.getName()));
		}
		return null;
	}

	public SyncRepository<String, Product> getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(SyncRepository<String, Product> productRepository) {
		this.productRepository = productRepository;
	}

}
