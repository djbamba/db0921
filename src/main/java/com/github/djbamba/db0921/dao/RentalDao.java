package com.github.djbamba.db0921.dao;

import com.github.djbamba.db0921.model.Rentable;
import java.util.List;
import java.util.Optional;

/**
 * Main interface for interacting with rental items.
 * @param <T>
 */
public interface RentalDao<T extends Rentable> {
  Optional<T> findByID(String id);

  List<T> getAll();
}
