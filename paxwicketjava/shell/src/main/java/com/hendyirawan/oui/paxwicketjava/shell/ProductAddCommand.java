package com.hendyirawan.oui.paxwicketjava.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.soluvas.push.data.SyncRepository;

import com.hendyirawan.oui.paxwicketjava.core.Product;

/**
 * @author ceefour
 *
 */
@Command(scope="product", name="add", description="Add product.")
public class ProductAddCommand extends OsgiCommandSupport {

	private SyncRepository<String, Product> productRepository;
	
	@Argument(index=0, name="NAME", required=true)
	private String name;
	
	@Argument(index=1, name="ID", required=false)
	private String id;
	
	@Override
	protected Object doExecute() throws Exception {
		if (id == null)
			id = name.toLowerCase();
		Product product = new Product(id, id, name);
		productRepository.create(product);
		return product.toString();
	}

	public SyncRepository<String, Product> getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(SyncRepository<String, Product> productRepository) {
		this.productRepository = productRepository;
	}

}
