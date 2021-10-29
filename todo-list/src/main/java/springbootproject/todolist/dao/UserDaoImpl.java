package springbootproject.todolist.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springbootproject.todolist.entity.User;


@Repository
public class UserDaoImpl implements UserDao {
	
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public User findByUsername(String username) {
		Session theSession=entityManager.unwrap(Session.class);
		
		Query<User> theQuery=theSession.createQuery("from User where username=:uname",User.class);
		
		
		theQuery.setParameter("uname", username);
		
		User theUser=null;
		try {
			theUser=theQuery.getSingleResult();
		}
		catch(Exception exc) {
			theUser=null;
		}
		return theUser;
	}

	@Override
	public void save(User user) {
		Session theSession=entityManager.unwrap(Session.class);
		
		theSession.saveOrUpdate(user);

	}

	@Override
	public List<User> getAllUsers() {
		Session theSession=entityManager.unwrap(Session.class);
		Query theQuery=theSession.createQuery("from User",User.class);
		List<User> userList=theQuery.getResultList();
		return userList;
	}

}
