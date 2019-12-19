package com.icecream.springboot.repository.user;

import com.icecream.springboot.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

//@Repository
public class UserRepositoryImpl implements UserRepository {

    //用来生成一个递增的id ，作为用户的唯一编号。
    private static AtomicLong counterId = new AtomicLong();

    //模拟数据的存储，
    private final ConcurrentMap<Long,User> userConcurrentMap = new ConcurrentHashMap<Long, User>();

    @Override
    public User saveOrUpdateUser(User user) {
        Long id = user.getId();
        if(id == null){
            id = counterId.incrementAndGet();
            user.setId(id);
        }
        this.userConcurrentMap.put(id,user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        this.userConcurrentMap.remove(id);
    }

    @Override
    public User getUserById(Long id) {
        return this.userConcurrentMap.get(id);
    }

    @Override
    public List<User> userList() {
        return new ArrayList<>(this.userConcurrentMap.values());
    }

    @Override
    public List<User> findUsersByNameLike(String name) {
        return null;
    }

    @Override
    public <S extends Long> S save(S s) {
        return null;
    }

    @Override
    public <S extends Long> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Long> findById(User user) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(User user) {
        return false;
    }

    @Override
    public Iterable<Long> findAll() {
        return null;
    }

    @Override
    public Iterable<Long> findAllById(Iterable<User> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(User user) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void deleteAll(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
