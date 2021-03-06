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
@Command(scope="prodcat", name="rename", description="Rename product category.")
public class ProductCategoryRenameCommand extends OsgiCommandSupport {

	private SyncRepository<String, Category> categoryRepository;
	
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

	public SyncRepository<String, Category> getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(SyncRepository<String, Category> categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

}
