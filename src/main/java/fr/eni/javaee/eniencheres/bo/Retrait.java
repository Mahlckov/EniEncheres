package fr.eni.javaee.eniencheres.bo;

public class Retrait {
	 private  Articles noArticle;
	 private  String rue ;
	 private  String code_postal;
	 private  String ville ;
	
	 
	 public Retrait(Articles noArticle, String rue, String code_postal, String ville) {
		super();
		this.noArticle =noArticle;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}
	 
	 public Retrait(String rue, String code_postal, String ville) {
			super();
			this.rue = rue;
			this.code_postal = code_postal;
			this.ville = ville;
	}
		 
	 
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}

	public Articles getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(Articles noArticle) {
		this.noArticle = noArticle;
	}	 
}
