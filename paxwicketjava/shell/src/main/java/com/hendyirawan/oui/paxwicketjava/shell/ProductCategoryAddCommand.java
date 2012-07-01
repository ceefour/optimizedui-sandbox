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
@Command(scope="prodcat", name="add", description="Add product category.")
public class ProductCategoryAddCommand extends OsgiCommandSupport {

	private SyncRepository<String, Category> categoryRepository;
	
	@Argument(index=0, name="NAME", required=true)
	private String name;
	
	@Argument(index=1, name="ID", required=false)
	private String id;
	
	@Override
	protected Object doExecute() throws Exception {
		if (id == null)
			id = name.toLowerCase();
		Category category = new Category(id, name);
		categoryRepository.create(category);
		return category.toString();
	}

	public SyncRepository<String, Category> getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(SyncRepository<String, Category> categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

}
