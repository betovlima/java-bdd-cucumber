package com.example.bdd.product;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final Map<Long, Product> db = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    @Override
    public Product save(Product p) {
        if (p.getId() == null) p.setId(seq.incrementAndGet());
        db.put(p.getId(), p);
        return p;
    }

    @Override
    public Optional<Product> findById(Long id) { return Optional.ofNullable(db.get(id)); }

    @Override
    public List<Product> findAll() { return new ArrayList<>(db.values()); }

    @Override
    public void deleteById(Long id) { db.remove(id); }

    @Override
    public void deleteAll() { db.clear(); }
}