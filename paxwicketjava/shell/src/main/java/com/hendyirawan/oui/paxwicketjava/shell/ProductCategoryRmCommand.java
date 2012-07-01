package com.hendyirawan.oui.paxwicketjava.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

import com.hendyirawan.oui.paxwicketjava.core.CategoryRepository;

/**
 * @author ceefour
 *
 */
@Command(scope="prodcat", name="rm", description="Delete product category/categories.")
public class ProductCategoryRmCommand extends OsgiCommandSupport {

	private CategoryRepository categoryRepository;
	
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

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

}
