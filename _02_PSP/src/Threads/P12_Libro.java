package Threads;

public class P12_Libro {

	String titulo;
	boolean libroCompletado;
	public P12_Libro(String titulo) {
		this.titulo = titulo;
		libroCompletado = false;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public boolean isLibroCompletado() {
		return libroCompletado;
	}
	public void setLibroCompletado(boolean libroCompletado) {
		this.libroCompletado = libroCompletado;
	}
	
	
	
	
}
