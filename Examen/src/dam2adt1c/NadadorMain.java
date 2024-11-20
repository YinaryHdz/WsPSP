package dam2adt1c;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NadadorMain {

	//Hago un arrayList de nadadores global para poder acceder en cualquier parte del codigo
	public static List<Nadador>nadadores = new ArrayList<Nadador>();
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al gestor de club de natación.");

        String directorio;
        System.out.println("Ruta absoluta del directorio: ");
        directorio = scanner.nextLine();

        // Preguntar al usuario de dónde cargar los datos
        System.out.println("Seleccione de dónde cargar los datos:");
        System.out.println("1. Cargar desde archivo de texto (nadadores.txt)");
        System.out.println("2. Cargar desde archivo de objetos (nadadores.obj)");
        System.out.println("3. Cargar desde archivo binario (nadadores.dat)");
        System.out.println("4. Cargar desde archivo NIO (nadadores.nio)");
        System.out.println("5. Salir");
        System.out.print("Elija una opción (1/2/3/4/5): ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                cargarNadadoresDeFicheroTexto(directorio);
                break;
            case 2:
                cargarNadadoresDeFicheroObjetos(directorio);
                break;
            case 3:
                cargarNadadoresDeFicheroBinario(directorio);
                break;
            case 4:
                cargarNadadoresDeFicheroNIO(directorio);
                break;
            case 5:
                System.out.println("Saliendo del programa.");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Opción no válida. Saliendo del programa.");
                scanner.close();
                System.exit(1);
        }

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Agregar nadador");
            System.out.println("2. Ver nadadores");
            System.out.println("3. Modificar nadador");
            System.out.println("4. Guardar datos en archivo");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción (1/2/3/4/5): ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.println("NIF: ");
                    String nif = scanner.nextLine();
                    System.out.println("Fecha de nacimiento (dd-mm-aaaa): ");
                    String fechaNacimiento = scanner.nextLine();
                    System.out.println("Peso (kg): ");
                    double peso = scanner.nextDouble();
                    System.out.println("Altura (cm): ");
                    int altura = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    Nadador nuevoNadador = new Nadador(nombre, nif, fechaNacimiento, peso, altura);
                    nadadores.add(nuevoNadador);
                    System.out.println("Nadador agregado con éxito.");
                    break;
                case 2:
                    System.out.println("Lista de nadadores:");
                    for (Nadador nadador : nadadores) {
                        System.out.println(nadador);
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el NIF del nadador que desea modificar: ");
                    String nifModificar = scanner.nextLine();
                    modificarNadador(nifModificar);
                    break;
                case 4:
                    // Preguntar al usuario dónde guardar los datos
                    System.out.println("Seleccione dónde guardar los datos:");
                    System.out.println("1. Guardar en archivo de texto (nadadores.txt)");
                    System.out.println("2. Guardar en archivo de objetos (nadadores.obj)");
                    System.out.println("3. Guardar en archivo binario (nadadores.dat)");
                    System.out.println("4. Guardar en archivo con NIO (nadadores.nio)");
                    System.out.println("5. Salir");
                    System.out.print("Elija una opción (1/2/3/4/5): ");
                    int opcionGuardar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    switch (opcionGuardar) {
                        case 1:
                            guardarNadadoresAFicheroTexto(directorio);
                            break;
                        case 2:
                            guardarNadadoresAFicheroObjetos(directorio);
                            break;
                        case 3:
                            guardarNadadoresAFicheroBinario(directorio);
                            break;
                        case 4:
                        	//Lo de buffers y canales se me complica asi que lo 
                        	//he hecho de otro modo
                            guardarNadadoresAFicheroNIO(directorio);
                            break;
                        case 5:
                            System.out.println("Saliendo del programa.");
                            scanner.close();
                            System.exit(0);
                        default:
                            System.out.println("Opción no válida.");
                    }
                    System.out.println("Datos guardados en archivo.");
                    break;
                case 5:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

	private static void guardarNadadoresAFicheroNIO(String directorio) {
	    Path rutaArchivo = Paths.get(directorio, "nadadores.nio");

	    // Usar Files.newBufferedWriter para escribir los datos
	    try (BufferedWriter writer = Files.newBufferedWriter(rutaArchivo)) {

	        // Escribir los datos de cada nadador en formato CSV.
	        for (Nadador nadador : nadadores) {
	            writer.write(nadador.toCsv());
	            writer.newLine(); 
	        }
	        System.out.println("Nadadores guardados con éxito en el archivo NIO.");
	    } catch (IOException e) {
	        System.out.println("Error al guardar los nadadores en el archivo NIO: " + e.getMessage());
	    }
	}
	
	private static void guardarNadadoresAFicheroBinario(String directorio) {
	    String rutaArchivo = directorio + "/nadadores.dat"; // Asegúrate de que el archivo tiene la ruta correcta
	    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(rutaArchivo))) {
	        for (Nadador nadador : nadadores) {
	            dos.writeUTF(nadador.getNombre());
	            dos.writeUTF(nadador.getNif());
	            dos.writeUTF(nadador.getFechaNacimiento());
	            dos.writeDouble(nadador.getPeso());
	            dos.writeInt(nadador.getAltura());
	        }
	        System.out.println("Nadadores guardados con éxito en formato binario.");
	    } catch (IOException e) {
	        System.out.println("Error al guardar los nadadores en el archivo binario: " + e.getMessage());
	    }
	}

	private static void guardarNadadoresAFicheroObjetos(String directorio) {
	    String rutaArchivo = directorio + "/nadadores.obj";  // Ruta correcta
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
	        oos.writeObject(nadadores);  // Guardar la lista completa de nadadores como un único objeto
	        System.out.println("Nadadores guardados con éxito en formato de objetos.");
	    } catch (IOException e) {
	        System.out.println("Error al guardar los nadadores en el archivo de objetos: " + e.getMessage());
	    }
	}

	private static void guardarNadadoresAFicheroTexto(String directorio) {
	    String rutaArchivo = directorio + "/nadadores.txt"; // Añadir '/' para asegurarse de que se esté creando en el directorio correcto.
	    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo))) {
	        for (Nadador nadador : nadadores) {
	            escritor.write(nadador.toCsv()); // Añadir el nadador con el formato toCsv de Nadador
	            escritor.newLine();
	        }
	        System.out.println("Nadadores guardados con éxito en el archivo de texto.");
	    } catch (IOException e) {
	        System.out.println("Error al guardar los nadadores en el archivo de texto: " + e.getMessage());
	    }
	}

	private static void modificarNadador(String nifModificar) {
		Nadador nadadorEncontrado = null;
		for (Nadador nadador : nadadores) {
			//Si el nif de un nadador que este dentro del array coincide con el nif pasador por parametro
			//nadadorEncontrado = nadador
			if(nadador.getNif().equalsIgnoreCase(nifModificar)) {
				nadadorEncontrado = nadador;
				break;//Rompe el bucle
			}
		}
		if(nadadorEncontrado == null) {
			System.out.println( "El: " +nifModificar + " no coincide con ningun nif");
			return;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Modificar datos de: " + nadadorEncontrado.getNombre());
		System.out.println("Nuevo nombre: ");
		String nuevoNombre = sc.nextLine();
		System.out.println("Nuevo nif: ");
		String nuevoNif = sc.nextLine();
		System.out.println("Nueva fecha de nacimiento: ");
		String nuevaFechaNacimiento = sc.nextLine();
		System.out.println("Nuevo peso");
		double nuevoPeso = sc.nextDouble();
		System.out.println("Nueva altura");
		int nuevaAltura = sc.nextInt();
		
		nadadorEncontrado.setNombre(nuevoNombre);
		nadadorEncontrado.setNif(nuevoNif);
		nadadorEncontrado.setFechaNacimiento(nuevaFechaNacimiento);
		nadadorEncontrado.setPeso(nuevoPeso);
		nadadorEncontrado.setAltura(nuevaAltura);
		
		System.out.println("Datos modificados con exito");
		
	}

	private static void cargarNadadoresDeFicheroNIO(String directorio) {
		
		Path rutaArchivo = Paths.get(directorio, "nadadores.nio");  // Ruta del archivo

        // Verificar si el archivo existe
        if (Files.notExists(rutaArchivo)) {
            System.out.println("El archivo no existe: " + rutaArchivo.toString());
            return;
        }

        try {
            // Leer todas las líneas del archivo
            List<String> lineas = Files.readAllLines(rutaArchivo);
            
            // Procesar cada línea del archivo
            for (String linea : lineas) {
                // Dividir la línea en partes utilizando la coma como delimitador
                String[] partes = linea.split(",");
                
                if (partes.length == 5) {  // Asegurarnos de que haya exactamente 5 campos
                    String nombre = partes[0].trim();
                    String nif = partes[1].trim();
                    
                    String fechaNacimiento = partes[2].trim();
                    double peso = Double.parseDouble(partes[3].trim());
                    int altura = Integer.parseInt(partes[4].trim());

                    // Crear un objeto Nadador y agregarlo a la lista
                    Nadador nadador = new Nadador(nombre, nif, fechaNacimiento, peso, altura);
                    nadadores.add(nadador);
                } else {
                    System.out.println("Error en el formato de la línea: " + linea);
                }
            }
            System.out.println("Nadadores cargados exitosamente desde el archivo NIO.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo NIO: " + e.getMessage());
        }
		
	}

	private static void cargarNadadoresDeFicheroBinario(String directorio) {
		File archivo = new File(directorio, "nadadores.dat");
		try (DataInputStream dis = new DataInputStream( new FileInputStream(archivo))){
			//Leemos el archivo
			while(dis.available() > 0) {
				String nombre = dis.readUTF();
                String nif = dis.readUTF();     
                String fechaNacimiento = dis.readUTF();  
                double peso = dis.readDouble();  
                int altura = dis.readInt();  
                
                Nadador nadador = new Nadador(nombre, nif, fechaNacimiento, peso, altura);
                nadadores.add(nadador);
			}
			System.out.println("Nadadores cargados con exito desde el archivo binario.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	private static void cargarNadadoresDeFicheroObjetos(String directorio) {
	    File archivo = new File(directorio, "nadadores.obj");

	    // Verificar si el archivo existe y no está vacío
	    if (!archivo.exists() || archivo.length() == 0) {
	        System.out.println("El archivo no existe o está vacío.");
	        return;  // Salir si el archivo está vacío
	    }

	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
	        // Leer la lista completa de nadadores
	        nadadores = (List<Nadador>) ois.readObject();  
	        System.out.println("Nadadores cargados con éxito desde el archivo de objetos.");
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	private static void cargarNadadoresDeFicheroTexto(String directorio) {
		File archivo = new File(directorio, "nadadores.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
			String linea = "";
			while((linea = br.readLine()) != null) {
				//Creo un array de String para dividir mi fichero en partes,
				//cada parte sera uno de los atributos del Nadador
				String [] partes = linea.split(",");
				if (partes.length == 5) {
					String nombre = partes[0].trim();
					String nif = partes[1].trim();
					String fechaNacimiento = partes[2].trim();
					double peso = Double.parseDouble(partes[3].trim());
					int altura = Integer.parseInt(partes[4].trim());
					
					//Creo el objeto nadador asignandole los valores que cree anteriormente
					Nadador nadador = new Nadador(nombre, nif, fechaNacimiento, peso, altura);
					//Agrego el nadador a mi lista de nadadores
					nadadores.add(nadador);
				}else {
                System.out.println("Formato incorrecto en línea: " + linea);
            }
        }
        
        System.out.println("Nadadores cargados con exito desde el archivo de texto.");
        
		}catch (IOException e) {
         System.out.println("Error al leer el archivo: " + e.getMessage());
	     }
	}
}