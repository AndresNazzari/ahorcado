import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        int vidas = 6;
        // Crear un array con todas las letras del abecedario
        Object[] abecedario = {"Seleccione una letra:",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};

        ArrayList<String> letrasElegidas = new ArrayList<>();

        // Mostrar mensaje de bienvenida y seleccionar dificultad
        JOptionPane.showMessageDialog(null, "Bienvenido al juego del Ahorcado");
        String dificultad = seleccionarDificultad();
        // Mostrar mensaje de dificultad seleccionada y selecciona el listado de palabras posibles segun la dificultad
        String[] palabraSeleccionada = seleccionarPalabra(dificultad);

        ArrayList<String> palabraAdivinada = new ArrayList<>();
        int i = 0;
        do {
            palabraAdivinada.add("_");
            i++;
        } while (i< palabraSeleccionada.length);

        String persona = dibujarPersona(vidas);
        JOptionPane.showMessageDialog(null,
                "Intenta salvar al ahorcado \n" + persona + "\n" +
                        "La palabra tiene " + palabraSeleccionada.length + " letras.\n" +
                        "Tenes " + vidas + " vidas.");

        while (vidas > 0) {
            // selecciona una letra
            String letraSeleccionada;

            do {
                letraSeleccionada = (String)JOptionPane.showInputDialog(null,"Selecciona una letra:",
                        "Seleccionar letra",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        abecedario,
                        abecedario[0]);

                if (letraSeleccionada.equals("Seleccione una letra:")) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una letra.");
                } else if (letrasElegidas.contains(letraSeleccionada)) {
                    JOptionPane.showMessageDialog(null, "Ya seleccionaste esa letra, por favor, seleccione otra.");
                } else {
                    letrasElegidas.add(letraSeleccionada);
                    //remueve la letra elegida del abecedario
                    for (int j = 0; j < abecedario.length; j++) {
                        if (abecedario[j].equals(letraSeleccionada)) {
                            // Convertir el array a un ArrayList para eliminar un objeto
                            ArrayList<Object> arrayListAbecedario = new ArrayList<>(Arrays.asList(abecedario));
                            // Eliminar el elemento del ArrayList
                            arrayListAbecedario.remove(letraSeleccionada);
                            // Convertir el ArrayList de nuevo a un array
                            //es necesario que sea un objeto para mostrarlo en joptionpane select
                            abecedario = arrayListAbecedario.toArray();
                            break;
                        }
                    }
                    break;
                }
            } while (true);


            ArrayList<Integer> posicionesDeLetra = posicionesDeLetra(letraSeleccionada, palabraSeleccionada);

            for (int j = 0; j < posicionesDeLetra.size(); j++) {
                palabraAdivinada.set(posicionesDeLetra.get(j), letraSeleccionada);
            }

            String palabraAdivinadaStr = "";
            for (int j = 0; j < palabraAdivinada.size(); j++) {
                palabraAdivinadaStr += palabraAdivinada.get(j);
                palabraAdivinadaStr += " ";
            }

            if (posicionesDeLetra.size() > 0) {
                persona = dibujarPersona(vidas);
                JOptionPane.showMessageDialog(null,
                        "Adivinaste!! \n" + persona + "\n" +
                                "La palabra tiene " + palabraSeleccionada.length + " letras.\n" +
                                palabraAdivinadaStr + "\n" +
                                "Te quedan " + vidas + " vidas, no perdiste ninguna!!.");
            } else {
                vidas--;
                persona = dibujarPersona(vidas);
                JOptionPane.showMessageDialog(null,
                        "La letra " + letraSeleccionada + " no esta en la palabra. \n" +
                                persona + "\n" +
                                palabraAdivinadaStr + "\n" +
                                "Perdiste una vida! Te quedan " + vidas + ".");

                if (vidas == 0) {
                    JOptionPane.showMessageDialog(null, "Has perdido...");
                    break;
                }
            }
        };


    }

    public static ArrayList<Integer> posicionesDeLetra(String letra, String[] palabra){
        ArrayList<Integer> posicionesLetra = new ArrayList<>();
        for (int i = 0; i < palabra.length; i++) {
            if (palabra[i].equals(letra)) {
                posicionesLetra.add(i);
            }
        }
        return posicionesLetra;
    };

    public static String[] seleccionarPalabra(String dificultad) {
        String[][] palabras;
        Random random = new Random();
        switch (dificultad) {
            case "Equilibrado":
                JOptionPane.showMessageDialog(null, "Has seleccionado la dificultad: " + dificultad);
                palabras = new String[][] {
                        {"F", "A", "C", "I", "L"},
                        {"F", "A", "C", "I", "L", "2"},
                        {"F", "A", "C", "I", "L", "3"},
                        {"F", "A", "C", "I", "L", "4"},
                        {"F", "A", "C", "I", "L", "5"}
                };
                break;
            case "Desafiante":
                JOptionPane.showMessageDialog(null, "Has seleccionado la dificultad: " + dificultad);
                palabras = new String[][] {
                        {"M", "E", "D", "I", "O"},
                        {"M", "E", "D", "I", "O", "2"},
                        {"M", "E", "D", "I", "O", "3"},
                        {"M", "E", "D", "I", "O", "4"},
                        {"M", "E", "D", "I", "O", "5"}
                };
                break;
            case "Imposible":
                JOptionPane.showMessageDialog(null, "Has seleccionado la dificultad: " + dificultad);
                palabras = new String[][] {
                        {"D", "I", "F", "I", "C", "I", "L"},
                        {"D", "I", "F", "I", "C", "I", "L", "2"},
                        {"D", "I", "F", "I", "C", "I", "L", "3"},
                        {"D", "I", "F", "I", "C", "I", "L", "4"},
                        {"D", "I", "F", "I", "C", "I", "L", "5"}
                };
                break;
            default:
                palabras = new String[][]{}; // esto no deberia pasar nunca
                break;
        }
        return palabras[random.nextInt(palabras.length)];
    };

    public static String seleccionarDificultad(){
        Object[] dificultades = {"Equilibrado", "Desafiante", "Imposible"};
        return (String)JOptionPane.showInputDialog(null,"Selecciona la dificultad:\n"
                        + "Esto impactara en las palabras que tendras que adivinar\n"
                        + "En cualquier caso tendras la misma cantidad de vidas: 6",
                "Dificultad",
                JOptionPane.PLAIN_MESSAGE,
                null,
                dificultades,
                "Fácil");
    }

    public static String dibujarPersona(int intentos) {
                // Array con las etapas de dibujo
        String[] dibujo = {
                          "  ______ |\n"
                        + "  |              |\n"
                        + "  O            |\n"
                        + " /|\\             |\n"
                        + " / \\             |\n"
                        + "                 |\n"
                        + "_______ |",

                "  ______ |\n"
                        + "  |              |\n"
                        + "  O            |\n"
                        + " /|\\             |\n"
                        + " /               |\n"
                        + "                 |\n"
                        + "_______ |",

                "  ______ |\n"
                        + "  |              |\n"
                        + "  O            |\n"
                        + " /|\\             |\n"
                        + "                 |\n"
                        + "                 |\n"
                        + "_______ |",

                "  ______ |\n"
                        + "  |              |\n"
                        + "  O            |\n"
                        + " /|              |\n"
                        + "                 |\n"
                        + "                 |\n"
                        + "_______ |",


                "  ______ |\n"
                        + "  |              |\n"
                        + "  O            |\n"
                        + "  |              |\n"
                        + "                 |\n"
                        + "                 |\n"
                        + "_______ |",


                "  ______ |\n"
                        + "  |              |\n"
                        + "  O            |\n"
                        + "                 |\n"
                        + "                 |\n"
                        + "                 |\n"
                        + "_______ |",

                "  ______ |\n"
                        + "  |              |\n"
                        + "                 |\n"
                        + "                 |\n"
                        + "                 |\n"
                        + "                 |\n"
                        + "_______ |",
        };

        //JOptionPane.showMessageDialog(null, dibujo[intentos]);
        return dibujo[intentos];
    }

}