package com.luckyGirls.ForYourNutrition.dao;


import org.springframework.dao.DataAccessException;
import com.luckyGirls.ForYourNutrition.domain.OrderItem;

public interface OrderItemDao {
	void insertOrderItem(int member_id, int item_id, int count) throws DataAccessException; 
<<<<<<< HEAD
}
=======
}
>>>>>>> 9c54a40b224c987d308b814212938df069de40d1
