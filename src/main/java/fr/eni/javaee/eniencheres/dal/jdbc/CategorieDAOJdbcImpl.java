package fr.eni.javaee.eniencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Categorie;
import fr.eni.javaee.eniencheres.dal.CategorieDAO;
import fr.eni.javaee.eniencheres.dal.CodesResultatDAL;
import fr.eni.javaee.eniencheres.dal.ConnectionProvider;

public class CategorieDAOJdbcImpl implements CategorieDAO{
	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORIE";
	private static final String SELECT_BY_ID_CATEGORIE = "SELECT* FROM CATEGORIES WHERE no_categorie=?";

	
	@Override
	public List<Categorie> selectAllCategories() throws BusinessException {
		List<Categorie> listCategories = new ArrayList<Categorie>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_CATEGORIES);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				
				int noCategorie = rs.getInt("noCategorie");
				String libelle = rs.getString("libelle");

				Categorie categorie = new Categorie(noCategorie, libelle);
				listCategories.add(categorie);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_CATEGORIES_ECHEC);
			throw businessException;
		}
		return listCategories;
	}	
	
	
	@Override
	public Categorie selectByIdCategorie(int noCategorie) throws BusinessException {
		Categorie categorie = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID_CATEGORIE);
			ps.setInt(1, noCategorie);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String libelle = rs.getString("libelle");
				categorie = new Categorie(noCategorie, libelle);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIE_ECHEC);
			throw businessException;	
		}
		if(categorie.getNoCategorie()==0){
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIE_INEXISTANTE);
			throw businessException;
		}
		return categorie;
	}
}
