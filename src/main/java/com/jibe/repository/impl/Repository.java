package com.jibe.repository.impl;



public interface Repository <T, Long>{

    void save(T object, Long id);
}
