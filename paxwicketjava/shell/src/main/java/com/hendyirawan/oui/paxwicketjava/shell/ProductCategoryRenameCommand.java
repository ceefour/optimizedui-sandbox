package com.hendyirawan.oui.paxwicketjava.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

import com.hendyirawan.oui.paxwicketjava.core.Category;
import com.hendyirawan.oui.paxwicketjava.core.CategoryRepository;

/**
 * @author ceefour
 *
 */
@Command(scope="prodcat", name="rename", description="Rename product category.")
public class ProductCategoryRenameCommand extends OsgiCommandSupport {

	private CategoryRepository categoryRepository;
	
	@Argument(index=0, name="ID", required=true)
	private String id;
	
	@Argument(index=1, name="NAME", required=true)
	private String name;
	
	@Override
	protected Object doExecute() throws Exception {
		Category category = categoryRepository.findOne(id);
		category.setName(name);
		categoryRepository.update(id, category);
		return category.toString();
	}

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

}
