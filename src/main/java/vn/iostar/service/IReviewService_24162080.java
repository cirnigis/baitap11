package vn.iostar.service;

import java.util.List;
import vn.iostar.model.Review_24162080;

public interface IReviewService_24162080 {

	List<Review_24162080> findByBookId(int bookId);

	int countByBookId(int bookId);

	boolean insert(Review_24162080 review);
}