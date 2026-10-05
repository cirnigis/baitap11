package vn.iostar.service.impl;

import java.util.List;

import vn.iostar.dao.IReviewDAO_24162080;
import vn.iostar.dao.impl.ReviewDAO_24162080;
import vn.iostar.model.Review_24162080;
import vn.iostar.service.IReviewService_24162080;

public class ReviewService_24162080 implements IReviewService_24162080 {

	private final IReviewDAO_24162080 reviewDAO = new ReviewDAO_24162080();

	@Override
	public List<Review_24162080> findByBookId(int bookId) {
		return reviewDAO.findByBookId(bookId);
	}

	@Override
	public int countByBookId(int bookId) {
		return reviewDAO.countByBookId(bookId);
	}

	@Override
	public boolean insert(Review_24162080 review) {
		return reviewDAO.insert(review);
	}
}