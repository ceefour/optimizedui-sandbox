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
@Command(scope="prodcat", name="ls", description="List product categories.")
public class ProductCategoryLsCommand extends OsgiCommandSupport {

	private SyncRepository<String, Category> categoryRepository;
	
	@Argument(index=0, name="NAME", required=false)
	private String name;
	
	@Override
	protected Object doExecute() throws Exception {
		for (Category cat : categoryRepository.findAll()) {
			System.out.println(String.format("%-20s%s", cat.getId(), cat.getName()));
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
