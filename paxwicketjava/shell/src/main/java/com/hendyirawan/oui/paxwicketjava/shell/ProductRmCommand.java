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
@Command(scope="prodcat", name="rm", description="Delete product product/categories.")
public class ProductRmCommand extends OsgiCommandSupport {

	private SyncRepository<String, Product> productRepository;
	
	@Argument(index=0, name="ID", required=true, multiValued=true)
	private String[] ids;
	
	@Override
	protected Object doExecute() throws Exception {
		for (String id : ids) {
			System.out.print(String.format("Deleting %-20s...", id));
			productRepository.delete(id);
			System.out.println(" DELETED");
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
