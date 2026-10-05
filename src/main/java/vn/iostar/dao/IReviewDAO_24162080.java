package vn.iostar.dao;

import java.util.List;
import vn.iostar.model.Review_24162080;

public interface IReviewDAO_24162080 {

	List<Review_24162080> findByBookId(int bookId);

	int countByBookId(int bookId);

	boolean insert(Review_24162080 review);
}