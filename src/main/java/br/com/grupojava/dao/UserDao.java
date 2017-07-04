package br.com.grupojava.dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import br.com.grupojava.model.User;
import br.com.grupojava.model.User_;
import br.com.grupojava.util.JPAUtil;

public class UserDao {
	
	  private EntityManager manager;

	
	public UserDao() {
	    manager = new JPAUtil().getEntityManager();
	  }
	
	public void addUser(User user) {
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

    }

    public void addUserCriteria(User user) {
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

    }
	
	public void deleteUser(int userId) {
        manager.getTransaction().begin();
        manager.createQuery("Delete User where id = '" + userId + "'").executeUpdate();
        manager.getTransaction().commit();
	}
	
	public void updateUser(User user) {
		manager.getTransaction().begin();
		manager.merge(user);
	    manager.getTransaction().commit();

    }
	
	public List<User> getAllUsers() {
		List<User> users = null;
		try {
        manager.getTransaction().begin();
         users = (List<User>) manager.createQuery("from User").getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
        return users;
    }
	
	public List<User> getAllUsersCriteria() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root); // necessário caso uma condição where seja adicionada na consulta
		TypedQuery<User> query = manager.createQuery(criteriaQuery);
		List<User> users =  query.getResultList();
		return users;
    }
	
	public User getUserByLogin(String login) {
		User user = null;
		try {
			manager.getTransaction().begin();
			user = manager.createQuery("From User where userName = '"  + login + "'", User.class)
					.getSingleResult();
			manager.getTransaction().commit();
		}catch(NoResultException e){
			System.out.println("Nenhum usuário encontrado");
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			return user;
		}
    }
	
	public User getUserByLoginCriteria(String login) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery( User.class );
		Root<User> root = criteria.from( User.class );
		criteria.select( root );
		criteria.where( builder.equal( root.get( User_.userName ), login ) );
		User user = manager.createQuery( criteria ).getSingleResult();
        return user;
    }
	
	public void updateUserName(String novoUserName, Long userId){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaUpdate<User> update = builder.createCriteriaUpdate(User.class);
        Root<User> root = update.from(User.class);
        update.set("userName", novoUserName);
        update.where(builder.equal(root.get(User_.id), userId));
        manager.createQuery(update).executeUpdate();
	}
	
	
	
	public void close() {
		manager.close();
	  }
	
}
