package entity;

public class Consulta {

    public String data;
    public String hora;
    public String medico;
    public String especialidade;

    public Consulta(String data, String hora, String medico, String especialidade) {
        this.data = data;
        this.hora = hora;
        this.medico = medico;
        this.especialidade = especialidade;
    }

    public String getData() { return data; }
    public String getHora() { return hora; }
    public String getMedico() { return medico; }
    public String getEspecialidade() { return especialidade; }

    @Override
    public String toString() {
        return "Consulta - Data: " + data +
                " | Hora: " + hora +
                " | Médico: " + medico +
                " | Especialidade: " + especialidade;
    }
}