package dam2adt1c;

import java.io.Serializable;

public class Nadador implements Serializable {
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String nombre;
	    public void setNif(String nif) {
			this.nif = nif;
		}

		private String nif;
	    private String fechaNacimiento;
	    private double peso;
	    private int altura;

	    public Nadador(String nombre, String nif, String fechaNacimiento, double peso, int altura) {
	        this.nombre = nombre;
	        this.nif = nif;
	        this.fechaNacimiento = fechaNacimiento;
	        this.peso = peso;
	        this.altura = altura;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getNif() {
	        return nif;
	    }

	    public String getFechaNacimiento() {
	        return fechaNacimiento;
	    }

	    public void setFechaNacimiento(String fechaNacimiento) {
	        this.fechaNacimiento = fechaNacimiento;
	    }

	    public double getPeso() {
	        return peso;
	    }

	    public void setPeso(double peso) {
	        this.peso = peso;
	    }

	    public int getAltura() {
	        return altura;
	    }

	    public void setAltura(int altura) {
	        this.altura = altura;
	    }

	    @Override
	    public String toString() {
	        return "Nadador{" +
	                "nombre='" + nombre + '\'' +
	                ", nif='" + nif + '\'' +
	                ", fechaNacimiento='" + fechaNacimiento + '\'' +
	                ", peso=" + peso +
	                ", altura=" + altura +
	                '}';
	    }

	    public String toCsv() {
	        return nombre + "," + nif + "," + fechaNacimiento + "," + peso + "," + altura;
	    }
}
