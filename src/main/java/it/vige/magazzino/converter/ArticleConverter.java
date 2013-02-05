package it.vige.magazzino.converter;

import it.vige.magazzino.inventory.all.ArticleAllSearch;
import it.vige.magazzino.model.Article;

import javax.faces.component.UIComponent;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.jboss.seam.faces.conversion.Converter;

@FacesConverter("articleConverter")
public class ArticleConverter extends Converter<Article> {

	@Inject
	private ArticleAllSearch articleAllSearch;

	@Override
	public Article toObject(UIComponent arg0, String arg1) {
		// TODO Auto-generated method stub
		for (Article article : articleAllSearch.getAllArticles())
			if (article.getCodeArticle().equals(arg1))
				return article;
		return null;
	}

	@Override
	public String toString(UIComponent arg0, Article arg1) {
		// TODO Auto-generated method stub
		return arg1.getCodeArticle();
	}

}
