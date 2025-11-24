package model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.entity.Paleontologist;
import jakarta.persistence.Query;
import java.util.List;

public class PaleontologistDAO implements InterfaceDAO<Paleontologist> {

    private EntityManagerFactory emf;
    private EntityManager manager;

    public void initHibernate() {
        emf = Persistence.createEntityManagerFactory("default");
        manager = emf.createEntityManager();
    }

    public PaleontologistDAO() {
        initHibernate();
    }

    @Override
    public void create(Paleontologist p) {
        manager.getTransaction().begin();
        manager.persist(p);
        manager.getTransaction().commit();
    }

    @Override
    public List<Paleontologist> findAll() {
        Query query = manager.createQuery("select p from Paleontologist p");
        return query.getResultList();
    }

    @Override
    public void update(Paleontologist p) {
        manager.getTransaction().begin();
        manager.merge(p);
        manager.getTransaction().commit();
    }

    @Override
    public void delete(Paleontologist p) {
        manager.getTransaction().begin();
        Paleontologist pDrop = manager.find(Paleontologist.class, p.getId());
        manager.remove(pDrop);
        manager.getTransaction().commit();
    }
}