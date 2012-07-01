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
@Command(scope="prodcat", name="rename", description="Rename product product.")
public class ProductRenameCommand extends OsgiCommandSupport {

	private SyncRepository<String, Product> productRepository;
	
	@Argument(index=0, name="ID", required=true)
	private String id;
	
	@Argument(index=1, name="NAME", required=true)
	private String name;
	
	@Override
	protected Object doExecute() throws Exception {
		Product product = productRepository.findOne(id);
		product.setName(name);
		productRepository.update(id, product);
		return product.toString();
	}

	public SyncRepository<String, Product> getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(SyncRepository<String, Product> productRepository) {
		this.productRepository = productRepository;
	}

}
