package edu.vn.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.vn.model.User;

public class UserDao extends AbstractEntityDao<User> {

	public UserDao() {
		super(User.class);
	}

	public void changPassword(String username, String oldPassword, String newPassword) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();
		// tim kiem thong tin username va passwrod cos trong csdl hay khong
		String jpql = "SELECT u FROM edu.vn.User u WHERE u.username =:username and u.password =:password";

		// thiet lap cac gia tri co cac tham so
		try {
			trans.begin();
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("username", username);
			query.setParameter("password", oldPassword);

			// tra ve cac doi tuong
			User user = query.getSingleResult();

			// ko tim thay thi tra ve username == rong
			if (user == null) {
				// nem ra ngoai le
				throw new Exception("Current password or Username are incorrect");
			}
			// tra ve user (Tim thay)
			user.setPassword(newPassword);
			// thay mat khau cu thanh mat khau moi
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	// tim kiem thong tin cua username theo email

	public User findByUsernameAndEmail(String username, String email) {
		
		EntityManager em = JpaUtils.getEntityManager();

		String jpql = "select u from edu.vn.model.User u where u.username =:username and u.email =:email";

		try {
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			//thiet lap cac gia tri
			query.setParameter("username", username);
			query.setParameter("email", email);
			//tra ve ket qua cua user duoc thay(tra ve duy nhat)
			return query.getSingleResult();

		} finally {
			em.close();
		}
	}
}
