package service;

import java.util.ArrayList;
import entity.Consulta;

public class ConsultaService {
    public ArrayList<Consulta> consultas = new ArrayList<>();

    public void marcar(Consulta c) {
        consultas.add(c);
    }

    public void listar() {
        if (consultas.isEmpty()) {
            System.out.println("\nNenhuma consulta marcada.");
            return;
        }
        System.out.println("\n--- Consultas Marcadas ---");
        for (Consulta c : consultas) {
            System.out.println(c);
        }
    }
}