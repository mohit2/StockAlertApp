package com.shanti.StockAlertApp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shanti.StockAlertApp.Model.Stock;

public interface StockRepository extends CrudRepository<Stock, String>{

	@Query("select s.symbol from Stock s")
	List<String> getAllStockSymbol();
}
