import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static int WIDTH = 480, HEIGHT = 480;

    public Game(){
        this.setPreferredSize(new Dimension(WIDTH   ,HEIGHT));

    }

    public void tick(){

    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }


        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g.setColor(Color.red);
        g.fillRect(10,10,100,100);

        bs.show();

    }
    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("Mini Zelda");
        frame.pack();
        frame.setLocationRelativeTo(null);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void run() {

        while (true){
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}