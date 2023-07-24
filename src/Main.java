import javax.swing.JOptionPane;
public class Main {
    public static void main(String[] args) {

        int intentos = 0;
        int maxIntentos = 6;

        // Bucle para mostrar progresivamente las etapas de dibujo
        while (intentos < maxIntentos) {
            dibujarPersona(intentos);
            intentos++;
        }

        // Mostrar la última etapa del dibujo cuando se agoten los intentos
        dibujarPersona(intentos);

        JOptionPane.showMessageDialog(null, "Has perdido...");
    }

    public static void dibujarPersona(int intentos) {
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

        JOptionPane.showMessageDialog(null, dibujo[intentos]);
    }

}