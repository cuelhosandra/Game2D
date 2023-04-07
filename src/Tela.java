import javax.swing.*;
import java.awt.*;

public class Tela extends Canvas {

    private static final long serialVersionUID= 1L;
    private static JFrame frame;
    public static final int LARGURA = 400, ALTURA = 300;
    public static final int ESCALA = 2;
    public Tela() {
        //Tamanho da nossa tela
        this.setPreferredSize(new Dimension(LARGURA *ESCALA, ALTURA * ESCALA));
        initFrame();
    }
    //Inicialização da tela
    private void initFrame() {
        frame = new JFrame("Teste");
        frame.add(this);
        frame.setResizable(false);
        frame.pack(); //Ajustar tudo no Canvas
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
