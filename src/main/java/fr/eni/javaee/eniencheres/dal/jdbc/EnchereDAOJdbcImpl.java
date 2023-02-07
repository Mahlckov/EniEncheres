package fr.eni.javaee.eniencheres.dal.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Articles;
import fr.eni.javaee.eniencheres.bo.Encheres;
import fr.eni.javaee.eniencheres.bo.Utilisateur;
import fr.eni.javaee.eniencheres.dal.CodesResultatDAL;
import fr.eni.javaee.eniencheres.dal.ConnectionProvider;
import fr.eni.javaee.eniencheres.dal.EnchereDAO;


public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String SELECT_ALL_ENCHERES_BY_NOARTICLE = "SELECT * FROM ENCHERES WHERE no_article = ?";
	
	private static final String INSERT_ENCHERE ="INSERT INTO ENCHERES(no_acheteur, no_article, date_enchere,\r\n"
			+ "			 montant_enchere) "
			+ "			 VALUES(?,?,?,?)";
	
	UtilisateurDAOJdbcImpl utilisateurDAOJdbcImpl = new UtilisateurDAOJdbcImpl();
	ArticleDAOJdbcImpl articleDAOJdbcImpl = new ArticleDAOJdbcImpl();
	
	
	
	
	
	
	public List<Encheres> selectAllEncheresByNoArticle(int noArticle) throws BusinessException {
		List<Encheres> listEncheres = new ArrayList<Encheres>();
		
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
		PreparedStatement ps = cnx.prepareStatement(SELECT_ALL_ENCHERES_BY_NOARTICLE);			
		ps.setInt(1, noArticle);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Utilisateur noAcheteur = utilisateurDAOJdbcImpl.selectUserById(rs.getInt("no_acheteur")); 
			Articles articleNo = articleDAOJdbcImpl.selectById(rs.getInt("no_article")) ;
			LocalDate date_enchere = rs.getDate("date_enchere").toLocalDate();
			int montant_enchere = rs.getInt("montant_enchere");
			
		
			Encheres encheres = new Encheres(noAcheteur,articleNo,date_enchere,montant_enchere);
			listEncheres.add(encheres);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERES_ECHEC);
			throw businessException;
		}
		return listEncheres;
	}
		
	
	public void insertEnchere(Encheres enchere) throws BusinessException{
		if (enchere== null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ENCHERE_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
//				cnx.setAutoCommit(false); //does not commit transaction automatically after each query
			    PreparedStatement pstmt;
			    ResultSet rs;
			    pstmt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
			    pstmt.setInt(1, enchere.getNoAcheteur().getNoUtilisateur());
			    pstmt.setInt(2, enchere.getNoArticle().getNoArticle());
			    pstmt.setDate(3, java.sql.Date.valueOf(enchere.getDate_enchere()));
			    pstmt.setInt(4, enchere.getMontant_enchere());
			    
			    pstmt.executeUpdate();
			    rs= pstmt.getGeneratedKeys();
			   
//			    if(rs.next()) {
//					enchere.setNoArticle(rs.getInt(1));
//					
//			    }
			    rs.close();
			    pstmt.close();
			 
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}
		
		
		
		
	}

	
	

	
	

