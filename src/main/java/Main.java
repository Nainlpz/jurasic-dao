
import controller.DinosaurController;
import model.dao.DinosaurDAO;
import model.dao.ExcavationDAO;
import model.dao.PaleontologistDAO;
import model.entity.Dinosaur;
import model.entity.Excavation;
import model.entity.Paleontologist;
import model.entity.Period;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DinosaurController dinosaurController = new DinosaurController();
        dinosaurController.viewAllDinosaurs();

        // Añade un nuevo personaje, paleontólogo y excavación. Nuevos personajes

        ExcavationDAO excavationDAO = new ExcavationDAO();
        PaleontologistDAO paleontologistDAO = new PaleontologistDAO();
        DinosaurDAO dinosaurDAO = new DinosaurDAO();

        // Crear y guardar Excavación
        Excavation e = new Excavation();
        e.setName("Zona 1 - Norte");
        excavationDAO.create(e);
        System.out.println("Excavación creada: " + e.getName());

        // Crear y guardar Paleontólogo
        Paleontologist paleo = new Paleontologist();
        paleo.setName("Dr. Alan Grant");
        paleo.setTeam(1);
        paleo.setExcavation(e);
        paleontologistDAO.create(paleo);
        System.out.println("Paleontólogo creado: " + paleo.getName());


        // Crea una consulta parametrizada con JPQL que permita encontrar todos los paleontólogos de una
        // excavación facilitada por parámetro.

        System.out.println("\nBuscando paleontólogos en " + e.getName());
        List<Paleontologist> resultados = paleontologistDAO.findByExcavation(e);
        for (Paleontologist p : resultados) {
            System.out.println(" - " + p.getName());
        }


        // Consultas Nominales (NamedQuery) con Inner Join y Subconsulta

        String busqueda = "Norte";

        System.out.println("\nPRUEBA 1: Usando JPQL");
        List<Paleontologist> listaJPQL = paleontologistDAO.findByNameJPQL(busqueda);
        for (Paleontologist p : listaJPQL) {
            System.out.println("Encontrado: " + p.getName());
        }

        System.out.println("\nPRUEBA 2: Usando SQL Nativo");
        List<Paleontologist> listaSQL = paleontologistDAO.findByNameSQL(busqueda);
        for (Paleontologist p : listaSQL) {
            System.out.println("Encontrado: " + p.getName());
        }


        // Extra 1: Borrado de un dinosaurio a través de una Query

        String dinosaurioTemporal = "Dinosaurio Borrar";
        Dinosaur tempDino = new Dinosaur();
        tempDino.setName(dinosaurioTemporal);
        tempDino.setHeight(1);
        tempDino.setWeight(50);
        tempDino.setLength(2);
        tempDino.setIdPeriod(null);

        dinosaurDAO.create(tempDino);
        System.out.println("\nDinosaurio temporal creado: " + tempDino.getName());

        System.out.println("\nEjecutando Borrado por Query");
        dinosaurDAO.deleteDinosaurByName(dinosaurioTemporal);

        System.out.println("\nVerificando todos los dinosaurios:");
        dinosaurController.viewAllDinosaurs();


        // Extra 2: Uso de HQL

        dinosaurDAO.queryHQL();
    }
}
