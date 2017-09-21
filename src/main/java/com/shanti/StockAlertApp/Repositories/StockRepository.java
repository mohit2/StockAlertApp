package com.shanti.StockAlertApp.Repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shanti.StockAlertApp.Model.QuandleModel.CompositeKey;
import com.shanti.StockAlertApp.Model.QuandleModel.StockPrice;

public interface StockRepository extends CrudRepository<StockPrice, CompositeKey>{

	@Query("select max(s.key.date) from StockPrice s where s.key.symbol = :symbol")
	Date getMaxDateForStock(@Param("symbol") String symbol);
}

