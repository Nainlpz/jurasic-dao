package model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.entity.Dinosaur;
import model.entity.Excavation;
import jakarta.persistence.Query;
import java.util.List;

public class ExcavationDAO implements InterfaceDAO<Excavation> {

    private EntityManagerFactory emf;
    private EntityManager manager;

    private void initHibernate() {
        emf = Persistence.createEntityManagerFactory("default");
        manager=emf.createEntityManager();
    }

    public ExcavationDAO() {
        initHibernate();
    }

    @Override
    public void create(Excavation e) {
        manager.getTransaction().begin();
        manager.persist(e);
        manager.getTransaction().commit();
    }

    @Override
    public List<Excavation> findAll() {
        Query query = manager.createQuery("select e from Excavation e");
        return query.getResultList();
    }

    @Override
    public void update(Excavation e) {
        manager.getTransaction().begin();
        manager.merge(e);
        manager.getTransaction().commit();
    }

    @Override
    public void delete(Excavation e) {
        manager.getTransaction().begin();
        Excavation eDrop = manager.find(Excavation.class, e.getId());
        manager.remove(eDrop);
        manager.getTransaction().commit();
    }
}
