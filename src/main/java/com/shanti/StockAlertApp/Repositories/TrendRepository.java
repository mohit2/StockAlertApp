package com.shanti.StockAlertApp.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.shanti.StockAlertApp.Model.Trend;

public interface TrendRepository extends CrudRepository<Trend, String> {

}
