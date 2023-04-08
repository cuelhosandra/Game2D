import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Tela extends Canvas implements Runnable {

    private static final long serialVersionUID= 1L;
    private static JFrame frame;
    public static final int LARGURA = 400, ALTURA = 300;
    public static final int ESCALA = 2;
    private Thread thread;
    private boolean isRunning = true;
    private int frames = 0;
    private final BufferedImage image;


    public Tela() {
        //Tamanho da nossa tela
        this.setPreferredSize(new Dimension(LARGURA *ESCALA, ALTURA * ESCALA));
        initFrame();
        image = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
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


    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = image.getGraphics();
        //Render do game - estamos pintando a tela de fundo
        g.setColor(new Color(0,0,0));
        g.fillRect(0,0,LARGURA, ALTURA);
        g = bs.getDrawGraphics();
        g.drawImage(image, 0,0,LARGURA * ESCALA, ALTURA * ESCALA, null);
        //A partir daqui tudo será renderizado em cima da cor do background

        //RENDERIZANDO UM TEXTO
        g.setColor(new Color(255,0,0));
        g.setFont(new Font("arial", Font.BOLD, 30));
        g.drawString("TESTANDO TEXTO RENDER ", 50,250);

        //Final dos objetos a serem desenhados
        bs.show();
        g.dispose(); //desalocar coisas inuteis da memoria
    }




}
