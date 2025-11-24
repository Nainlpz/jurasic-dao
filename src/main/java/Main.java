
import controller.DinosaurController;
import model.dao.ExcavationDAO;
import model.dao.PaleontologistDAO;
import model.entity.Excavation;
import model.entity.Paleontologist;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DinosaurController dinosaurController = new DinosaurController();
        dinosaurController.viewAllDinosaurs();

        // Añade un nuevo personaje, paleontólogo y excavación. Nuevos personajes

        ExcavationDAO excavationDAO = new ExcavationDAO();
        PaleontologistDAO paleontologistDAO = new PaleontologistDAO();

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
    }
}
