package com.gassion.tennis_match_table.repository;

import org.hibernate.HibernateException;

import java.util.List;

public abstract class DAO <T>{
    public abstract T getByID(long id) throws HibernateException;
    public abstract List<T> getAll() throws HibernateException;
    public abstract void set(T t) throws HibernateException;
    public abstract void add(T t) throws HibernateException;
    public abstract void delete(int id) throws HibernateException;
}
