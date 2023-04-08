import javax.swing.*;
import java.awt.*;

public class Tela extends Canvas implements Runnable {

    private static final long serialVersionUID= 1L;
    private static JFrame frame;
    public static final int LARGURA = 400, ALTURA = 300;
    public static final int ESCALA = 2;
    private Thread thread;
    private boolean isRunning = true;
    private int frames = 0;



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

    //Implements RUNNABLE
    @Override
    public void run() {
        requestFocus(); //chamar a atenção do windows
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        double timer = System.currentTimeMillis();

        //LOOP GAME
        while (isRunning) {
            long now = System.nanoTime();
            delta +=(now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1){
                tick();
                render();
                frames++;
                delta--;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
//                System.out.println("FPS: "+frames);
                frame.setTitle("Meu jogo JAVA GAME COM O FPS: " + frames);
                frames = 0;
                timer += 1000;
            }
        }

        stop();
   }

    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {

        }

    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }
    private void tick() {
        System.out.println("TICK OK ");

    }
    private void render() {
    }




}
