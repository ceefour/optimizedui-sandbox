package com.hendyirawan.oui.paxwicketjava.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.soluvas.push.data.SyncRepository;

import com.hendyirawan.oui.paxwicketjava.core.Category;

/**
 * @author ceefour
 *
 */
@Command(scope="prodcat", name="rm", description="Delete product category/categories.")
public class ProductCategoryRmCommand extends OsgiCommandSupport {

	private SyncRepository<String, Category> categoryRepository;
	
	@Argument(index=0, name="ID", required=true, multiValued=true)
	private String[] ids;
	
	@Override
	protected Object doExecute() throws Exception {
		for (String id : ids) {
			System.out.print(String.format("Deleting %-20s...", id));
			categoryRepository.delete(id);
			System.out.println(" DELETED");
		}
		return null;
	}

	public SyncRepository<String, Category> getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(SyncRepository<String, Category> categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

}
