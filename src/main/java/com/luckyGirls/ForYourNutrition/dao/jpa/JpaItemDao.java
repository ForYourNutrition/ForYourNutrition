package com.luckyGirls.ForYourNutrition.dao.jpa;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luckyGirls.ForYourNutrition.dao.ItemDao;
import com.luckyGirls.ForYourNutrition.domain.Item;

@Repository
@Transactional
public class JpaItemDao implements ItemDao{

    @PersistenceContext
    private EntityManager em;

    public Item findById(int item_id) {
        return em.find(Item.class, item_id);
    }

    public List<Item> findAll() {
        return em.createQuery("from Item", Item.class).getResultList();
    }

	@Override
	public void saveItem(Item item) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(item);
	}

	@Override
	public void updateItem(Item item) throws DataAccessException {
		// TODO Auto-generated method stub
		em.merge(item);
	}

	@Override
	public void deleteItem(Item item) throws DataAccessException {
		// TODO Auto-generated method stub
		em.remove(item);
	}
}